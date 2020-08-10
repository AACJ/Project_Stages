package br.com.projectstages_mvc.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;

@Entity
public class Role implements GrantedAuthority {
	
	@Id
	private String name;

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public	String	getAuthority() {
		return	name;
	}

}