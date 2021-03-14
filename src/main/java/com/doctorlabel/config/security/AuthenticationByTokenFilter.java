package com.doctorlabel.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.doctorlabel.model.User;
import com.doctorlabel.repository.UserRepository;

/**
* 
* <p>This Filter Class that allow us configure some behaviors for Spring Boot Security</p>
* 
* <p>There are 2 dependencies related with this Rest Controller and it is Injectabled by SpringBoot by tag @Autowired <p>
* 
* <ol>
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
public class AuthenticationByTokenFilter extends OncePerRequestFilter {

	private TokenService tokenService;

	private UserRepository userRepository;

	public AuthenticationByTokenFilter(TokenService tokenService, UserRepository userRepository) {
		this.tokenService = tokenService;
		this.userRepository = userRepository;
	}
	
	/**
	 * <p>Filter every request for this application and validate if Token and other informations are valid</p>
	 * <p> Get the token send through request and call TokenService to validate it</p>
	 * <p> Case valid, authenticate the client. Case not, break the access to application</p>
	 * 
	 * @author Igor Melão (igormelao@gmail.com)
	 * @since 0.0.1-SNAPSHOT
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String token = getToken(request);

		boolean isValid = tokenService.isTokenValid(token);

		if (isValid) {
			authenticateClient(token);
		}

		filterChain.doFilter(request, response);
	}

	/**
	 * <p>Get the User informations thought Token via request</p>
	 * <p>Verify if the user exist and authenticate the Client</p>
	 * 
	 *  <p>PS: You can get some informations from user logged in through SecurityContextHolder.getContext().getAuthentication method</p>
	 * 
	 * @author Igor Melão (igormelao@gmail.com)
	 * @since 0.0.1-SNAPSHOT
	 */
	private void authenticateClient(String token) {
		Long idUser = tokenService.getUserId(token);
		User user = userRepository.findById(idUser).get();
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null,
				user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	private String getToken(HttpServletRequest request) {
		
		String token = request.getHeader("Authorization");

		if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null;
		}

		return token.substring(7, token.length());
	}

}
