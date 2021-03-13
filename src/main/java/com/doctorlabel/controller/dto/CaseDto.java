package com.doctorlabel.controller.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.doctorlabel.model.Case;
import com.doctorlabel.model.Label;
import com.doctorlabel.service.LabelProxy;

public class CaseDto {

	private Long caseId;
	private UserDto doctor;
	private List<LabelDto> labels;
	private String electronicHealthRecord;
	private Long timeLabelling;
	private LocalDateTime dateCreate;

	public CaseDto() {

	}

	public CaseDto(Case caseDoctor, LabelProxy labelProxy) {
		this.caseId = caseDoctor.getId();

		if (caseDoctor.getDoctor() != null)
			this.doctor = new UserDto(caseDoctor.getDoctor());

		if (caseDoctor.getLabels() != null) {
			List<String> labelsIds = caseDoctor.getLabels();
			List<Label> labels = new ArrayList<Label>();

			// TODO: Create a new resource Label to get all labels by id
			// making only one call instead of many
			labelsIds.forEach(idLabel -> {
				Label label = labelProxy.retrieveLabel(idLabel);

				if (label != null)
					labels.add(label);
			});

			this.labels = LabelDto.convert(labels);
		}

		this.electronicHealthRecord = caseDoctor.getElectronicHealthRecord();
		this.timeLabelling = caseDoctor.getTimeInMinutesToLabel();
		this.dateCreate = caseDoctor.getDateCreate();
	}

	public Long getCaseId() {
		return caseId;
	}

	public void setCaseId(Long caseId) {
		this.caseId = caseId;
	}

	public UserDto getDoctor() {
		return doctor;
	}

	public void setDoctor(UserDto doctor) {
		this.doctor = doctor;
	}

	public List<LabelDto> getLabels() {
		return labels;
	}

	public void setLabels(List<LabelDto> labels) {
		this.labels = labels;
	}

	public String getElectronicHealthRecord() {
		return electronicHealthRecord;
	}

	public void setElectronicHealthRecord(String electronicHealthRecord) {
		this.electronicHealthRecord = electronicHealthRecord;
	}

	public Long getTimeLabelling() {
		return timeLabelling;
	}

	public void setTimeLabelling(Long timeLabelling) {
		this.timeLabelling = timeLabelling;
	}

	public static List<CaseDto> convert(List<Case> cases, LabelProxy labelProxy) {
		List<CaseDto> casesDtos = new ArrayList<CaseDto>();

		cases.stream().forEach(doctorCase -> {
			CaseDto caseDto = new CaseDto(doctorCase, labelProxy);

			if (caseDto != null)
				casesDtos.add(caseDto);
		});

		return casesDtos;
	}

	public LocalDateTime getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(LocalDateTime dateCreate) {
		this.dateCreate = dateCreate;
	}

}
