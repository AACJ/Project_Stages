package br.com.projectstages_mvc.model;



import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Amigos implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String emailUsuario;
	private String emailAmigo;
	
	public Amigos() {

	}
	
	public Amigos(String emailUsuario, String emailAmigo) {
		this.emailUsuario = emailUsuario;
		this.emailAmigo = emailAmigo;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmailUsuario() {
		return emailUsuario;
	}
	
	public void setEmailUsuario(String emailUsuario) {
		this.emailUsuario = emailUsuario;
	}
	
	public String getEmailAmigo() {
		return emailAmigo;
	}
	
	public void setEmailAmigo(String emailAmigo) {
		this.emailAmigo = emailAmigo;
	}
	
	
}

