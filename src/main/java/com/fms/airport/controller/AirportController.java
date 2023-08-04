package com.fms.airport.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fms.airport.DTO.AirportDTO;
import com.fms.airport.entity.Airport;
import com.fms.airport.exception.AirportAlreadyExistsException;
import com.fms.airport.service.AirportService;

@RestController
public class AirportController {
	
	@Autowired
	private AirportService airportService;
	
	@PostMapping("/airport")
	public ResponseEntity<AirportDTO> addAirport(@RequestBody Airport newAirport) throws AirportAlreadyExistsException{
		AirportDTO airportDTO = airportService.addAirport(newAirport);
		return ResponseEntity.status(HttpStatus.OK).body(airportDTO);
	}
	
	

}
