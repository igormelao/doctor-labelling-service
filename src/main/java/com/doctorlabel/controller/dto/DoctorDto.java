package com.doctorlabel.controller.dto;

import com.doctorlabel.model.Doctor;

public class DoctorDto {
	private Long doctorId;
	private String name;

	public DoctorDto() {
	}

	public DoctorDto(Doctor doctor) {
		this.doctorId = doctor.getId();
		this.name = doctor.getName();
	}

	public Long getDoctorId() {
		return doctorId;
	}

	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((doctorId == null) ? 0 : doctorId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		DoctorDto other = (DoctorDto) obj;
		if (doctorId == null) {
			if (other.doctorId != null)
				return false;
		} else if (!doctorId.equals(other.doctorId))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DoctorDto [doctorId=" + doctorId + ", name=" + name + "]";
	}

}
