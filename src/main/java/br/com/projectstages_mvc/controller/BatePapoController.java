package br.com.projectstages_mvc.controller;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
public class BatePapoController {
	
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

	private Usuario user = new Usuario();
	private Configuracoes config = new Configuracoes();
	private Usuario amigoChat = new Usuario();
	private Date date = new Date();
	private Calendar calendario =  new GregorianCalendar(); 
	private GregorianCalendar gc = new GregorianCalendar();
	private int idDoAmigo;
	
	@RequestMapping("/bate-papo")
	@Cacheable(value = "papo")
	public ModelAndView batePapo(@AuthenticationPrincipal Usuario usuario, @RequestParam int idUsuario, @RequestParam String userName) {
		ModelAndView model = new ModelAndView("bate-papo");
		int totalMensagens = 0;
		int quantidade = 0;
		List<NotificacaoAmizade> msgNotificacoes = new ArrayList<NotificacaoAmizade>();
		List<Chat> listMensagens = chatDao.getAllMensagens(usuario.getEmail());
		user = cadastroDao.findUsuario(usuario.getUsername());
		amigoChat = cadastroDao.findById(idUsuario);
		idDoAmigo = idUsuario;
		msgNotificacoes = notificacaoDao.listarTodasNotificacoesDoDestinatario(usuario.getUsername());
		for(int i = 0 ; i < msgNotificacoes.size();i++) {
			if(msgNotificacoes.get(i).isVisualizacao() == false) {
				quantidade++;
			}
		}
		
		if(quantidade > 0) {
			model.addObject("qtnNotificacao", quantidade);
		}
		
		for(int i = 0 ; i < listMensagens.size() ;i++) {
			if(listMensagens.get(i).getEmailRemetente().equals(amigoChat.getEmail()) && listMensagens.get(i).getEmailDestinatario().equals(usuario.getEmail())) {
				listMensagens.get(i).setVisualizacao(true);
				chatDao.update(listMensagens.get(i));
			}
		}
		
		//Mostra o total de mensagens nao lidas.
		for (int j = 0; j < listMensagens.size(); j++) {
			if(listMensagens.get(j).isVisualizacao() == false && listMensagens.get(j).getEmailDestinatario().equals(usuario.getEmail())) {
				totalMensagens++;
			}
		}
		
		if (totalMensagens > 0) {
			model.addObject("totalMensagens", totalMensagens);
		}
		
		config = configuracoesDao.configuracoesDoUsuario(usuario.getUsername());
		model.addObject("listaProjeto", projetodao.listarTodosProjetos(usuario.getUsername()));
		model.addObject("usuarioFoto", user.getFoto());
		model.addObject("amigoChat", amigoChat);
		model.addObject("usuarioAtual", user);
		model.addObject("idAmigo", idUsuario);
		return model;
	}
	
	@RequestMapping("/salvar/mensagem")
	@CacheEvict(value = "papo", allEntries = true)
	@ResponseBody
	public void saveMensagem(@AuthenticationPrincipal Usuario usuario, HttpServletRequest request) {
		Time horaAtual = new Time(0);
		LocalDateTime now = LocalDateTime.now();
	
		horaAtual.setHours(date.getHours());
		horaAtual.setMinutes(date.getMinutes());
		horaAtual.setSeconds(date.getSeconds());
		
		Chat mensagem = new Chat();
		String msg = request.getParameter("msg");
		String idAmigo = request.getParameter("idAmigo");
		Usuario amigo = cadastroDao.findById(Integer.parseInt(idAmigo));
		mensagem.setEmailRemetente(usuario.getEmail());
		mensagem.setEmailDestinatario(amigo.getEmail());
		mensagem.setMensagem(msg);	
		mensagem.setHora(horaAtual);
		chatDao.save(mensagem);
	}
	
