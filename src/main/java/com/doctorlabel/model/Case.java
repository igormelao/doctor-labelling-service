package com.doctorlabel.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cases")
public class Case {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Doctor doctor;

	@ElementCollection
	@CollectionTable(name = "cases_label", joinColumns = @JoinColumn(name = "case_id"))
	@Column(name = "label_id")
	private List<String> labels;

	@Column(columnDefinition = "TEXT")
	private String electronicHealthRecord;

	private LocalDateTime timeToLabel;

	public Case() {
	}

	public Case(Long id, Doctor doctor, List<String> labels, String electronicHealthRecord, LocalDateTime timeToLabel) {
		this.id = id;
		this.doctor = doctor;
		this.labels = labels;
		this.electronicHealthRecord = electronicHealthRecord;
		this.timeToLabel = timeToLabel;
	}

	public Case(String electronicHealthRecord) {
		this.electronicHealthRecord = electronicHealthRecord;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public List<String> getLabels() {
		return labels;
	}

	public void setLabels(List<String> labels) {
		this.labels = labels;
	}

	public String getElectronicHealthRecord() {
		return electronicHealthRecord;
	}

	public void setElectronicHealthRecord(String electronicHealthRecord) {
		this.electronicHealthRecord = electronicHealthRecord;
	}

	public LocalDateTime getTimeToLabel() {
		return timeToLabel;
	}

	public void setTimeToLabel(LocalDateTime timeToLabel) {
		this.timeToLabel = timeToLabel;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((doctor == null) ? 0 : doctor.hashCode());
		result = prime * result + ((electronicHealthRecord == null) ? 0 : electronicHealthRecord.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((labels == null) ? 0 : labels.hashCode());
		result = prime * result + ((timeToLabel == null) ? 0 : timeToLabel.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Case other = (Case) obj;
		if (doctor == null) {
			if (other.doctor != null)
				return false;
		} else if (!doctor.equals(other.doctor))
			return false;
		if (electronicHealthRecord == null) {
			if (other.electronicHealthRecord != null)
				return false;
		} else if (!electronicHealthRecord.equals(other.electronicHealthRecord))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (labels == null) {
			if (other.labels != null)
				return false;
		} else if (!labels.equals(other.labels))
			return false;
		if (timeToLabel == null) {
			if (other.timeToLabel != null)
				return false;
		} else if (!timeToLabel.equals(other.timeToLabel))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Case [id=" + id + ", doctor=" + doctor + ", labels=" + labels + ", electronicHealthRecord="
				+ electronicHealthRecord + ", timeToLabel=" + timeToLabel + "]";
	}

}
