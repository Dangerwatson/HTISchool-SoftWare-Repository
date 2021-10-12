package com.pathlight.hti_school.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;





@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Bean
	public UserDetailsService userDetailsService() {
		return new CustomUserDetailsService();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		
		return authProvider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		 http.csrf().disable() .authorizeRequests();		 
		http.authorizeRequests()
		
		.antMatchers("/ListerClasses**/**","/classes**/**","/inscription**/**","/Details**/**","/AjouterProfesseur**/**","/listeEl**/**","/listeMatiereC**/**","/AjouterMatieres**/**","/liste_cate_matiere**/**","/cate_matiere**/**","/ajouterProfesseur**/**","/listeProfesseurs**/**" ,"/releve_notes**/**","/listeNote**/**","/calculMoyenne**/**","/listeDeFormationEl**/**","/listeDeCesion**/**","/users**/**","/registerUser**/**","/role**/**","/AjouterPresence**/**","/listepresence**/**","/AjouterPresenceProf**/**","/listepresenceProf**/**").hasAnyAuthority( "ADMIN","DIRECTEUR")
		//.antMatchers("/editUser").hasAnyAuthority("ADMIN", "DIRECTEUR")
		//.antMatchers("/users/**").hasAnyAuthority("USER")
		//.antMatchers("/delete/**").hasAuthority("ADMIN")
		
			.antMatchers("/users/**").authenticated()
			.anyRequest().permitAll()
			.and()
			.formLogin().loginPage("/login").permitAll()
				.usernameParameter("email")
				.defaultSuccessUrl("/HTISchool")
				
				.successHandler(successHandler)
				.permitAll()
			.and()
			.logout().logoutSuccessUrl("/").permitAll();
		http.exceptionHandling().accessDeniedPage("/notAutorized");
	}
	
	@Autowired
	private LoginSuccessHandler successHandler;
}
