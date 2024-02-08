package com.example.peopledl.service;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.example.peopledl.dto.Peopledto;
import com.example.peopledl.model.People;

@Component
public interface PeopleService {
	List<Peopledto> fetchAllPeople();

	Peopledto getPeopleBypeopleId(Long peopleId);

	People deletePeopleBypeopleId(Long peopleId);

	boolean addPeople(Peopledto peopledto);

	boolean assignLicenses(Long peopleId, Long LicensesId);

	String uploadFile(MultipartFile file);
	
	ResponseEntity<Object> downloadFile(String fileName);

	String uploadFile1(MultipartFile file) throws IOException;

//	String uploadFile1(MultipartFile file);

}
