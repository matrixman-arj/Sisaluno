package br.mil.eb.decex.sisaluno.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import br.mil.eb.decex.sisaluno.security.AppUserDetailsService;

@EnableWebSecurity
@ComponentScan(basePackageClasses = AppUserDetailsService.class)
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{	
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {		
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
		
//		auth.inMemoryAuthentication()
//		.withUser("admin").password("admin").roles("CADASTRO_COMPONENTE");
	}	
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
			.antMatchers("/layout/**")
			.antMatchers("/images/**")
			.antMatchers("/javascripts/**")
			.antMatchers("/templates/**");	
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
//				.antMatchers("/cidades/nova").hasRole("ADMINISTRACAO")
//				.antMatchers("/usuarios/**").hasRole("ADMINISTRACAO")
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login")								
				.permitAll()
				.and()
				.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.and()
			.exceptionHandling()
				.accessDeniedPage("/403")
				.and()
		.sessionManagement()
			.maximumSessions(3)
			.expiredUrl("/login")
			.and()
			.invalidSessionUrl("/login");
						
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
