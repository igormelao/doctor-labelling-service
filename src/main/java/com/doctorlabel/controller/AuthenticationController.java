package com.doctorlabel.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doctorlabel.config.security.TokenService;
import com.doctorlabel.controller.dto.TokenDto;
import com.doctorlabel.controller.form.Loginform;

/**
* 
* <p>This class is a Rest Controller for resources of Authentication</p>
* 
* <p>There are 2 dependencies related with this Rest Controller and it is Injectabled by SpringBoot by tag @Autowired <p>
* 
* <ol>
* 	<li>AuthenticationManager</li>
*  <li>TokenService</li>
* </ol>
* 
* <p>The list available for this Rest AuthenticationController are described below:<p>
* 
*  <ul> 
*  	<li>
*  		Request Mapping: "/auth" 
*  		<ul>
*  			<li>METHOD: POST</li>
*  			<li>return a ResponseEntity TokenDto ( @see {@link com.doctorlabel.controller.dto.TokenDto})</li>
*  		</ul>
* 	</li>
*  </ul>
*  
*  <p> You can see more details of all resource @see <a href="http://localhost:8081/swagger-ui.html#/authentication-controller">http://localhost:8081/swagger-ui.html#/authentication-controller</a>
* 
* @author Igor Melão (igormelao@gmail.com)
* @Date:  14-03-2021
* @since  0.0.1-SNAPSHOT
* 
*/
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

	/**
	 * <p>This is a Spring Boot class that it's responsible to authenticate the user</p>
	 * 
	 * @author Igor Melão (igormelao@gmail.com)
	 * @since 0.0.1-SNAPSHOT
	 */
	@Autowired
	private AuthenticationManager authManager;
	
	/**
	 * <p>This is a class that it's responsible to manager the Token (create,validate and return informations of user)</p>
	 * 
	 * @author Igor Melão (igormelao@gmail.com)
	 * @since 0.0.1-SNAPSHOT
	 */
	@Autowired
	private TokenService tokenService;

	/**
	 * <p>This is a resource to authenticate a User</p>
	 * <p>The parameters necessaries are:</p>
	 * <ol>
	 * 	<li>e-mail</li>
	 * <li>password</li>
	 * </ol>
	 * <p>This will return a TokenDto object with a Token generated and a type Bearer.</p>
	 * 
	 * @author Igor Melão (igormelao@gmail.com)
	 * @param 
	 * @return ResponseEntity< TokenDto > @see(com.doctorlabel.controller.dto.TokenDto)
	 * @see <a href="http://localhost:8081/swagger-ui.html#/authentication-controller/authenticateUsingPOST">Swagger API Documentation</a>
	 * @since 0.0.1-SNAPSHOT
	 */
	@PostMapping
	public ResponseEntity<TokenDto> authenticate(@RequestBody @Valid Loginform form) {
		UsernamePasswordAuthenticationToken dataLogin = form.convert();

		try {
			Authentication authentication = authManager.authenticate(dataLogin);
			String token = tokenService.createToken(authentication);
			return ResponseEntity.ok(new TokenDto(token, "Bearer"));
		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().build();
		}
	}
}
