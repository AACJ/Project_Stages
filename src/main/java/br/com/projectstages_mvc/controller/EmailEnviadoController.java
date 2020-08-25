package br.com.projectstages_mvc.controller;

import javax.transaction.Transactional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Transactional
public class EmailEnviadoController {

	@RequestMapping(value = "/email-enviado", method = RequestMethod.GET)
	public ModelAndView emailEncontrado(@RequestParam String email){
		ModelAndView model = new ModelAndView("email-encontrado");
		model.addObject("email", email);
		return model;
	}
	
}
