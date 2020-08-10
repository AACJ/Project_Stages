package br.com.projectstages_mvc.model;



import java.sql.Time;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Chat {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String emailRemetente;
	private String emailDestinatario;
	private String mensagem;
	private String emoji;
	private Time hora;
	private boolean visualizacao;
	
	public Chat() {
	}

	public Chat(String emailRemetente, String emailDestinatario, String mensagem) {
		this.emailRemetente = emailRemetente;
		this.emailDestinatario = emailDestinatario;
		this.mensagem = mensagem;
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
	
	public String getMensagem() {
		return mensagem;
	}
	
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getEmoji() {
		return emoji;
	}

	public void setEmoji(String emoji) {
		this.emoji = emoji;
	}

	public Time getHora() {
		return hora;
	}

	public void setHora(Time hora) {
		this.hora = hora;
	}

	public boolean isVisualizacao() {
		return visualizacao;
	}

	public void setVisualizacao(boolean visualizacao) {
		this.visualizacao = visualizacao;
	}
}
