package com.doctorlabel.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.doctorlabel.model.Label;

public class LabelDto {

	private String id;
	private String description;

	public LabelDto() {
	}

	public LabelDto(Label label) {
		this.id = label.getId();
		this.description = label.getDescription();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public static List<LabelDto> convert(List<Label> labels) {
		return labels.stream().map(LabelDto::new).collect(Collectors.toList());
	}

}
