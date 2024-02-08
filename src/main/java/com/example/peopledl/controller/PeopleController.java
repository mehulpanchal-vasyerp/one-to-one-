package com.example.peopledl.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.peopledl.dto.Peopledto;
import com.example.peopledl.dto.Response;
import com.example.peopledl.model.People;
import com.example.peopledl.service.PeopleService;

@RestController
@RequestMapping("/api/people")

public class PeopleController {
	@Autowired
	public PeopleService peoService;

	@GetMapping("/all")
	public ResponseEntity<List<Peopledto>> getAllPeople() {

		return ResponseEntity.ok(peoService.fetchAllPeople());
	}

	@PostMapping({ "/add", "/update" })
	public ResponseEntity<String> addPeople(@RequestBody Peopledto peopledto) {
		peoService.addPeople(peopledto);
		return ResponseEntity.ok("success");
	}

	@DeleteMapping("/delete")
	public Response deletebyid(@RequestParam Long peopleId) {
		peoService.deletePeopleBypeopleId(peopleId);
		return new Response(200, "success", "Delete Suuccessful");

		// peoService.deletePeopleBypeopleId(peopleId);
		// return ResponseEntity.ok(" delete successful");

	}

	@GetMapping("/get")
	public Response get(@RequestParam Long peopleId) {
		Peopledto peopledto= peoService.getPeopleBypeopleId(peopleId);
	
	//public ResponseEntity<Peopledto> get(@RequestParam Long peopleId) {
		return new Response(200,"success",peopledto);
	}

//	public ResponseEntity<Peopledto> get(@RequestParam Long peopleId) {
//		return ResponseEntity.ok(peoService.getPeopleBypeopleId(peopleId));
//
//	}

	@PostMapping("/assign")
	public ResponseEntity<String> assignPeople(@RequestParam Long peopleId, @RequestParam Long LicensesId) {
		peoService.assignLicenses(peopleId, LicensesId);
		return ResponseEntity.ok("Assign Success");
	}

	@PostMapping("/upload")

	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
		// Implement logic to save the file, e.g., store it in the file system or
		// database
		// Return a success message or error message accordingly
//		String fileName = file.getOriginalFilename();
//		System.out.println("Uploaded file name: " + fileName);
//		peoService.uploadFile(file);
		return ResponseEntity.ok(peoService.uploadFile(file));

	}

	@GetMapping("/download/{fileName}")
	public ResponseEntity<Object> downloadFile(@PathVariable String fileName) {
		ResponseEntity<Object> response = peoService.downloadFile(fileName);
		return response;

	}

	@PostMapping("/uploads")
	public ResponseEntity<String> uploadFile1(@RequestParam("file") MultipartFile file) throws IOException {
		// Implement logic to save the file, e.g., store it in the file system or
		// database
		// Return a success message or error message accordingly
//	String fileName = file.getOriginalFilename();
//	System.out.println("Uploaded file name: " + fileName);
//	peoService.uploadFile(file);
		return ResponseEntity.ok(peoService.uploadFile1(file));
	}




	
}