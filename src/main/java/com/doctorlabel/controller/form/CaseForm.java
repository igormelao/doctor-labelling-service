package com.doctorlabel.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.doctorlabel.model.Case;

public class CaseForm {

	@NotNull
	@NotEmpty
	@Length(min = 10)
	private String electronicHealthRecord;

	public String getElectronicHealthRecord() {
		return electronicHealthRecord;
	}

	public void setElectronicHealthRecord(String electronicHealthRecord) {
		this.electronicHealthRecord = electronicHealthRecord;
	}

	public Case convert() {
		return new Case(this.getElectronicHealthRecord());
	}

}
