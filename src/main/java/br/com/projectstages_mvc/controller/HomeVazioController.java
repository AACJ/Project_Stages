package br.com.projectstages_mvc.controller;

import java.util.ArrayList;
import java.util.List;

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
public class HomeVazioController {

	@Autowired
	private CadastroDao cadastroDao;

	@Autowired
	private NotificacaoAmizadeDao notificacaoDao;

	@Autowired
	private ProjetoDao projetodao;

	@Autowired
	private ConfiguracoesDao configuracoesDao;

	@Autowired
	private ChatDao chatDao;

	@Autowired
	private AmigosDao amigosDao;

	@Autowired
	private ParticipantesDao participantesDao;

	private Usuario user = new Usuario();
	private Configuracoes config = new Configuracoes();

	@RequestMapping("/home-vazio")
	@Cacheable(value = "vazio")
	public ModelAndView chat(@AuthenticationPrincipal Usuario usuario) {
		ModelAndView model = new ModelAndView("projetos-vazio");
		int totalMensagens = 0;
		int quantidade = 0;
		List<NotificacaoAmizade> msgNotificacoes = new ArrayList<NotificacaoAmizade>();
		List<Usuario> listAmigos = new ArrayList<Usuario>();
		List<String> listEmailsAmigos = new ArrayList<String>();
		List<Chat> listMensagens = new ArrayList<Chat>();
		List<Integer> listaqtnMensagens = new ArrayList<Integer>();
		List<Projeto> listaProjetosParticipantes = new ArrayList<Projeto>();
		List<Participantes> projetosParticipantes = new ArrayList<Participantes>();
		projetosParticipantes = participantesDao.listarProjetosParticipantes(usuario.getUsername());
		user = cadastroDao.findUsuario(usuario.getUsername());

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

		for (int i = 0; i < listAmigos.size(); i++) {
			int qtnVisualizacoes = 0;
			listMensagens = chatDao.getAllMensagens(usuario.getEmail());
			for (int j = 0; j < listMensagens.size(); j++) {
				if (listMensagens.get(j).isVisualizacao() == false
						&& listMensagens.get(j).getEmailRemetente().equals(listAmigos.get(i).getEmail())) {
					qtnVisualizacoes++;
					totalMensagens++;
				}
			}
			listaqtnMensagens.add(qtnVisualizacoes);
		}

		if (totalMensagens > 0) {
			model.addObject("totalMensagens", totalMensagens);
		}

		config = configuracoesDao.configuracoesDoUsuario(usuario.getUsername());
		model.addObject("listaProjeto", projetodao.listarTodosProjetos(usuario.getUsername()));
		model.addObject("usuarioFoto", user.getFoto());
		model.addObject("listaAmigos", listAmigos);
		model.addObject("msgNotViewed", listaqtnMensagens);
		model.addObject("projetosParticipantes", listaProjetosParticipantes);
		return model;
	}

	// Retorna o modo noturno
	@RequestMapping("/retorna/modo-noturno/home-vazio")
	@CacheEvict(value = "vazio", allEntries = true)
	@ResponseBody
	public boolean getModoNotuno() {
		return config.isModoNoturno();
	}
}
