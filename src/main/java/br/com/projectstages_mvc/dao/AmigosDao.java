package br.com.projectstages_mvc.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.projectstages_mvc.model.Amigos;
import br.com.projectstages_mvc.model.Usuario;

@Repository
public class AmigosDao {

	@PersistenceContext
	private	EntityManager manager;
	
	public void save(Amigos amigos) {
		manager.persist(amigos);
	}

	public void remove(Amigos amigos) {
		manager.remove(amigos);
	}
	
	public void update(Amigos amigos) {
		manager.merge(amigos);
	}
	
	public boolean verificacaoDeAmizade(String emailUser, String emailAmigo ) {
		String	jpql = "select a from Amigos a where a.emailUsuario = :emailUsuario and a.emailAmigo = :emailAmigo";
		List<Amigos> amigos = manager.createQuery(jpql, Amigos.class).setParameter("emailUsuario",emailUser).setParameter("emailAmigo",emailAmigo).getResultList();
		if(amigos.isEmpty()){
			return false;
		}
			return true;
	}
	
	public Amigos findAmigo(String emailUser, String emailAmigo ) {
		String	jpql = "select a from Amigos a where a.emailUsuario = :emailUsuario and a.emailAmigo = :emailAmigo";
		Amigos amigos = manager.createQuery(jpql, Amigos.class).setParameter("emailUsuario",emailUser).setParameter("emailAmigo",emailAmigo).getSingleResult();
		return amigos;
	}
	
	public List<String> listarTodosOsAmigos(String emailUser){
		List<String> listAmigos = new ArrayList<String>();
		String	jpql = "select a from Amigos a where a.emailUsuario = :emailUsuario";
		List<Amigos> amigos = manager.createQuery(jpql, Amigos.class).setParameter("emailUsuario",emailUser).getResultList();
		for(int i = 0; i < amigos.size();i++) {
			System.out.println(amigos.get(i).getEmailAmigo());
			listAmigos.add(amigos.get(i).getEmailAmigo());
			System.out.println(listAmigos.get(i));
		}
		return listAmigos;
	}
}

