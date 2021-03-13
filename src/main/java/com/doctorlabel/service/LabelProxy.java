package com.doctorlabel.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.doctorlabel.model.Label;

@FeignClient(name="doctor-label-service", url="localhost:8080")
public interface LabelProxy {
	
	@GetMapping("/labels/{id}")
	public Label retrieveLabel(@PathVariable String id);

}
