package com.example.peopledl.dto;

import lombok.Data;
@Data
public class Licensesdto {
	
	private Long LicensesId;
	private String LicenseExdate ;

	@Override
	public String toString() {
		return "Licensesdto [LicensesId=" + LicensesId + ", LicenseExdate=" + LicenseExdate + "]";
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
}
