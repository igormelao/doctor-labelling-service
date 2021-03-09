package com.doctorlabel.controller.form;

import java.util.Arrays;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.doctorlabel.model.Case;
import com.doctorlabel.repository.CaseRepository;

public class CreateLabelCaseForm {

	@NotNull
	@NotEmpty
	@Length(min = 2)
	private String idLabel;

	public String getIdLabel() {
		return idLabel;
	}

	public void setIdLabel(String idLabel) {
		this.idLabel = idLabel;
	}

	public Case insertLabel(Long idCase, CreateLabelCaseForm form, CaseRepository caseRepository) {
		Case doctorCase = caseRepository.getOne(idCase);

		if (doctorCase.getLabels().isEmpty())
			doctorCase.setLabels(Arrays.asList(form.getIdLabel()));
		else
			doctorCase.getLabels().add(form.getIdLabel());

		return doctorCase;
	}
}
