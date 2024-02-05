package com.example.peopledl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.peopledl.dto.Peopledto;
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

	@PostMapping({"/add", "/update"})
	public ResponseEntity<String> addPeople(@RequestBody Peopledto peopledto) {
		peoService.addPeople(peopledto);
		return ResponseEntity.ok("success");
	}

	

	@DeleteMapping("/delete")
	public ResponseEntity<String> addPeople(@RequestParam Long peopleId) {
		peoService.deletePeopleBypeopleId(peopleId);
		return ResponseEntity.ok(" delete successful");

	}

	@GetMapping("/get")
	public ResponseEntity<Peopledto> get(@RequestParam Long peopleId) {
		return ResponseEntity.ok(peoService.getPeopleBypeopleId(peopleId));
		
	}
	@PostMapping("/assign")
	public ResponseEntity<String>assignPeople( @RequestParam Long peopleId,@RequestParam Long LicensesId ){
		peoService.assignLicenses(peopleId, LicensesId);
		return ResponseEntity.ok("Assign Success");
	}
}
	