	@RequestMapping("/salvar/emoji")
	@CacheEvict(value = "papo", allEntries = true)
	@ResponseBody
	public void saveEmoji(@AuthenticationPrincipal Usuario usuario, HttpServletRequest request) {
		Time horaAtual = new Time(0);
		LocalDateTime now = LocalDateTime.now();
	
		horaAtual.setHours(date.getHours());
		horaAtual.setMinutes(date.getMinutes());
		horaAtual.setSeconds(date.getSeconds());
		
		Chat mensagem = new Chat();
		String emoji = request.getParameter("emoji");
		String idAmigo = request.getParameter("idAmigo");
		Usuario amigo = cadastroDao.findById(Integer.parseInt(idAmigo));
		mensagem.setEmailRemetente(usuario.getEmail());
		mensagem.setEmailDestinatario(amigo.getEmail());
		mensagem.setEmoji(emoji);
		mensagem.setHora(horaAtual);
		chatDao.save(mensagem);
	}
	
	@RequestMapping("/retorna/mensagem")
	@CacheEvict(value = "papo", allEntries = true)
	@ResponseBody
	public String getAllMensegers(@AuthenticationPrincipal Usuario usuario) {
		Usuario amigo = cadastroDao.findById(idDoAmigo);
		String msg = new String();
		List<String> mensagens = new ArrayList<String>();
		List<Chat> listMensagens = chatDao.getAllMensagens(usuario.getEmail());
		
		for(int i = 0; i < listMensagens.size();i++) {
			if(listMensagens.get(i).getEmailRemetente().equals(usuario.getEmail()) && listMensagens.get(i).getEmailDestinatario().equals(amigo.getEmail()) && listMensagens.get(i).getMensagem()!= null) {
				mensagens.add("<nav class='box-msg-my'><div class='my-msg'><p>" + listMensagens.get(i).getMensagem() + "</p><p class='hora-msg-my'>" + listMensagens.get(i).getHora().getHours() + ":" + listMensagens.get(i).getHora().getMinutes() +"</p></div></nav>");
			}else
			if(listMensagens.get(i).getEmailRemetente().equals(amigo.getEmail()) && listMensagens.get(i).getEmailDestinatario().equals(usuario.getEmail()) && listMensagens.get(i).getMensagem()!= null) {
				mensagens.add("<nav class='box-msg-friend'><div class='friend-msg'><p>" + listMensagens.get(i).getMensagem() + "</p><p class='hora-msg-friend'>" + listMensagens.get(i).getHora().getHours() + ":" + listMensagens.get(i).getHora().getMinutes() +"</p></div></nav>");
			}else
			if(listMensagens.get(i).getEmailRemetente().equals(usuario.getEmail()) && listMensagens.get(i).getEmailDestinatario().equals(amigo.getEmail()) && listMensagens.get(i).getEmoji()!= null) {
				mensagens.add("<nav class='box-emoji-my'><div class='img-emoji-my'><img src='" + listMensagens.get(i).getEmoji() + "'><p class='hora-msg-my'>" + listMensagens.get(i).getHora().getHours() + ":" + listMensagens.get(i).getHora().getMinutes() +"</p></div></nav>");
			}else
			if(listMensagens.get(i).getEmailRemetente().equals(amigo.getEmail()) && listMensagens.get(i).getEmailDestinatario().equals(usuario.getEmail()) && listMensagens.get(i).getEmoji()!= null) {
				mensagens.add("<nav class='box-emoji-friend'><div class='img-emoji-friend'><img src='" + listMensagens.get(i).getEmoji() + "'><p class='hora-msg-friend'>" + listMensagens.get(i).getHora().getHours() + ":" + listMensagens.get(i).getHora().getMinutes() +"</p></div></nav>");
			}
		}
		
		for(int i = 0; i < mensagens.size();i++) {
			msg += mensagens.get(i);
		}
	
		return msg;
	}
	
	@RequestMapping("/retorna/modo-noturno/bate-papo")
	@CacheEvict(value = "papo", allEntries = true)
	@ResponseBody
	public boolean getModoNotuno() {
		return config.isModoNoturno();
	}
}
