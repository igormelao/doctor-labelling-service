package com.doctorlabel.controller.dto;

public class TokenDto {

	private String token;
	private String type;

	public TokenDto(String token, String type) {
		this.token = token;
		this.type = type;
		// TODO Auto-generated constructor stub
	}

	public String getToken() {
		return token;
	}

	public String getType() {
		return type;
	}

}
