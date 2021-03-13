package com.doctorlabel.config.security;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.doctorlabel.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

	@Value("${doctor.labelling.service.jwt.expiration}")
	private String tokenExpiration;

	@Value("${doctor.labelling.service.jwt.secret}")
	private String secret;

	public String createToken(Authentication authentication) {
		User userLogIn = (User) authentication.getPrincipal();
		Date today = new Date();
		Date dateTokenExpiration = new Date(today.getTime() + Long.parseLong(tokenExpiration));

		Map<String,Object> claims = new HashMap<>();
		claims.put("id",userLogIn.getId());
        claims.put("name",userLogIn.getUsername());
        claims.put("email", userLogIn.getEmail());
		
		return Jwts.builder().setIssuer("Api Doctor Labelling Service")
				.setSubject(userLogIn.getId().toString())
				.setClaims(claims)
				.setIssuedAt(today).setExpiration(dateTokenExpiration)
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
	}

	public boolean isTokenValid(String token) {
		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Long getUserId(String token) {
		Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();		
		List<Object> userValues = claims.values().stream().collect(Collectors.toList());
		Long idUser = Long.parseLong(userValues.get(1).toString());
		return idUser;
	}
}
