package com.example.peopledl.service;

import java.util.List;

import com.example.peopledl.dto.Peopledto;

public interface PeopleService{
   List <Peopledto> fetchAllPeople();
   
   Peopledto getPeopleBypeopleId(Long peopleId);
   boolean deletePeopleBypeopleId(Long peopleId);
   boolean addPeople(Peopledto peopledto);
   boolean assignLicenses(Long peopleId, Long LicensesId );
   
}
