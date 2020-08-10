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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.projectstages_mvc.dao.CadastroDao;
import br.com.projectstages_mvc.dao.ChatDao;
import br.com.projectstages_mvc.dao.ConfiguracoesDao;
import br.com.projectstages_mvc.dao.NotificacaoAmizadeDao;
import br.com.projectstages_mvc.dao.ProjetoDao;
import br.com.projectstages_mvc.model.Chat;
import br.com.projectstages_mvc.model.Configuracoes;
import br.com.projectstages_mvc.model.NotificacaoAmizade;
import br.com.projectstages_mvc.model.Usuario;

@Controller
@Transactional
public class ConfiguracoesController {

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

	private Usuario user = new Usuario();
	private Configuracoes config = new Configuracoes();

	@RequestMapping("/configuracoes")
	@Cacheable(value = "configs")
	public ModelAndView configuracoes(@AuthenticationPrincipal Usuario usuario) {
		ModelAndView model = new ModelAndView("configuracoes");
		int totalMensagens = 0;
		int quantidade = 0;
		List<NotificacaoAmizade> msgNotificacoes = new ArrayList<NotificacaoAmizade>();
		List<Chat> listMensagens = new ArrayList<Chat>();
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

		config = configuracoesDao.configuracoesDoUsuario(usuario.getUsername());
		model.addObject("listaProjeto", projetodao.listarTodosProjetos(usuario.getUsername()));
		model.addObject("nomeUsuario", usuario.getUserName());
		model.addObject("usuarioFoto", user.getFoto());
		return model;
		// return "configuracoes";
	}

	@RequestMapping(value = "/salvar/modo-noturno", method = RequestMethod.POST)
	@CacheEvict(value = "configs", allEntries = true)
	public String salvarConfiguracaoModoNoturno(@AuthenticationPrincipal Usuario usuario, Configuracoes configuracoes) {
		configuracoes.setEmailUsuario(usuario.getUsername());
		configuracoesDao.update(configuracoes);
		return "redirect:/configuracoes";
	}

	// Retorna o status do Usuario
	@RequestMapping("/retorna/status-usuario/configuracoes")
	@CacheEvict(value = "configs", allEntries = true)
	@ResponseBody
	public String getStatusUsuario(@AuthenticationPrincipal Usuario usuario) {
		user = cadastroDao.findUsuario(usuario.getUsername());
		return user.getStatusUsuario();
	}

	@RequestMapping("/retorna/modo-noturno")
	@CacheEvict(value = "configs", allEntries = true)
	@ResponseBody
	public boolean getModoNotuno() {
		return config.isModoNoturno();
	}

}
