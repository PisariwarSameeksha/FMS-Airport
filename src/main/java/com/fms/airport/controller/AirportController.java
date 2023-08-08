package com.fms.airport.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fms.airport.DTO.AirportDTO;
import com.fms.airport.entity.Airport;
import com.fms.airport.exception.AirportAlreadyExistsException;
import com.fms.airport.exception.AirportNotFoundException;
import com.fms.airport.service.AirportService;

@RestController
public class AirportController {
	
	@Autowired
	private AirportService airportService;
	
	@PostMapping("/airport")
	public ResponseEntity<AirportDTO> addAirport(@RequestBody Airport newAirport) {
		AirportDTO airportDTO = airportService.addAirport(newAirport);
		return ResponseEntity.status(HttpStatus.OK).body(airportDTO);
	}
	
	@GetMapping("/airportname/{name}")
	public ResponseEntity<AirportDTO> getAirportByName(@RequestParam String name) throws AirportNotFoundException{
		AirportDTO airportDTO = airportService.getAirportByName(name);
		return ResponseEntity.status(HttpStatus.OK).body(airportDTO);
	}
	
	@GetMapping("/airport/{id}")
	public ResponseEntity<AirportDTO> getAirportById(@RequestParam String id) throws AirportNotFoundException{
		AirportDTO airportDTO = airportService.getAirportById(id);
		return ResponseEntity.status(HttpStatus.OK).body(airportDTO);
	}
	
	@GetMapping("/airports")
	public ResponseEntity<List<AirportDTO>>getAirports(){
		List<AirportDTO> airportDTO = airportService.getAllAirports();
		return ResponseEntity.status(HttpStatus.OK).body(airportDTO);
	}
	
	@DeleteMapping("/airport/{id}")
	public ResponseEntity<AirportDTO> deleteAirportById(@RequestParam String id) throws AirportNotFoundException{
		AirportDTO airportDTO = airportService.removeAirportById(id);
		return ResponseEntity.status(HttpStatus.OK).body(airportDTO);
	}
	
	

}
