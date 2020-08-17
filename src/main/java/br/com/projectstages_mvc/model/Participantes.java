package br.com.projectstages_mvc.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Participantes {
	 
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private int idProjeto;
	private String emailParticipante;
	private String funcao;
	private boolean projetoFavorito;
	
	public Participantes() {
	}

	public Participantes(int idProjeto, String emailParticipante) {
		this.idProjeto = idProjeto;
		this.emailParticipante = emailParticipante;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdProjeto() {
		return idProjeto;
	}
	
	public void setIdProjeto(int idProjeto) {
		this.idProjeto = idProjeto;
	}
	
	public String getEmailParticipante() {
		return emailParticipante;
	}
	
	public void setEmailParticipante(String emailParticipante) {
		this.emailParticipante = emailParticipante;
	}

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

	public boolean isProjetoFavorito() {
		return projetoFavorito;
	}

	public void setProjetoFavorito(boolean projetoFavorito) {
		this.projetoFavorito = projetoFavorito;
	}
	
}
