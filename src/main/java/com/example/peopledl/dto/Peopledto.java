package com.example.peopledl.dto;

import javax.persistence.Column;

import com.example.peopledl.model.Licenses;

import lombok.Data;

@Data
public class Peopledto {
	private Long peopleId;
	private String peopleName;

	private Licenses licenses;

	@Override
	public String toString() {
		return "Peopledto [peopleId=" + peopleId + ", peopleName=" + peopleName + ", licenses=" + licenses + "]";
	}

	public Long getPeopleId() {
		return peopleId;
	}

	public void setPeopleId(Long peopleId) {
		this.peopleId = peopleId;
	}

	public String getPeopleName() {
		return peopleName;
	}

	public void setPeopleName(String peopleName) {
		this.peopleName = peopleName;
	}

	public Licenses getLicenses() {
		return licenses;
	}

	public void setLicenses(Licenses licenses) {
		this.licenses = licenses;
	}
}
