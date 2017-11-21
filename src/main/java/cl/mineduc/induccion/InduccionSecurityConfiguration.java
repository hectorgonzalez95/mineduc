/**
 * 
 */
package cl.mineduc.induccion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import cl.mineduc.framework2.security.spring.LdapAuthenticationProvider;
import cl.mineduc.framework2.security.spring.UserService;

import cl.mineduc.induccion.authentication.UserServices;


/**
 * @author Rodrigo Alvarez Chanchito My Friend, Alvaro Tellez
 *
 */
@Configuration
@ComponentScan(basePackages = {"cl.mineduc.induccion.authentication"})
@EnableWebSecurity
public class InduccionSecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Bean
	public UserService userService(){
		return new UserServices();
	}
	
	@Bean
	public LdapAuthenticationProvider ldapProvider(){
		LdapAuthenticationProvider provider = new LdapAuthenticationProvider();
		provider.setUserService(userService());
		
		return provider;
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/locals/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.authorizeRequests()
//				.antMatchers("/login/**").permitAll()
//				.antMatchers("/alumno/**").permitAll()
				.antMatchers("/**").permitAll()
				.anyRequest().authenticated()
			//.and().exceptionHandling().accessDeniedPage("/mvc/errors/accessDenied")
		.and()
			.formLogin()
//				.loginPage("/login/login")
				.loginPage("/alumno/form")
				.loginProcessingUrl("/login/do_login")
				.defaultSuccessUrl("/home/index", true)
				.failureUrl("/login/login?error=1")
				.usernameParameter("txtUsuario")
				.passwordParameter("txtClave")
				.permitAll();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		//auth.authenticationProvider(ldapProvider())
		auth.inMemoryAuthentication().withUser("1").password("1").roles("USER");
	}
}