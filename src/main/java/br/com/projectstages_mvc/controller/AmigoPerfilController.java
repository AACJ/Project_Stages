package br.com.projectstages_mvc.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.projectstages_mvc.dao.AmigosDao;
import br.com.projectstages_mvc.dao.CadastroDao;
import br.com.projectstages_mvc.dao.ChatDao;
import br.com.projectstages_mvc.dao.ConfiguracoesDao;
import br.com.projectstages_mvc.dao.NotificacaoAmizadeDao;
import br.com.projectstages_mvc.dao.ParticipantesDao;
import br.com.projectstages_mvc.dao.ProjetoDao;
import br.com.projectstages_mvc.model.Amigos;
import br.com.projectstages_mvc.model.Chat;
import br.com.projectstages_mvc.model.Configuracoes;
import br.com.projectstages_mvc.model.NotificacaoAmizade;
import br.com.projectstages_mvc.model.Participantes;
import br.com.projectstages_mvc.model.Projeto;
import br.com.projectstages_mvc.model.Usuario;

@Controller
@Transactional
public class AmigoPerfilController {

	@Autowired
	private CadastroDao cadastroDao;

	@Autowired
	private ProjetoDao projetodao;

	@Autowired
	private ChatDao chatDao;

	@Autowired
	private ConfiguracoesDao configuracoesDao;

	@Autowired
	private AmigosDao amigosDao;

	@Autowired
	private NotificacaoAmizadeDao notificacaoDao;

	@Autowired
	private ParticipantesDao participantesDao;
	
	private Usuario user = new Usuario();
	private Usuario myUser = new Usuario();
	private Configuracoes config = new Configuracoes();

	@RequestMapping("/perfil-usuarios")
	@Cacheable(value = "amigosPerfils")
	public ModelAndView amigoPerfil(@AuthenticationPrincipal Usuario usuario, @RequestParam int idUsuario,
			@RequestParam String userName) {
		ModelAndView model = new ModelAndView("amigoPerfil");
		int totalMensagens = 0;
		int quantidade = 0;
		List<NotificacaoAmizade> msgNotificacoes = new ArrayList<NotificacaoAmizade>();
		List<Chat> listMensagens = new ArrayList<Chat>();
		List<Projeto> listaProjetosParticipantes = new ArrayList<Projeto>();
		List<Projeto> listaProjetosFavoritos = new ArrayList<Projeto>();
		List<Participantes> projetosParticipantes = new ArrayList<Participantes>();
		myUser = cadastroDao.findUsuario(usuario.getUsername());
		user = cadastroDao.findById(idUsuario);
		projetosParticipantes = participantesDao.listarProjetosParticipantes(usuario.getUsername());
		msgNotificacoes = notificacaoDao.listarTodasNotificacoesDoDestinatario(usuario.getUsername());
		for (int i = 0; i < msgNotificacoes.size(); i++) {
			if (msgNotificacoes.get(i).isVisualizacao() == false) {
				quantidade++;
			}
		}

		if (quantidade > 0) {
			model.addObject("qtnNotificacao", quantidade);
		}

		// Ajusta data
		if (user.getAniversario() != null) {
			Date ajustaData = new Date(user.getAniversario().getTime());
			int dia = user.getAniversario().getDate() + 1;
			ajustaData.setDate(dia);
			model.addObject("usuarioAniversario", ajustaData);
		} else {
			model.addObject("usuarioAniversario", user.getAniversario());
		}

		// Mostra o total de mensagens nao lidas.
		listMensagens = chatDao.getAllMensagens(usuario.getEmail());
		for (int j = 0; j < listMensagens.size(); j++) {
			if (listMensagens.get(j).isVisualizacao() == false
					&& listMensagens.get(j).getEmailDestinatario().equals(usuario.getEmail())) {
				totalMensagens++;
			}
		}

		if (totalMensagens > 0) {
			model.addObject("totalMensagens", totalMensagens);
		}
		
		for (int i = 0; i < projetosParticipantes.size(); i++) {
			listaProjetosParticipantes
					.add(projetodao.listarProjetosParticipantePorID(projetosParticipantes.get(i).getIdProjeto()));
	}

		for (int i = 0; i < projetosParticipantes.size(); i++) {
			if(projetosParticipantes.get(i).isProjetoFavorito()) {
			listaProjetosFavoritos
					.add(projetodao.listarProjetosParticipantePorID(projetosParticipantes.get(i).getIdProjeto()));
			}
		}
		
		config = configuracoesDao.configuracoesDoUsuario(usuario.getUsername());
		model.addObject("listaProjeto", projetodao.listarTodosProjetos(usuario.getUsername()));
		model.addObject("usuarioNameUser", user.getUserName());
		model.addObject("usuarioId", user.getId());
		model.addObject("usuarioEmailContato", user.getEmailContato());
		model.addObject("usuarioSkype", user.getSkype());
		model.addObject("usuarioBiografia", user.getBiografia());
		model.addObject("usuarioCelular", user.getCelular());
		model.addObject("usuarioHorario", user.getHorario());
		model.addObject("usuarioLocalizacao", user.getLocalizacao());
		model.addObject("usuarioFoto", myUser.getFoto());
		model.addObject("amigoFoto", user.getFoto());
		model.addObject("emailAmigo", user.getEmail());
		model.addObject("projetosParticipantes", listaProjetosParticipantes);
		model.addObject("projetosFavoritos",listaProjetosFavoritos);
		return model;
	}

