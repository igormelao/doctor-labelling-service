package com.doctorlabel.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.doctorlabel.repository.UserRepository;
/**
* 
* <p>This is a Security Configuration Class that allow us configure some behaviors for Spring Boot Security</p>
* 
* <p>There are 3 dependencies related with this Rest Controller and it is Injectabled by SpringBoot by tag @Autowired <p>
* 
* <ol>
*   <li>AuthenticationService</li>
* 	<li>TokenService (to manage Token)</li>
* 	<li>UserRepository (to manage User database informations)</li>
* </ol>
* 
* <p> Every request that was made for this application is filtered by this Filter. With this, we validate the header informations
* like Toke passed and verify if the user is present or not in the system.
* </p>
*
* <p>Case everything it's ok the system allow to continue the flow of the application. Case not, break the request and return
* bad request to who called</p>
*  
* @author Igor Melão (igormelao@gmail.com)
* @Date:  14-03-2021
* @since  0.0.1-SNAPSHOT
* 
*/
@EnableWebSecurity
@Configuration
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {

	@Autowired
	private AuthenticationService authenticationService;

	@Autowired
	private TokenService tokenService;

	@Autowired
	private UserRepository userRepository;

	
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(authenticationService).passwordEncoder(new BCryptPasswordEncoder());
	}

	/**
	 * <p>Allows which resource it's permit or not to access without token</p>
	 * <p>For this case, only POST request to /auth it's allows to request without be valid.It's 
	 * possible that can generate TOKEN for the others requests</p>
	 * <p> For others request, the TOKEN it's mandatory</p>
	 * <p> This place is also the place to call the Filter to validate Token and disable cors and csrf
	 * 
	 * @author Igor Melão (igormelao@gmail.com)
	 * @since 0.0.1-SNAPSHOT
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/auth").permitAll().anyRequest().authenticated().and()
				.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and().cors()
				.and()
				.addFilterBefore(new AuthenticationByTokenFilter(tokenService, userRepository),
						UsernamePasswordAuthenticationFilter.class);
	}

	/**
	 * <p>Allows some resources to be touch without send the TOKEN</p>
	 * <p> The case configured below, it's for allows access to all resources to Swagger UI</p>
	 * @author Igor Melão (igormelao@gmail.com)
	 * @since 0.0.1-SNAPSHOT
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {
	    web.ignoring()
	        .antMatchers("/**.html", "/v2/api-docs", "/webjars/**", "/configuration/**", "/swagger-resources/**");
	}

}
