package br.com.projectstages_mvc.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Projeto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String emailUsuario;
	private String nome;
	private String tarefa;
	private String desenvolvimento;
	private String concluido;
	
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
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getTarefa() {
		return tarefa;
	}
	
	public void setTarefa(String tarefa) {
		this.tarefa = tarefa;
	}
	
	public String getDesenvolvimento() {
		return desenvolvimento;
	}
	
	public void setDesenvolvimento(String desenvolvimento) {
		this.desenvolvimento = desenvolvimento;
	}
	
	public String getConcluido() {
		return concluido;
	}
	
	public void setConcluido(String concluido) {
		this.concluido = concluido;
	}

	@Override
	public String toString() {
		return "ProjetoDao [id=" + id + ", idUsuario=" + emailUsuario + ", nome=" + nome + ", tarefa=" + tarefa
				+ ", desenvolvimento=" + desenvolvimento + ", concluido=" + concluido + "]";
	}
}
