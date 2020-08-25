package br.com.projectstages_mvc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.projectstages_mvc.dao.CadastroDao;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private CadastroDao users;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		/*
		 * http.authorizeRequests().antMatchers("/").hasRole("ADMIN")
		 * .anyRequest().authenticated() .and() .formLogin().and() .httpBasic();
		 */
		http.csrf().ignoringAntMatchers("/alterar-senha").and().authorizeRequests()
				.antMatchers("/", "/cadastro", "/login", "/pos-cadastro/home", "/pos-cadastro",
						"/cadastro/pos-cadastro", "/configuracoes", "/salvar/foto-perfil", "/termos-de-uso",
						"/solicitacao-email", "/solicitacao-email/email-enviado", "/email-enviado", "/alterar-senha" ,"/alterar-senha/atualizar-senha", "/email-nao-enviado")
				.permitAll().antMatchers("/home", "/login/validacao").access("hasRole('ROLE_ADMIN')")
				.antMatchers("/CSS/**", "/JS/**", "/img/**", "/Bootstrap/**").permitAll().anyRequest().authenticated()
				.and().formLogin().loginPage("/login").usernameParameter("email").passwordParameter("senha")
				.defaultSuccessUrl("/login/validacao").failureUrl("/login").permitAll().and()
				// .logout().permitAll()
				// .and()
				// .formLogin().loginPage("/cadastro/pos-cadastro").usernameParameter("email").passwordParameter("senha").defaultSuccessUrl("/pos-cadastro").failureUrl("/cadastro").permitAll()
				// .and()
				.logout().permitAll();

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(users).passwordEncoder(new BCryptPasswordEncoder());
	}
}
