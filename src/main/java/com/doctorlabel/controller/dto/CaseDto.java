package com.doctorlabel.controller.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.doctorlabel.model.Case;

public class CaseDto {

	private Long caseId;
	private DoctorDto doctor;
	private List<LabelDto> labels;
	private String electronicHealthRecord;
	private LocalDateTime timeLabelling;

	public CaseDto() {

	}

	public CaseDto(Case caseDoctor) {
		this.caseId = caseDoctor.getId();

		if (caseDoctor.getDoctor() != null)
			this.doctor = new DoctorDto(caseDoctor.getDoctor());
		
		if (caseDoctor.getLabels() != null)
			this.labels = LabelDto.convert(caseDoctor.getLabels());
		
		this.electronicHealthRecord = caseDoctor.getElectronicHealthRecord();
		this.timeLabelling = caseDoctor.getTimeToLabel();
	}

	public Long getCaseId() {
		return caseId;
	}

	public void setCaseId(Long caseId) {
		this.caseId = caseId;
	}

	public DoctorDto getDoctor() {
		return doctor;
	}

	public void setDoctor(DoctorDto doctor) {
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

}
