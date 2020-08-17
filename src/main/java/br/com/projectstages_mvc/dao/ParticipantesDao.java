package br.com.projectstages_mvc.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.projectstages_mvc.model.Participantes;
import br.com.projectstages_mvc.model.Usuario;

@Repository
public class ParticipantesDao {

	@PersistenceContext
	private	EntityManager manager;
	
	public void save(Participantes participantes){
		manager.persist(participantes);
	}
	
	public void update(Participantes participantes){
		manager.merge(participantes);
	}
	
	public void delete(Participantes participantes){
		manager.remove(participantes);
	}
	
	public List<String> listarTodosOsPartcipantesDoProjeto(int idProjeto){
		List<String> listEmails = new ArrayList<String>();
		String	jpql = "select a from Participantes a where a.idProjeto = :idProjeto";
		List<Participantes> listParticipantes = manager.createQuery(jpql, Participantes.class).setParameter("idProjeto",idProjeto).getResultList();
		for(int i = 0; i< listParticipantes.size(); i++) {
			listEmails.add(listParticipantes.get(i).getEmailParticipante());
		}
		
		return listEmails;
	}
	
	public Participantes findParticipante(int idProjeto,String emailParticipante) {
		String	jpql = "select a from Participantes a where a.idProjeto = :idProjeto and a.emailParticipante = :emailParticipante";
		Participantes participantes = manager.createQuery(jpql, Participantes.class).setParameter("idProjeto",idProjeto).setParameter("emailParticipante", emailParticipante).getSingleResult();
		return participantes;
	}
	
	public List<String> listarFuncoesDosPartcipantes(int idProjeto){
		List<String> listFuncoes = new ArrayList<String>();
		String	jpql = "select a from Participantes a where a.idProjeto = :idProjeto";
		List<Participantes> listParticipantes = manager.createQuery(jpql, Participantes.class).setParameter("idProjeto",idProjeto).getResultList();
		for(int i = 0; i< listParticipantes.size(); i++) {
			listFuncoes.add(listParticipantes.get(i).getFuncao());
		}
		
		return listFuncoes;
	}
	
	public List<String> pesquisarPorFuncao(int idProjeto,String funcao){
		List<String> listEmail = new ArrayList<String>();
		String	jpql = "select a from Participantes a where a.idProjeto = :idProjeto and a.funcao = :funcao";
		List<Participantes> listParticipantes = manager.createQuery(jpql, Participantes.class).setParameter("idProjeto",idProjeto).setParameter("funcao",funcao).getResultList();
		for(int i = 0; i< listParticipantes.size(); i++) {
			listEmail.add(listParticipantes.get(i).getEmailParticipante());
		}
		
		return listEmail;
	}
	
	public List<Participantes> listarProjetosParticipantes(String emailParticipante) {
		String	jpql = "select a from Participantes a where a.emailParticipante = :emailParticipante";
		List<Participantes> participantes = manager.createQuery(jpql, Participantes.class).setParameter("emailParticipante", emailParticipante).getResultList();
		if(participantes.isEmpty()) {
			return null;
		}
		
		return participantes;
	}
	
	public List<Participantes> listAllParticipantesDoProjeto(int idProjeto) {
		String	jpql = "select a from Participantes a where a.idProjeto = :idProjeto";
		List<Participantes> participantes = manager.createQuery(jpql, Participantes.class).setParameter("idProjeto",idProjeto).getResultList();
		if(participantes.isEmpty()) {
			return null;
		}
		
		return participantes;
	}
	
}
