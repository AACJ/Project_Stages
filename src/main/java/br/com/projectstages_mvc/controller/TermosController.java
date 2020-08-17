package br.com.projectstages_mvc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.projectstages_mvc.model.Chat;
import br.com.projectstages_mvc.model.NotificacaoAmizade;
import br.com.projectstages_mvc.model.Participantes;
import br.com.projectstages_mvc.model.Projeto;
import br.com.projectstages_mvc.model.Usuario;

@Controller
public class TermosController {
	
	@RequestMapping("/termos-de-uso")
	public ModelAndView chat(@AuthenticationPrincipal Usuario usuario) {
		ModelAndView model = new ModelAndView("termosUso");
		return model;
	}
}
