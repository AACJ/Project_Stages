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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import br.com.projectstages_mvc.config.FileSaver;
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
public class PerfilController {

	@Autowired
	private CadastroDao cadastroDao;

	@Autowired
	private NotificacaoAmizadeDao notificacaoDao;
	
	@Autowired
	private ChatDao chatDao;
	
	@Autowired
	private ProjetoDao projetodao;

	@Autowired
	private ConfiguracoesDao configuracoesDao;

	@Autowired
	private ParticipantesDao participantesDao;
	
	@Autowired
	private FileSaver fileSaver;

	private Usuario user = new Usuario();
	private Configuracoes config = new Configuracoes();

	@RequestMapping("/perfil")
	@Cacheable(value = "perfils")
	public ModelAndView perfil(@AuthenticationPrincipal Usuario usuario) {
		ModelAndView model = new ModelAndView("perfil");
		int totalMensagens = 0;
		int quantidade = 0;
		List<NotificacaoAmizade> msgNotificacoes = new ArrayList<NotificacaoAmizade>();
		List<Chat> listMensagens = new ArrayList<Chat>(); 
		List<Projeto> listaProjetosParticipantes = new ArrayList<Projeto>();
		List<Participantes> projetosParticipantes = new ArrayList<Participantes>();
		List<Projeto> listaProjetosFavoritos = new ArrayList<Projeto>();
		user = cadastroDao.findUsuario(usuario.getUsername());
		projetosParticipantes = participantesDao.listarProjetosParticipantes(usuario.getUsername());
		msgNotificacoes = notificacaoDao.listarTodasNotificacoesDoDestinatario(usuario.getUsername());
		for(int i = 0 ; i < msgNotificacoes.size();i++) {
			if(msgNotificacoes.get(i).isVisualizacao() == false) {
				quantidade++;
			}
		}
		
		if(quantidade > 0) {
			model.addObject("qtnNotificacao", quantidade);
		}
		
		//Ajusta a data de aniversario.
		if (user.getAniversario() != null) {
			Date ajustaData = new Date(user.getAniversario().getTime());
			int dia = user.getAniversario().getDate() + 1;
			ajustaData.setDate(dia);
			model.addObject("usuarioAniversario", ajustaData);
		} else {
			model.addObject("usuarioAniversario", user.getAniversario());
		}
		
		//Mostra o total de mensagens nao lidas.
		listMensagens = chatDao.getAllMensagens(usuario.getEmail());
		for (int j = 0; j < listMensagens.size(); j++) {
			if(listMensagens.get(j).isVisualizacao() == false && listMensagens.get(j).getEmailDestinatario().equals(usuario.getEmail())) {
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
		model.addObject("usuarioFoto", user.getFoto());
		model.addObject("projetosParticipantes", listaProjetosParticipantes);
		model.addObject("projetosFavoritos",listaProjetosFavoritos);
		return model;
		// return "perfil";
	}

	@RequestMapping(value = "/atualizar/perfil-meu-usuario", method = RequestMethod.GET)
	@CacheEvict(value = "perfils", allEntries = true)
	public String updatePerfil(Usuario userUpdate) {
		user.setEmailContato(userUpdate.getEmailContato());
		user.setSkype(userUpdate.getSkype());
		user.setBiografia(userUpdate.getBiografia());
		user.setCelular(userUpdate.getCelular());
		user.setHorario(userUpdate.getHorario());
		user.setLocalizacao(userUpdate.getLocalizacao());
		user.setAniversario(userUpdate.getAniversario());
		cadastroDao.update(user);
		return "redirect:/perfil";
	}
	
	@RequestMapping(value = "/atualizar/perfil-userName-meu-usuario")
	@CacheEvict(value = "perfils", allEntries = true)
	public String updateNomePerfilUsuario(@AuthenticationPrincipal Usuario usuario, HttpServletRequest request) {
		String userName = request.getParameter("userName");
		usuario.setUserName(userName);
		cadastroDao.update(usuario);
		return "redirect:/perfil";
	}
	
	@RequestMapping(value = "/remover/foto-perfil-meu-usuario")
	@CacheEvict(value = "perfils", allEntries = true)
	public String removeFotoPerfil(@AuthenticationPrincipal Usuario usuario) {
		usuario.setFoto(null);
		cadastroDao.update(usuario);
		return "redirect:/perfil";
	}

	@RequestMapping(value = "/salvar/foto-perfil", method = RequestMethod.POST)
	@CacheEvict(value = "perfils", allEntries = true)
	public String salvarFotoPerfil(@RequestParam MultipartFile foto) {
		System.out.println("FOTOS");
		String webPath = fileSaver.write("uploads", foto);
		user.setFoto(webPath);
		cadastroDao.update(user);
		return "redirect:/perfil";
	}

	// Retorna o status do Usuario
	@RequestMapping("/retorna/status-usuario/perfil")
	@CacheEvict(value = "perfils", allEntries = true)
	@ResponseBody
	public String getStatusUsuario(@AuthenticationPrincipal Usuario usuario) {
		user = cadastroDao.findUsuario(usuario.getUsername());
		return user.getStatusUsuario();
	}

	@RequestMapping("/retorna/modo-noturno/perfil")
	@CacheEvict(value = "perfils", allEntries = true)
	@ResponseBody
	public boolean getModoNotuno() {
		return config.isModoNoturno();
	}
}
