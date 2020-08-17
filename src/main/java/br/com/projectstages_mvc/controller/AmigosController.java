package br.com.projectstages_mvc.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.projectstages_mvc.dao.AmigosDao;
import br.com.projectstages_mvc.dao.CadastroDao;
import br.com.projectstages_mvc.dao.ChatDao;
import br.com.projectstages_mvc.dao.ConfiguracoesDao;
import br.com.projectstages_mvc.dao.NotificacaoAmizadeDao;
import br.com.projectstages_mvc.dao.ParticipantesDao;
import br.com.projectstages_mvc.dao.ProjetoDao;
import br.com.projectstages_mvc.model.Chat;
import br.com.projectstages_mvc.model.Configuracoes;
import br.com.projectstages_mvc.model.NotificacaoAmizade;
import br.com.projectstages_mvc.model.Participantes;
import br.com.projectstages_mvc.model.Projeto;
import br.com.projectstages_mvc.model.Usuario;

@Controller
@Transactional
public class AmigosController {

	@Autowired
	private CadastroDao cadastroDao;

	@Autowired
	private NotificacaoAmizadeDao notificacaoDao;

	@Autowired
	private ChatDao chatDao;

	@Autowired
	private ProjetoDao projetodao;

	@Autowired
	private AmigosDao amigosDao;

	@Autowired
	private ConfiguracoesDao configuracoesDao;
	
	@Autowired
	private ParticipantesDao participantesDao;

	private Usuario user = new Usuario();
	private Configuracoes config = new Configuracoes();

	@RequestMapping("/amigos")
	@Cacheable(value = "amigo")
	public ModelAndView amigos(@AuthenticationPrincipal Usuario usuario) {
		ModelAndView model = new ModelAndView("amigos");
		List<Usuario> listUsers = new ArrayList<Usuario>();
		listUsers = cadastroDao.getAllUsers();
		int totalMensagens = 0;
		int quantidade = 0;
		List<NotificacaoAmizade> msgNotificacoes = new ArrayList<NotificacaoAmizade>();
		List<Usuario> listAmigos = new ArrayList<Usuario>();
		List<String> listEmailsAmigos = new ArrayList<String>();
		List<Chat> listMensagens = new ArrayList<Chat>();
		List<Projeto> listaProjetosParticipantes = new ArrayList<Projeto>();
		List<Projeto> listaProjetosFavoritos = new ArrayList<Projeto>();
		List<Participantes> projetosParticipantes = new ArrayList<Participantes>();
		user = cadastroDao.findUsuario(usuario.getUsername());
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

		// Lista meus amigos
		listEmailsAmigos = amigosDao.listarTodosOsAmigos(usuario.getUsername());
		for (int i = 0; i < listEmailsAmigos.size(); i++) {
			listAmigos.add(cadastroDao.findUsuario(listEmailsAmigos.get(i)));
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
		model.addObject("usuarios", listUsers);
		model.addObject("listaProjeto", projetodao.listarTodosProjetos(usuario.getUsername()));
		model.addObject("usuarioFoto", user.getFoto());
		model.addObject("listaAmigos", listAmigos);
		model.addObject("projetosParticipantes", listaProjetosParticipantes);
		model.addObject("projetosFavoritos",listaProjetosFavoritos);
		return model;
		// return "amigos";
	}

	@RequestMapping("/retorna/pesquisa")
	@CacheEvict(value = "amigo", allEntries = true)
	@ResponseBody
	public List<Integer> getPesquisaUsuario(HttpServletRequest request) {
		List<Integer> ids = new ArrayList<Integer>();
		String userName = request.getParameter("nome");
		List<Usuario> usuario = cadastroDao.pesquisarUsuarioNome(userName);
		for (int i = 0; i < usuario.size(); i++) {
			ids.add(usuario.get(i).getId());
		}
		return ids;
	}

	@RequestMapping("/clear/pesquisa")
	@CacheEvict(value = "amigo", allEntries = true)
	@ResponseBody
	public List<Integer> clearPesquisaUsuario() {
		List<Integer> ids = new ArrayList<Integer>();
		List<Usuario> listUsers = new ArrayList<Usuario>();
		listUsers = cadastroDao.getAllUsers();
		for (int i = 0; i < listUsers.size(); i++) {
			ids.add(listUsers.get(i).getId());
		}
		return ids;
	}

	// Retorna o status do Usuario
	@RequestMapping("/retorna/status-usuario/amigos")
	@CacheEvict(value = "amigo", allEntries = true)
	@ResponseBody
	public String getStatusUsuario(@AuthenticationPrincipal Usuario usuario) {
		user = cadastroDao.findUsuario(usuario.getUsername());
		return user.getStatusUsuario();
	}

	@RequestMapping("/retorna/modo-noturno/amigos")
	@CacheEvict(value = "amigo", allEntries = true)
	@ResponseBody
	public boolean getModoNotuno() {
		return config.isModoNoturno();
	}
}
