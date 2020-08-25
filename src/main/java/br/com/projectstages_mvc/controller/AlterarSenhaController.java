package br.com.projectstages_mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.projectstages_mvc.dao.CadastroDao;
import br.com.projectstages_mvc.model.Usuario;

@Controller
@Transactional
public class AlterarSenhaController {

	@Autowired
	private CadastroDao cadastroDao;
	
	private String emailUsuario = new String();
	
	@RequestMapping(value = "/alterar-senha", method = RequestMethod.POST)
	public ModelAndView solicitacaoEmail(@RequestParam String email){
		ModelAndView model = new ModelAndView("alterar-senha");
		emailUsuario = email;
		return model;
	}
	
	@RequestMapping(value = "/alterar-senha/atualizar-senha", method = RequestMethod.POST)
	public String updatePassword(@RequestParam String novaSenha) {
		Usuario usuario = cadastroDao.findUsuario(emailUsuario);
		BCryptPasswordEncoder senha = new BCryptPasswordEncoder();
		String hasSenha = senha.encode(novaSenha);
		usuario.setSenha(hasSenha);
		
		cadastroDao.update(usuario);
		
		return "redirect:/login";
	}
}
