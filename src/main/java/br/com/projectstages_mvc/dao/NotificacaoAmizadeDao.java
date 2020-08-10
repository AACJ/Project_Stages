package br.com.projectstages_mvc.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.projectstages_mvc.model.Amigos;
import br.com.projectstages_mvc.model.NotificacaoAmizade;

@Repository
public class NotificacaoAmizadeDao {

	@PersistenceContext
	private	EntityManager manager;
	
	public void save(NotificacaoAmizade notificacaoAmizade) {
		manager.persist(notificacaoAmizade);
	}

	public void remove(NotificacaoAmizade notificacaoAmizade) {
		manager.remove(notificacaoAmizade);
	}
	
	public void update(NotificacaoAmizade notificacaoAmizade) {
		manager.merge(notificacaoAmizade);
	}
	
	public boolean verificacaoNotificacao(String emailRemetente, String emailDestinatario) {
		String	jpql = "select a from NotificacaoAmizade a where a.emailRemetente = :emailRemetente and a.emailDestinatario = :emailDestinatario";
		List<NotificacaoAmizade> notificacoes = manager.createQuery(jpql, NotificacaoAmizade.class).setParameter("emailRemetente",emailRemetente).setParameter("emailDestinatario",emailDestinatario).getResultList();
		if(notificacoes.isEmpty()){
			return false;
		}
			return true;
	}
	
	public NotificacaoAmizade findNotificacao(String emailRemetente, String emailDestinatario ) {
		String	jpql = "select a from NotificacaoAmizade a where a.emailRemetente = :emailRemetente and a.emailDestinatario = :emailDestinatario";
		NotificacaoAmizade notificacao = manager.createQuery(jpql, NotificacaoAmizade.class).setParameter("emailRemetente",emailRemetente).setParameter("emailDestinatario",emailDestinatario).getSingleResult();
		return notificacao;
	}
	
	public List<String> listarTodosAsNotificacoes(String emailDestinatario ){
		List<String> listNotificacoes = new ArrayList<String>();
		String	jpql = "select a from NotificacaoAmizade a where a.emailDestinatario = :emailDestinatario";
		List<NotificacaoAmizade> notificacoes = manager.createQuery(jpql, NotificacaoAmizade.class).setParameter("emailDestinatario",emailDestinatario).getResultList();
		for(int i = 0; i < notificacoes.size();i++) {
			listNotificacoes.add(notificacoes.get(i).getEmailRemetente());
		}
		return listNotificacoes;
	}
	
	public List<NotificacaoAmizade> listarTodasNotificacoesDoDestinatario(String emailDestinatario ){
		String	jpql = "select a from NotificacaoAmizade a where a.emailDestinatario = :emailDestinatario";
		List<NotificacaoAmizade> notificacoes = manager.createQuery(jpql, NotificacaoAmizade.class).setParameter("emailDestinatario",emailDestinatario).getResultList();
		return notificacoes;
	}
	
	public NotificacaoAmizade findByID(int id) {
		String	jpql = "select a from NotificacaoAmizade a where a.id = :id";
		NotificacaoAmizade notificacao = manager.createQuery(jpql, NotificacaoAmizade.class).setParameter("id",id).getSingleResult();
		return notificacao;
	}
}
