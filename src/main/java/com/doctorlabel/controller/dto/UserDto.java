package com.doctorlabel.controller.dto;

import com.doctorlabel.model.User;

public class UserDto {

	private Long id;
	private String name;

	public UserDto() {
	}
	
	public UserDto(User user) {
		this.id = user.getId();
		this.name = user.getName();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

}
