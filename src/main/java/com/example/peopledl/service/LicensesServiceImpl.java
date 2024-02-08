package com.example.peopledl.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.peopledl.dto.Licensesdto;
import com.example.peopledl.model.Licenses;
import com.example.peopledl.repository.LicensesRepo;

import exception.LicensesNotFoundException;
import exception.PeopleNotFoundException;

@Service
public class LicensesServiceImpl implements LicensesService{

	
	@Autowired
	private LicensesRepo licensesRepo;
	@Override
	public List<Licensesdto> getallLicenses() {
		List<Licenses> licenses= licensesRepo.findAll();
		List<Licensesdto>licensesdtos= new ArrayList<Licensesdto>();
		for(Licenses license : licenses) {
			Licensesdto licensesdto=new Licensesdto();
			licensesdto.setLicensesId(license.getLicensesId());
			licensesdto.setLicenseExdate(license.getLicenseExdate());
			licensesdtos.add(licensesdto);
		}
		return licensesdtos;
	}

	@Override
	public void deleteLicensesById(Long LicensesId) {
		
		//licensesRepo.deleteById(LicensesId);
		if(licensesRepo.existsById(LicensesId)) {
			licensesRepo.deleteById(LicensesId);
			}
			else {
				throw new LicensesNotFoundException("License not found");
			}
			
		}
		
	

	@Override
	public Licensesdto getLicensesById(Long LicensesId) {
		Licenses license=licensesRepo.findById(LicensesId).orElseThrow(()->new PeopleNotFoundException("licenses not found"));;
		Licensesdto licensesdto=new Licensesdto();
		licensesdto.setLicensesId(license.getLicensesId());
		licensesdto.setLicenseExdate(license.getLicenseExdate());
	
		return licensesdto ;
	}

	@Override
	public void addLicenses(Licensesdto licensesdto) {
		Licenses licenses=new Licenses();
		licenses.setLicenseExdate(licensesdto.getLicenseExdate());
		licensesRepo.save(licenses);
		
	}

}