	@RequestMapping("/add/notificacao")
	@CacheEvict(value = "amigosPerfils", allEntries = true)
	public void addNotificacao(@AuthenticationPrincipal Usuario usuario) {
		NotificacaoAmizade notificacao = new NotificacaoAmizade();
		notificacao.setEmailRemetente(usuario.getUsername());
		notificacao.setEmailDestinatario(user.getEmail());
		notificacaoDao.save(notificacao);
	}

	@RequestMapping("/add/amigo")
	@CacheEvict(value = "amigosPerfils", allEntries = true)
	public void addAmigo(@AuthenticationPrincipal Usuario usuario, HttpServletRequest request) {
		Amigos amigos = new Amigos();
		String emailAmigo = request.getParameter("emailAmigo");
		amigos.setEmailUsuario(usuario.getUsername());
		amigos.setEmailAmigo(emailAmigo);
		amigosDao.save(amigos);

		Amigos amigos2 = new Amigos();
		String emailAmigo2 = request.getParameter("emailAmigo");
		amigos2.setEmailUsuario(emailAmigo2);
		amigos2.setEmailAmigo(usuario.getUsername());
		amigosDao.save(amigos2);
	}

	@RequestMapping("/remove/notificacao")
	@CacheEvict(value = "amigosPerfils", allEntries = true)
	public void removeNotificacao(@AuthenticationPrincipal Usuario usuario, HttpServletRequest request) {
		String email = request.getParameter("email");
		System.out.println(email);
		NotificacaoAmizade notificacao = notificacaoDao.findNotificacao(usuario.getEmail(), email);
		System.out.println(notificacao.getEmailDestinatario());
		notificacaoDao.remove(notificacao);
	}

	@RequestMapping("/remove/amigo")
	@CacheEvict(value = "amigosPerfils", allEntries = true)
	public void removeAmigo(@AuthenticationPrincipal Usuario usuario) {
		Amigos amigos = amigosDao.findAmigo(usuario.getUsername(), user.getEmail());
		amigosDao.remove(amigos);
		Amigos amigos2 = amigosDao.findAmigo(user.getEmail(), usuario.getUsername());
		amigosDao.remove(amigos2);
	}

	@RequestMapping("/verificacao/amigo")
	@CacheEvict(value = "amigosPerfils", allEntries = true)
	@ResponseBody
	public boolean verfificacaoAmigo(@AuthenticationPrincipal Usuario usuario) {
		boolean amizade = amigosDao.verificacaoDeAmizade(usuario.getUsername(), user.getEmail());
		return amizade;
	}

	@RequestMapping("/verificacao/notificacao")
	@CacheEvict(value = "amigosPerfils", allEntries = true)
	@ResponseBody
	public boolean verfificacaoNotificacao(@AuthenticationPrincipal Usuario usuario) {
		boolean notificacao = notificacaoDao.verificacaoNotificacao(usuario.getUsername(), user.getEmail());
		return notificacao;
	}

	@RequestMapping("/retorna/modo-noturno/amigos-perfil")
	@CacheEvict(value = "amigosPerfils", allEntries = true)
	@ResponseBody
	public boolean getModoNotuno() {
		return config.isModoNoturno();
	}
	
}
