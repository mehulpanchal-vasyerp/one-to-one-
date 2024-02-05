package com.example.peopledl.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@Data
public class People {
	@Override
	public String toString() {
		return "People [peopleId=" + peopleId + ", peopleName=" + peopleName + ", licenses=" + licenses + "]";
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

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "people_id")
	private Long peopleId;
	@Column(name = "people_name")
	private String peopleName;
	
	@OneToOne
	private Licenses licenses;

}
