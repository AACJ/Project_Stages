package br.com.projectstages_mvc.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.projectstages_mvc.model.Concluidos;
import br.com.projectstages_mvc.model.Desenvolvimentos;
import br.com.projectstages_mvc.model.Projeto;
import br.com.projectstages_mvc.model.Tarefas;

@Repository
public class ProjetoDao {

	@PersistenceContext
	private	EntityManager manager;
	
	public void save(Projeto projeto){
		manager.persist(projeto);
	}
	
	//Tarefas
	public void saveTarefas(Tarefas tarefas) {
		manager.persist(tarefas);
	}
	
	public void updateTarefas(Tarefas tarefas) {
		manager.merge(tarefas);
	}
	
	public void deletarTarefa(int id) {
		String	jpql = "select t from Tarefas t where t.id = :id";
		Tarefas tarefa = manager.createQuery(jpql, Tarefas.class).setParameter("id",id).getSingleResult();
		manager.remove(tarefa);
	}
	
	//Desenvolvimentos
	public void saveDesenvolvimentos(Desenvolvimentos Desenvolvimentos) {
		manager.persist(Desenvolvimentos);
	}
	
	public void updateDesenvolvimentos(Desenvolvimentos Desenvolvimentos) {
		manager.merge(Desenvolvimentos);
	}
	
	public void deletarDesenvolvimentos(int id) {
		String	jpql = "select t from Desenvolvimentos t where t.id = :id";
		Desenvolvimentos Desenvolvimentos = manager.createQuery(jpql, Desenvolvimentos.class).setParameter("id",id).getSingleResult();
		manager.remove(Desenvolvimentos);
	}
	
	//Concluidos
	public void saveConcluidos(Concluidos Concluidos) {
		manager.persist(Concluidos);
	}
	
	public void updateConcluidos(Concluidos Concluidos) {
		manager.merge(Concluidos);
	}
	
	public void deletarConcluidos(int id) {
		String	jpql = "select t from Concluidos t where t.id = :id";
		Concluidos Concluidos = manager.createQuery(jpql, Concluidos.class).setParameter("id",id).getSingleResult();
		manager.remove(Concluidos);
	}
	
	public Projeto listarProjetosUsuario(String email,int i) {
		String	jpql = "select p from Projeto p where p.emailUsuario = :emailUsuario";
		List<Projeto> projetos = manager.createQuery(jpql, Projeto.class).setParameter("emailUsuario",email).getResultList();
		return projetos.get(i);
	}
	
	public Projeto listarProjetosUsuarioPorID(String email,int id) {
		String	jpql = "select p from Projeto p where p.emailUsuario = :emailUsuario";
		List<Projeto> projetos = manager.createQuery(jpql, Projeto.class).setParameter("emailUsuario",email).getResultList();
		for(int i = 0; i < projetos.size(); i++) {
			if(projetos.get(i).getId() == id) {
				return projetos.get(i);
			}
		}
		return null;
	}
	
	public Projeto listarProjetosParticipantePorID(int id) {
		String	jpql = "select p from Projeto p where p.id = :id";
		List<Projeto> projeto = manager.createQuery(jpql, Projeto.class).setParameter("id",id).getResultList();
		if(projeto.isEmpty()) {
			return null;
		}
		return projeto.get(0);
	}
	
	public List<Projeto> listarTodosProjetos(String email) {
		String	jpql = "select p from Projeto p where p.emailUsuario = :emailUsuario";
		List<Projeto> projetos = manager.createQuery(jpql, Projeto.class).setParameter("emailUsuario",email).getResultList();
		return projetos;
	}
	
	//Tarefas
	public List<Tarefas> listarTarefas(int id){
		String	jpql = "select t from Tarefas t where t.idProjeto = :idProjeto";
		List<Tarefas> tarefas = manager.createQuery(jpql, Tarefas.class).setParameter("idProjeto",id).getResultList();
		return tarefas;	
	}
	
	public List<String> listarStatusTarefas(int id){
		String	jpql = "select t from Tarefas t where t.idProjeto = :idProjeto";
		List<Tarefas> tarefas = manager.createQuery(jpql, Tarefas.class).setParameter("idProjeto",id).getResultList();
		List<String> listarStatus = new ArrayList<String>();
		for(int i = 0; i < tarefas.size();i++) {
			listarStatus.add(tarefas.get(i).getStatusProjeto());
		}
		return listarStatus;
	}
	
	public Tarefas foundTarefa(int id) {
		String	jpql = "select t from Tarefas t where t.id = :id";
		Tarefas tarefa = manager.createQuery(jpql, Tarefas.class).setParameter("id",id).getSingleResult();
		return tarefa;
	}
	
	//Desenvolvimentos
	public List<Desenvolvimentos> listarDesenvolvimentos(int id){
		String	jpql = "select t from Desenvolvimentos t where t.idProjeto = :idProjeto";
		List<Desenvolvimentos> desenvolvimentos = manager.createQuery(jpql, Desenvolvimentos.class).setParameter("idProjeto",id).getResultList();
		return desenvolvimentos;	
	}
	
	public List<String> listarStatusDesenvolvimentos(int id){
		String	jpql = "select t from Desenvolvimentos t where t.idProjeto = :idProjeto";
		List<Desenvolvimentos> desenvolvimentos = manager.createQuery(jpql, Desenvolvimentos.class).setParameter("idProjeto",id).getResultList();
		List<String> listarStatus = new ArrayList<String>();
		for(int i = 0; i < desenvolvimentos.size();i++) {
			listarStatus.add(desenvolvimentos.get(i).getStatusProjeto());
		}
		return listarStatus;
	}
	
	public Desenvolvimentos foundDesenvolvimentos(int id) {
		String	jpql = "select t from Desenvolvimentos t where t.id = :id";
		Desenvolvimentos desenvolvimentos = manager.createQuery(jpql, Desenvolvimentos.class).setParameter("id",id).getSingleResult();
		return desenvolvimentos;
	}
	
	//Concluidos
	public List<Concluidos> listarConcluidos(int id){
		String	jpql = "select t from Concluidos t where t.idProjeto = :idProjeto";
		List<Concluidos> concluidos = manager.createQuery(jpql, Concluidos.class).setParameter("idProjeto",id).getResultList();
		return concluidos;	
	}
	
	public List<String> listarStatusConcluidos(int id){
		String	jpql = "select t from Concluidos t where t.idProjeto = :idProjeto";
		List<Concluidos> concluidos = manager.createQuery(jpql, Concluidos.class).setParameter("idProjeto",id).getResultList();
		List<String> listarStatus = new ArrayList<String>();
		for(int i = 0; i < concluidos.size();i++) {
			listarStatus.add(concluidos.get(i).getStatusProjeto());
		}
		return listarStatus;
	}
	
	public Concluidos foundConcluidos(int id) {
		String	jpql = "select t from Concluidos t where t.id = :id";
		Concluidos concluidos = manager.createQuery(jpql, Concluidos.class).setParameter("id",id).getSingleResult();
		return concluidos;
	}
}
