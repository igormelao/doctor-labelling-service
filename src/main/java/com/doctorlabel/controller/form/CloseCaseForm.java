package com.doctorlabel.controller.form;

import java.time.Duration;
import java.time.LocalDateTime;

import com.doctorlabel.model.Case;
import com.doctorlabel.model.StateCase;
import com.doctorlabel.repository.CaseRepository;

public class CloseCaseForm {

	public static Case closeCase(Long id, CaseRepository caseRepository) {
		Case doctorCase = caseRepository.getOne(id);
		LocalDateTime dateCreate = doctorCase.getDateCreate();
		LocalDateTime now = LocalDateTime.now();

	    Duration duration = Duration.between(now, dateCreate);
		
		doctorCase.setTimeInMinutesToLabel(Math.abs(duration.toMinutes()));
		doctorCase.setState(StateCase.CLOSE);
		return doctorCase;
	}

}
