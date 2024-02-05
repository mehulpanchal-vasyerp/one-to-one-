package com.example.peopledl.service;

import java.util.List;

import com.example.peopledl.dto.Licensesdto;

public interface LicensesService {
	List<Licensesdto> getallLicenses();
	
	void deleteLicensesById(Long LicensesId);
	
	Licensesdto getLicensesById(Long LicensesId);
	
	void addLicenses(Licensesdto licensesdto);
	
	

}
