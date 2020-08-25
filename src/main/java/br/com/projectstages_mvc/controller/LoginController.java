package br.com.projectstages_mvc.controller;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
public class LoginController {

	@Autowired
	private	CadastroDao cadastroDao;
	
	@Autowired
	private	ProjetoDao projetoDao;
	
	@Autowired
	private ParticipantesDao participantesDao;
	
	@RequestMapping("/login")
	public String login(){
		return "login";
	}

	@RequestMapping(value = "/login/validacao", method = RequestMethod.GET)
	@Cacheable(value="usuarios")
	public String loginValidacao(@AuthenticationPrincipal Usuario user){
		List<Projeto> listaProjetosParticipantes = new ArrayList<Projeto>();
		List<Participantes> projetosParticipantes = new ArrayList<Participantes>();
		projetosParticipantes = participantesDao.listarProjetosParticipantes(user.getUsername());
		
		if(projetosParticipantes == null) {
			return "redirect:/home-vazio";
		}else {
			for (int i = 0; i < projetosParticipantes.size(); i++) {
				listaProjetosParticipantes
						.add(projetoDao.listarProjetosParticipantePorID(projetosParticipantes.get(i).getIdProjeto()));
			}
		}
			Projeto projeto = listaProjetosParticipantes.get(0);
		
		return "redirect:/home?idProjeto=" + projeto.getId() + "&nome=" + projeto.getNome();
	}
		
}