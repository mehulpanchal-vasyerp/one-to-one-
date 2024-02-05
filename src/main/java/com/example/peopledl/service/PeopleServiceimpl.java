  package com.example.peopledl.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.peopledl.dto.Licensesdto;
import com.example.peopledl.dto.Peopledto;
import com.example.peopledl.model.Licenses;
import com.example.peopledl.model.People;
import com.example.peopledl.repository.LicensesRepo;
import com.example.peopledl.repository.PeopleRepo1;

@Service
public class PeopleServiceimpl implements PeopleService{
@Autowired
     public PeopleRepo1 peopleRepo;
@Autowired
	public LicensesRepo licensesRepo;

@Override
public List<Peopledto> fetchAllPeople() {
	List<People> peoples= peopleRepo.findAll();
	List<Peopledto>peopledtos= new ArrayList<Peopledto>();
	for(People people : peoples) {
		Peopledto peopledto=new Peopledto();
		peopledto.setPeopleId(people.getPeopleId());
		peopledto.setPeopleName(people.getPeopleName());
		peopledto.setLicenses(people.getLicenses());
		peopledtos.add(peopledto);
		
	}
	return peopledtos;
}

@Override
public Peopledto getPeopleBypeopleId(Long peopleId) {
	People people=peopleRepo.findById(peopleId).get();
	Peopledto peopledto=new  Peopledto();
	peopledto.setPeopleId(people.getPeopleId()); // model to dto
	peopledto.setPeopleName(people.getPeopleName());
	
	return peopledto;
}

@Override
public boolean deletePeopleBypeopleId(Long peopleId) {
	peopleRepo.deleteById(peopleId);
	return true;
}

@Override
public boolean addPeople(Peopledto peopledto) {
	People people=new People(); // model object dto to model
	people.setPeopleId(peopledto.getPeopleId());
	people.setPeopleName(peopledto.getPeopleName());
	peopleRepo.save(people);
	return true;
}

@Override
public boolean assignLicenses(Long peopleId, Long LicensesId) {
	People existingPeople=peopleRepo.findById(peopleId).get();
	Licenses licenses= licensesRepo.findById(LicensesId).get();
	if(existingPeople!=null) {
		existingPeople.setLicenses(licenses);
		peopleRepo.save(existingPeople);
	
	return true;
}

	return false;
	
}
}
 