package com.example.peopledl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.peopledl.dto.Licensesdto;
import com.example.peopledl.dto.Response;
import com.example.peopledl.model.People;
import com.example.peopledl.service.LicensesService;

@RestController
@RequestMapping("/api/licenses")
public class LicensesController {
	@Autowired
	private LicensesService licensesService;
	
	@GetMapping("/all")
	public ResponseEntity<List<Licensesdto>> getAllLicenses() {

		return ResponseEntity.ok(licensesService.getallLicenses());
	}

	@PostMapping("/add")
	public ResponseEntity<String> addlicenses(@RequestBody Licensesdto licensesdto) {
		licensesService.addLicenses(licensesdto);
		return ResponseEntity.ok("success");
	}


	@DeleteMapping("/delete")
	public Response deleteLicensesById(@RequestParam Long LicensesId) {
		licensesService.deleteLicensesById(LicensesId);
		return new Response(200,"success","Delete Suuccessful");

	}

	@GetMapping("/get")
	public Response get(@RequestParam Long LicensesId) {
		Licensesdto licensesdto=licensesService.getLicensesById(LicensesId);
		return new Response(200,"success",licensesdto);

	}
}
	



