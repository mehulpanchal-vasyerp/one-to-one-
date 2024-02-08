package com.example.peopledl.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.peopledl.dto.Peopledto;
import com.example.peopledl.exception.PeopleNotFoundException;
import com.example.peopledl.model.Licenses;
import com.example.peopledl.model.People;
import com.example.peopledl.repository.LicensesRepo;
import com.example.peopledl.repository.PeopleRepo1;

@Service
public class PeopleServiceimpl implements PeopleService {
	@Autowired
	public PeopleRepo1 peopleRepo;
	@Autowired
	public LicensesRepo licensesRepo;

	@Override
	public List<Peopledto> fetchAllPeople() {
		List<People> peoples = peopleRepo.findAll(); // dto
		List<Peopledto> peopledtos = new ArrayList<Peopledto>();
		for (People people : peoples) {
			Peopledto peopledto = new Peopledto();
			peopledto.setPeopleId(people.getPeopleId());
			peopledto.setPeopleName(people.getPeopleName());
			peopledto.setLicenses(people.getLicenses());
			peopledtos.add(peopledto);

		}
		return peopledtos;
	}

	@Override
	public Peopledto getPeopleBypeopleId(Long peopleId) {
		People people = peopleRepo.findById(peopleId)
				.orElseThrow(() -> new PeopleNotFoundException("People not found"));
		Peopledto peopledto = new Peopledto();
		peopledto.setPeopleId(people.getPeopleId()); // model to dto
		peopledto.setPeopleName(people.getPeopleName());

		return peopledto;
	}

	@Override
	public People deletePeopleBypeopleId(Long peopleId) {
		People people = peopleRepo.findById(peopleId)
				.orElseThrow(() -> new PeopleNotFoundException("People not found"));
		peopleRepo.deleteById(peopleId);
		return people;
	}

	@Override
	public boolean addPeople(Peopledto peopledto) {
		People people = new People(); // model object dto to model
		people.setPeopleId(peopledto.getPeopleId());
		people.setPeopleName(peopledto.getPeopleName());
		peopleRepo.save(people);
		return true;
	}

	@Override
	public boolean assignLicenses(Long peopleId, Long LicensesId) {
		People existingPeople = peopleRepo.findById(peopleId).get();
		Licenses licenses = licensesRepo.findById(LicensesId).get();
		if (existingPeople != null) {
			existingPeople.setLicenses(licenses);
			peopleRepo.save(existingPeople);

			return true;
		}

		return false;
	}

	private static final String UPLOAD_DIR1 = "C:\\upload file\\html\\";

	@Override
	public String uploadFile(MultipartFile file) {
		try {

			File directory = new File(UPLOAD_DIR1);
			if (!directory.exists()) {
				directory.mkdirs();
			}

			String fileName = file.getOriginalFilename();
			Path filePath = Paths.get(UPLOAD_DIR1).resolve(fileName);

			Files.write(filePath, file.getBytes(), StandardOpenOption.CREATE);

			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/people/download/")
					.path(fileName).toUriString();

			return "File uploaded successfully. Download URL: " + fileDownloadUri;
		} catch (IOException ex) {
			return "Could not upload the file: " + ex.getMessage();
		}
	}

	public ResponseEntity<Object> downloadFile(@PathVariable String fileName) {
		ResponseEntity<Object> response = null;
		try {

			Path filePath = Paths.get(UPLOAD_DIR1).resolve(fileName).normalize();
			File file = filePath.toFile();

			if (file.exists()) {
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
				headers.setContentDispositionFormData("attachment", fileName);

				response = ResponseEntity.ok().headers(headers).contentLength(file.length())
						.body(new FileSystemResource(file));
			} else {
				response = ResponseEntity.notFound().build();
			}
		} catch (Exception ex) {
			response = ResponseEntity.status(500).body("Failed to download the file: " + ex.getMessage());
		}

		return response;
	}

	public String uploadFile1(MultipartFile file) throws IOException {
		String fileName;
		try {

			File directory = new File(UPLOAD_DIR1);
			if (!directory.exists()) {
				directory.mkdirs();
			}

			if (file.getContentType().equals("text/plain")) {
				fileName = "hello.txt";
				Path filePath = Paths.get(UPLOAD_DIR1).resolve(fileName);
				if (!filePath.toFile().exists()) {
					filePath.toFile().createNewFile();
				}
				Files.write(filePath, file.getBytes(), StandardOpenOption.APPEND);
			} else {

				fileName = file.getOriginalFilename();
				Path filePath = Paths.get(UPLOAD_DIR1).resolve(fileName);
				Files.write(filePath, file.getBytes(), StandardOpenOption.CREATE);
			}

			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/people/download/")
					.path(fileName).toUriString();

			System.out.println(file.getContentType());

			return ("File uploaded successfully. Download URL: " + fileDownloadUri);
		} catch (IOException ex) {
			throw new IOException("Could not upload the file: " + ex.getMessage());
		}
	}
}