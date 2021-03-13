package com.doctorlabel.controller.form;

import java.util.Arrays;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.doctorlabel.model.Case;
import com.doctorlabel.model.StateCase;
import com.doctorlabel.model.User;
import com.doctorlabel.repository.CaseRepository;
import com.doctorlabel.repository.UserRepository;

public class CreateLabelCaseForm {

	@NotNull
	@NotEmpty
	private String idLabel;

	@NotNull
	private Long idUser;

	public void setIdLabel(String idLabel) {
		this.idLabel = idLabel;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public Case insertLabel(Long idCase, CaseRepository caseRepository, UserRepository userRepository) {
		Case doctorCase = caseRepository.getOne(idCase);

		if (doctorCase.getLabels().isEmpty())
			doctorCase.setLabels(Arrays.asList(this.idLabel));
		else
			doctorCase.getLabels().add(this.idLabel);
		
		User doctor = userRepository.getOne(this.idUser);
		
		if(doctor != null)
			doctorCase.setDoctor(doctor);
		
		doctorCase.setState(StateCase.LABELLED);

		return doctorCase;
	}
}
