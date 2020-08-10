package br.com.projectstages_mvc.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.projectstages_mvc.model.Configuracoes;
import br.com.projectstages_mvc.model.Projeto;

@Repository
public class ConfiguracoesDao {

	@PersistenceContext
	private	EntityManager manager;
	
	public void save(Configuracoes configuracoes) {
		manager.persist(configuracoes);
	}

	public void update(Configuracoes configuracoes) {
		manager.merge(configuracoes);
	}
	
	public Configuracoes configuracoesDoUsuario(String email) {
		String	jpql = "select c from Configuracoes c where c.emailUsuario = :emailUsuario";
		Configuracoes configuracoes = manager.createQuery(jpql, Configuracoes.class).setParameter("emailUsuario",email).getSingleResult();
		return configuracoes;
	}
}
