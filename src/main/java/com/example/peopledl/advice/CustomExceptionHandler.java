package com.example.peopledl.advice;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.peopledl.dto.Response;
import com.example.peopledl.exception.LicensesNotFoundException;
import com.example.peopledl.exception.PeopleNotFoundException;


@RestControllerAdvice
public class CustomExceptionHandler {

	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ExceptionHandler
	public Response PeopleNotFoundExceptionHandling(PeopleNotFoundException ex) {
		return new Response(404, "ID Not Found", ex.getMessage());
	}
	
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ExceptionHandler
	public Response LicenseNotFoundExceptionHandling(LicensesNotFoundException ex) {
		return new Response(404, "ID Not Found", ex.getMessage());
	}
}