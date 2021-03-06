package br.com.projectstages_mvc.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Desenvolvimentos implements Serializable{
	private static final long serialVersionUID = 1L; 

	@Id
	private int id;
	private int idProjeto;
	private String nome;
	private String emailProprietario;
	private String statusProjeto;
	private Date prazo;
	
	public Desenvolvimentos(){
		
	}
	
	public Desenvolvimentos(int Id){
		id = Id;
	}
	
	public Desenvolvimentos(int Id, int IdProjeto, String Nome, String StatusProjeto, Date Prazo){
		id = Id;
		idProjeto = IdProjeto;
		nome = Nome;
		statusProjeto = StatusProjeto;
		prazo = Prazo;
	}
	
	public Desenvolvimentos(String Nome, String StatusProjeto, Date Prazo){
		nome = Nome;
		statusProjeto = StatusProjeto;
		prazo = Prazo;
	}
	
	public Desenvolvimentos(String Nome, Date Prazo){
		nome = Nome;
		prazo = Prazo;
	}
	
	public Desenvolvimentos(String Nome, String StatusProjeto){
		nome = Nome;
		statusProjeto = StatusProjeto;
	}
		
	public Desenvolvimentos(String StatusProjeto) {
		statusProjeto = StatusProjeto; 
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
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmailProprietario() {
		return emailProprietario;
	}

	public void setEmailProprietario(String emailProprietario) {
		this.emailProprietario = emailProprietario;
	}

	public String getStatusProjeto() {
		return statusProjeto;
	}

	public void setStatusProjeto(String statusProjeto) {
		this.statusProjeto = statusProjeto;
	}

	public Date getPrazo() {
		return prazo;
	}

	public void setPrazo(Date prazo) {
		this.prazo = prazo;
	}
	
}
