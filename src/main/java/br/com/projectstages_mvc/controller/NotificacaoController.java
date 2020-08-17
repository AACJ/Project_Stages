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
import org.springframework.web.bind.annotation.RequestMethod;
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
import br.com.projectstages_mvc.model.Chat;
import br.com.projectstages_mvc.model.Configuracoes;
import br.com.projectstages_mvc.model.NotificacaoAmizade;
import br.com.projectstages_mvc.model.Participantes;
import br.com.projectstages_mvc.model.Projeto;
import br.com.projectstages_mvc.model.Usuario;

@Controller
@Transactional
public class NotificacaoController {
	
	@Autowired
	private CadastroDao cadastroDao;
	
	@Autowired
	private ProjetoDao projetodao;

	@Autowired
	private NotificacaoAmizadeDao notificacaoDao;
	
	@Autowired
	private ChatDao chatDao;
	
	@Autowired
	private ConfiguracoesDao configuracoesDao;
	
	@Autowired
	private AmigosDao amigosDao;
	
	@Autowired
	private ParticipantesDao participantesDao;
	
	private Usuario user = new Usuario();
	private Configuracoes config = new Configuracoes();
	
	@RequestMapping(value = "/notificacao",method = RequestMethod.GET)
	@Cacheable(value="notifis")
	public ModelAndView notificacao(@AuthenticationPrincipal Usuario usuario){
		ModelAndView model = new ModelAndView("notificacao");	
		int totalMensagens = 0;
		List<NotificacaoAmizade> msgNotificacoes = new ArrayList<NotificacaoAmizade>();
		List<Usuario> listNotificacoes = new ArrayList<Usuario>();
		List<String> listEmailsRemetente = new ArrayList<String>();
		List<Chat> listMensagens = new ArrayList<Chat>(); 
		List<Projeto> listaProjetosParticipantes = new ArrayList<Projeto>();
		List<Participantes> projetosParticipantes = new ArrayList<Participantes>();
		List<Projeto> listaProjetosFavoritos = new ArrayList<Projeto>();
		user = cadastroDao.findUsuario(usuario.getUsername());
		projetosParticipantes = participantesDao.listarProjetosParticipantes(usuario.getUsername());
		//Sistema de visualização
		msgNotificacoes = notificacaoDao.listarTodasNotificacoesDoDestinatario(usuario.getUsername());
		for(int i = 0 ; i < msgNotificacoes.size();i++) {
			msgNotificacoes.get(i).setVisualizacao(true);
			notificacaoDao.update(msgNotificacoes.get(i));
		}
		
		//Sistema de exibição de pedido de amizade
		listEmailsRemetente = notificacaoDao.listarTodosAsNotificacoes(usuario.getUsername());
		for(int i = 0; i < listEmailsRemetente.size(); i++) {
			listNotificacoes.add(cadastroDao.findUsuario(listEmailsRemetente.get(i)));
		}
		
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
		model.addObject("listaProjeto",projetodao.listarTodosProjetos(usuario.getUsername()));
		model.addObject("usuarioFoto", user.getFoto());
		model.addObject("listaNotificacoes", listNotificacoes);
		model.addObject("projetosParticipantes", listaProjetosParticipantes);
		model.addObject("projetosFavoritos",listaProjetosFavoritos);
		return model;
	}
	
	@RequestMapping(value = "/remove/destinatario/notificacao",method = RequestMethod.POST)
	@CacheEvict(value="notifis", allEntries=true)
	public String removeNotificacao(@AuthenticationPrincipal Usuario usuario, @RequestParam String email) {
		System.out.println(email);
		NotificacaoAmizade notificacao = notificacaoDao.findNotificacao(email,usuario.getEmail());
		System.out.println(notificacao.getEmailDestinatario());
		notificacaoDao.remove(notificacao);
		return "redirect:/notificacao";
	}
	
	@RequestMapping("/verificacao/notificacao/amigo")
	@CacheEvict(value = "amigosPerfils", allEntries=true)
	@ResponseBody
	public List<Boolean> verfificacaoAmigo(@AuthenticationPrincipal Usuario usuario) {
		List<NotificacaoAmizade> msgNotificacoes = new ArrayList<NotificacaoAmizade>();
		List<Boolean> amizades = new ArrayList<Boolean>();
		msgNotificacoes = notificacaoDao.listarTodasNotificacoesDoDestinatario(usuario.getUsername());
		for(int i = 0 ; i < msgNotificacoes.size();i++) {
			amizades.add(amigosDao.verificacaoDeAmizade(usuario.getUsername(), msgNotificacoes.get(i).getEmailRemetente()));
		}
		
		return amizades;
	}
	
}
