package com.doctorlabel.controller.form;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.doctorlabel.model.Case;
import com.doctorlabel.repository.CaseRepository;

public class UpdateCaseForm {

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

	public Case updateCase(Long id, @Valid UpdateCaseForm form, CaseRepository caseRepository) {
		Case doctorCase = caseRepository.getOne(id);
		doctorCase.setElectronicHealthRecord(form.getElectronicHealthRecord());
		return doctorCase;
	}

}
