package br.com.projectstages_mvc.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class NotificacaoAmizade implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String emailRemetente;
	private String emailDestinatario;
	private boolean visualizacao;
	
	public NotificacaoAmizade() {

	}

	public NotificacaoAmizade(String emailRemetente, String emailDestinatario) {
		this.emailRemetente = emailRemetente;
		this.emailDestinatario = emailDestinatario;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getEmailRemetente() {
		return emailRemetente;
	}
	
	public void setEmailRemetente(String emailRemetente) {
		this.emailRemetente = emailRemetente;
	}
	
	public String getEmailDestinatario() {
		return emailDestinatario;
	}
	
	public void setEmailDestinatario(String emailDestinatario) {
		this.emailDestinatario = emailDestinatario;
	}

	public boolean isVisualizacao() {
		return visualizacao;
	}

	public void setVisualizacao(boolean visualizacao) {
		this.visualizacao = visualizacao;
	}
	
}
