package br.com.projectstages_mvc.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.projectstages_mvc.model.Chat;
import br.com.projectstages_mvc.model.NotificacaoAmizade;

@Repository
public class ChatDao {

	@PersistenceContext
	private	EntityManager manager;
	
	public void save(Chat chat) {
		manager.persist(chat);
	}

	public void remove(Chat chat) {
		manager.remove(chat);
	}
	
	public void update(Chat chat) {
		manager.merge(chat);
	}
	
	public List<Chat> getAllMensagens(String email) {
		String	jpql = "select a from Chat a where a.emailRemetente = :emailRemetente or a.emailDestinatario = :emailDestinatario";
		List<Chat> listChat = manager.createQuery(jpql, Chat.class).setParameter("emailRemetente",email).setParameter("emailDestinatario",email).getResultList();
		return listChat;
	}
}
