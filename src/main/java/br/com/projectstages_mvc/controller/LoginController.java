package br.com.projectstages_mvc.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.projectstages_mvc.dao.CadastroDao;
import br.com.projectstages_mvc.dao.ProjetoDao;
import br.com.projectstages_mvc.model.Projeto;
import br.com.projectstages_mvc.model.Usuario;

@Controller
@Transactional
public class LoginController {

	@Autowired
	private	CadastroDao cadastroDao;
	
	@Autowired
	private	ProjetoDao projetoDao;
	
	@RequestMapping("/login")
	public String login(){
		return "login";
	}

	@RequestMapping(value = "/login/validacao", method = RequestMethod.GET)
	@Cacheable(value="usuarios")
	public String loginValidacao(@AuthenticationPrincipal Usuario user){
		Projeto projeto = projetoDao.listarProjetosUsuario(user.getEmail(), 0);
		if(projeto == null) {
			return "redirect:/home-vazio";
		}
		
		return "redirect:/home?idProjeto=" + projeto.getId() + "&nome=" + projeto.getNome();
	}
		
}