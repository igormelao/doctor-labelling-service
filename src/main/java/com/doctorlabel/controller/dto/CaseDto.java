package com.doctorlabel.controller.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.doctorlabel.model.Case;

public class CaseDto {

	private Long caseId;
	private UserDto doctor;
	private List<LabelDto> labels;
	private String electronicHealthRecord;
	private LocalDateTime timeLabelling;
	private LocalDateTime dateCreate;

	public CaseDto() {

	}

	public CaseDto(Case caseDoctor) {
		this.caseId = caseDoctor.getId();

		if (caseDoctor.getDoctor() != null)
			this.doctor = new UserDto(caseDoctor.getDoctor());

		if (caseDoctor.getLabels() != null)
			this.labels = LabelDto.convert(caseDoctor.getLabels());

		this.electronicHealthRecord = caseDoctor.getElectronicHealthRecord();
		this.timeLabelling = caseDoctor.getTimeToLabel();
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

	public LocalDateTime getTimeLabelling() {
		return timeLabelling;
	}

	public void setTimeLabelling(LocalDateTime timeLabelling) {
		this.timeLabelling = timeLabelling;
	}

	public static List<CaseDto> convert(List<Case> cases) {
		return cases.stream().map(CaseDto::new).collect(Collectors.toList());
	}

	public LocalDateTime getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(LocalDateTime dateCreate) {
		this.dateCreate = dateCreate;
	}

}
