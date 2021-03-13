package com.doctorlabel.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
	private User doctor;

	@ElementCollection
	@CollectionTable(name = "cases_label", joinColumns = @JoinColumn(name = "case_id"))
	@Column(name = "label_id")
	private List<String> labels;

	@Column(columnDefinition = "TEXT")
	private String electronicHealthRecord;

	private Long timeInMinutesToLabel;

	private LocalDateTime dateCreate = LocalDateTime.now();

	@Enumerated(EnumType.STRING)
	private StateCase state = StateCase.NOT_LABELLED;

	public Case() {
	}

	public Case(Long id, User doctor, List<String> labels, String electronicHealthRecord, Long timeInMinutesToLabel,
			LocalDateTime dateCreate, StateCase state) {
		super();
		this.id = id;
		this.doctor = doctor;
		this.labels = labels;
		this.electronicHealthRecord = electronicHealthRecord;
		this.timeInMinutesToLabel = timeInMinutesToLabel;
		this.dateCreate = dateCreate;
		this.state = state;
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

	public User getDoctor() {
		return doctor;
	}

	public void setDoctor(User doctor) {
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

	public Long getTimeInMinutesToLabel() {
		return timeInMinutesToLabel;
	}

	public void setTimeInMinutesToLabel(Long timeInMinutesToLabel) {
		this.timeInMinutesToLabel = timeInMinutesToLabel;
	}

	public LocalDateTime getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(LocalDateTime dateCreate) {
		this.dateCreate = dateCreate;
	}

	public StateCase getState() {
		return state;
	}

	public void setState(StateCase state) {
		this.state = state;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateCreate == null) ? 0 : dateCreate.hashCode());
		result = prime * result + ((doctor == null) ? 0 : doctor.hashCode());
		result = prime * result + ((electronicHealthRecord == null) ? 0 : electronicHealthRecord.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((labels == null) ? 0 : labels.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((timeInMinutesToLabel == null) ? 0 : timeInMinutesToLabel.hashCode());
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
		if (dateCreate == null) {
			if (other.dateCreate != null)
				return false;
		} else if (!dateCreate.equals(other.dateCreate))
			return false;
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
		if (state != other.state)
			return false;
		if (timeInMinutesToLabel == null) {
			if (other.timeInMinutesToLabel != null)
				return false;
		} else if (!timeInMinutesToLabel.equals(other.timeInMinutesToLabel))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Case [id=" + id + ", doctor=" + doctor + ", labels=" + labels + ", electronicHealthRecord="
				+ electronicHealthRecord + ", timeInMinutesToLabel=" + timeInMinutesToLabel + ", dateCreate="
				+ dateCreate + ", state=" + state + "]";
	}

}
