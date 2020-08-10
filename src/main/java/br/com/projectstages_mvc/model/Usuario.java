package br.com.projectstages_mvc.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class Usuario implements Serializable,UserDetails{
	private static final long serialVersionUID = 1L;

	@Id
	private String email;
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String nome;
	private String userName;
	private String senha;
	private String statusUsuario;
	private String emailContato;
	private String skype;
	private String biografia;
	private String celular;
	private String horario;
	private String localizacao;
	private Date aniversario;
	private String foto;
	@ManyToMany(fetch =	FetchType.EAGER)
	private	List<Role> roles = new ArrayList<Role>();
	
	public Usuario() {}
	
	public Usuario(String Email , String Senha) {
		email = Email;
		senha = Senha;
	}
	
	public Usuario(String email, String nome, String userName, String senha) {
		this.email = email;
		this.nome = nome;
		this.userName = userName;
		this.senha = senha;
	}

	public Usuario(String email, String nome, String userName, String senha, String statusUsuario) {
		this.email = email;
		this.nome = nome;
		this.userName = userName;
		this.senha = senha;
		this.statusUsuario = statusUsuario;
	}

	public Usuario(int id, String emailContato, String skype, String biografia, String celular, String horario,
			String localizacao, Date aniversario) {
		this.id = id;
		this.emailContato = emailContato;
		this.skype = skype;
		this.biografia = biografia;
		this.celular = celular;
		this.horario = horario;
		this.localizacao = localizacao;
		this.aniversario = aniversario;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getNomeUsuario() {
		return userName;
	}
	
	public void setNomeUsuario(String userName) {
		this.userName = userName;
	}
	
	public String getNome() {
		return nome;
	}
		
	public void setNome(String nomeCompleto) {
		this.nome = nomeCompleto;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getStatusUsuario() {
		return statusUsuario;
	}

	public void setStatusUsuario(String statusUsuario) {
		this.statusUsuario = statusUsuario;
	}

	public String getEmailContato() {
		return emailContato;
	}

	public void setEmailContato(String emailContato) {
		this.emailContato = emailContato;
	}

	public String getSkype() {
		return skype;
	}

	public void setSkype(String skype) {
		this.skype = skype;
	}

	public String getBiografia() {
		return biografia;
	}

	public void setBiografia(String biografia) {
		this.biografia = biografia;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public String getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}

	public Date getAniversario() {
		return aniversario;
	}

	public void setAniversario(Date Aniversario) {
		aniversario = Aniversario;
	}
	
	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nome=" + nome + ", userName=" + userName + ", email=" + email + ", senha="
				+ senha + ", roles=" + roles + "]";
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return roles;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return senha;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
		
}
