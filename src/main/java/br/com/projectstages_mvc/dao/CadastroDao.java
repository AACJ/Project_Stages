package br.com.projectstages_mvc.dao;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import br.com.projectstages_mvc.model.Login;
import br.com.projectstages_mvc.model.Role;
import br.com.projectstages_mvc.model.Usuario;

@Repository	
public class CadastroDao implements	UserDetailsService{

	@PersistenceContext
	private	EntityManager manager;
	
	public void save(Usuario usuario){  
			BCryptPasswordEncoder senha = new BCryptPasswordEncoder();
			String hasSenha = senha.encode(usuario.getPassword());
			usuario.setSenha(hasSenha);
	        /*manager.createNativeQuery("insert into Role (name) values (?)")
	        .setParameter(1, "ROLE_ADMIN")
	        .executeUpdate();*/
	        List<Role> list = new ArrayList<Role>();
	        Role role = new Role();
	        role.setName("ROLE_ADMIN");
	        list.add(role);
	        usuario.setRoles(list);
	        
	        usuario.setStatusUsuario("Disponivel");
	        
	        //Inserte Usuario
	        manager.createNativeQuery("insert into Usuario (nome,userName,email,senha,statusUsuario) values (?,?,?,?,?)")
	        .setParameter(1, usuario.getNome())
	        .setParameter(2, usuario.getNomeUsuario())
	        .setParameter(3, usuario.getEmail())
	        .setParameter(4, usuario.getPassword())
	        .setParameter(5, usuario.getStatusUsuario())
	        .executeUpdate();
	        
	        //Inserte Usuario Role
	        manager.createNativeQuery("insert into Usuario_Role (Usuario_email,roles_name) values (?,?)")
	        .setParameter(1, usuario.getEmail())
	        .setParameter(2, "ROLE_ADMIN")
	        .executeUpdate();
	        
	        //Inserte Default Configurações
	        manager.createNativeQuery("insert into Configuracoes (emailUsuario,modoNoturno) values (?,?)")
	        .setParameter(1, usuario.getEmail())
	        .setParameter(2, false)
	        .executeUpdate();
	}
	
	public void update(Usuario usuario) {
		manager.merge(usuario);
	}
	
	public Usuario findUsuario(String email) {
		String	jpql = "select u from Usuario u where u.email = :email";
		Usuario userAux = manager.createQuery(jpql, Usuario.class).setParameter("email",email).getSingleResult();
		System.out.println("User 0 " + userAux.getAniversario() );
		/*int dia = userAux.getAniversario().getDate() + 1;
		Date ajustaData = new Date(userAux.getAniversario().getTime());
		ajustaData.setDate(dia);
		userAux.setAniversario(ajustaData);
		*/
		return userAux;
	}

	public boolean logar(Login login){
		List<String> userEmail = manager.createQuery("SELECT email FROM Usuario").getResultList();
		List<String> userSenha = manager.createQuery("SELECT senha FROM Usuario").getResultList();
		
		for(int i = 0; i < userEmail.size() ; i++ ) {
			if(userEmail.get(i).equals(login.getEmail()) && 
					userSenha.get(i).equals(login.getSenha())){
				return true;
			}
			return false;
		}

		return false;
	}
	
	public String buscarLogin(Login login) {
		String	jpql = "select u from Usuario u where u.email = :email and u.senha= :senha";
		List<Usuario> users = manager.createQuery(jpql, Usuario.class).setParameter("email",login.getEmail())
				.setParameter("senha", login.getSenha()).getResultList();
		if(users.isEmpty()) {
			return "";
		}
		return String.valueOf(users.get(0).getId());
	}
	
	public List<Usuario> pesquisarAddAmigos(String nomeId){
		String	jpql = "select u from Usuario u where u.userName = :userName";
		List<Usuario> userAux = manager.createQuery(jpql, Usuario.class).setParameter("userName",nomeId).getResultList();
		return userAux;
	}
	
	public List<Usuario> pesquisarUsuarioNome(String userName){
		String	jpql = "select u from Usuario u where u.userName like :userName";
		List<Usuario> userAux = manager.createQuery(jpql, Usuario.class).setParameter("userName",userName + "%").getResultList();
		return userAux;
	}
	
	public Usuario findById(int id) {
		String	jpql = "select u from Usuario u where u.id = :id";
		Usuario userAux = manager.createQuery(jpql, Usuario.class).setParameter("id",id).getSingleResult();
		return userAux;
	}
	
	public List<Usuario> getAllUsers(){
		String	jpql = "select u from Usuario u";
		List<Usuario> userAux = manager.createQuery(jpql, Usuario.class).getResultList();
		return userAux;
	}
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		String	jpql = "select u from Usuario u where u.email = :email";
		List<Usuario> users = manager.createQuery(jpql, Usuario.class).setParameter("email",email).getResultList();
		if(users.isEmpty()){
			throw	new	UsernameNotFoundException
							("O	usuario	"+ email +"	não	existe");
		}
			return (UserDetails) users.get(0);
		}
	
}
