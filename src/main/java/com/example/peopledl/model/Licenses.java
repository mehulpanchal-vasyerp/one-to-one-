package com.example.peopledl.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Licenses {
	@Override
	public String toString() {
		return "Licenses [LicensesId=" + LicensesId + ", LicenseExdate=" + LicenseExdate + "]";
	}
	public Long getLicensesId() {
		return LicensesId;
	}
	public void setLicensesId(Long licensesId) {
		LicensesId = licensesId;
	}
	public String getLicenseExdate() {
		return LicenseExdate;
	}
	public void setLicenseExdate(String licenseExdate) {
		LicenseExdate = licenseExdate;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "licenses_id")
	private Long LicensesId;
	@Column(name = "licenses_exdate")
	private String LicenseExdate ;
	

}
