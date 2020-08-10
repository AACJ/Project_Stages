package br.com.projectstages_mvc.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.projectstages_mvc.dao.CadastroDao;
import br.com.projectstages_mvc.dao.ParticipantesDao;
import br.com.projectstages_mvc.dao.ProjetoDao;
import br.com.projectstages_mvc.model.Participantes;
import br.com.projectstages_mvc.model.Projeto;
import br.com.projectstages_mvc.model.Usuario;

@Controller	
@Transactional
public class CadastroController {
	
	@Autowired
	private	CadastroDao	cadastroDao;
	
	@Autowired
	private ProjetoDao projetodao;
	
	@Autowired
	private ParticipantesDao participantesDao;
	
	private Usuario user = new Usuario();
		
	@RequestMapping("/cadastro")
	public String cadastro(){
		return "cadastro";
	}	
	
	@RequestMapping(value = "/cadastro/pos-cadastro", method = RequestMethod.POST)
	public String save(Usuario usuario){
		System.out.println("Passou");
		user = usuario;
		cadastroDao.save(usuario);
		//ModelAndView view = new ModelAndView("/pos-cadastro");
		//return "redirect:/pos-cadastro";
		return "redirect:/pos-cadastro";
		
	}
				
	@RequestMapping("/pos-cadastro")
	public String posCadastro() {
		return "pos-cadastro";
	}
	
	@RequestMapping(value ="/pos-cadastro/home", method = RequestMethod.POST)
	public String cadastroProjeto(Projeto projeto) {
		projeto.setEmailUsuario(user.getUsername());
		projetodao.save(projeto);
		
		Participantes participante = new Participantes();
		participante.setEmailParticipante(user.getUsername());
		participante.setIdProjeto(projeto.getId());
		participante.setFuncao("criador");
		participantesDao.save(participante);
		
		return "redirect:/login";
	}
}
