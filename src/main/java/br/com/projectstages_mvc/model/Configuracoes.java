package br.com.projectstages_mvc.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Configuracoes {

	@Id
	private String emailUsuario;
	private boolean modoNoturno;
	
	public Configuracoes() {}
	
	public Configuracoes(String EmailUsuario) {
		emailUsuario = EmailUsuario;
	}
	
	public Configuracoes(boolean ModoNoturno) {
		modoNoturno = ModoNoturno;
	}
	
	public Configuracoes(String EmailUsuario, boolean ModoNoturno) {
		emailUsuario = EmailUsuario;
		modoNoturno = ModoNoturno;
	}
	
	public String getEmailUsuario() {
		return emailUsuario;
	}
	
	public void setEmailUsuario(String emailUsuario) {
		this.emailUsuario = emailUsuario;
	}
	
	public boolean isModoNoturno() {
		return modoNoturno;
	}
	
	public void setModoNoturno(boolean modoNoturno) {
		this.modoNoturno = modoNoturno;
	}

}
