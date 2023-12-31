package com.fms.airport.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fms.airport.exception.AirportAlreadyExistsException;
import com.fms.airport.exception.AirportNotFoundException;

@RestControllerAdvice
public class AirportControllerAdvice {
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex){
		Map<String, String> errors=new HashMap<>();
		 ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}
	
	@ExceptionHandler(AirportNotFoundException.class)
	@ResponseStatus(value=HttpStatus.NOT_FOUND)
	public String handleCustomerExceptions(AirportNotFoundException e) {
		return e.getMessage();
	}
	
	@ExceptionHandler(AirportAlreadyExistsException.class)
	@ResponseStatus(value=HttpStatus.NOT_FOUND)
	public String handleCustomerExceptions(AirportAlreadyExistsException e) {
		return e.getMessage();
	}

}
