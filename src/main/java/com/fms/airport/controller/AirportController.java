package com.fms.airport.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fms.airport.DTO.AirportDTO;
import com.fms.airport.DTO.ScheduleFlightDTO;
import com.fms.airport.exception.AirportNotFoundException;
import com.fms.airport.service.AirportService;

@RestController 
@RequestMapping("/api/airports")
public class AirportController {

	private static final Logger logger = LoggerFactory.getLogger(AirportController.class);

	@Autowired
	private AirportService airportService;

	@PostMapping("/airports")
	ResponseEntity<String> addAirport(@RequestBody AirportDTO airportDTO) {

		logger.info("Received request to add a airport : {}", airportDTO);
		airportService.addAirport(airportDTO);
		logger.info("Airport added: {}", airportDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body("Added successfully");
	}

	@GetMapping("/airports/{name}")
	public ResponseEntity<AirportDTO> getAirportByName(@RequestParam String name) throws AirportNotFoundException {

		try {
			logger.info("Received request to fetch details for airport with name: {}", name);
			AirportDTO airportDTO = airportService.getAirportByName(name);
			logger.info("Airport details fetched successfully for airport with name : {}", name);
			return ResponseEntity.status(HttpStatus.OK).body(airportDTO);
		} catch (AirportNotFoundException e) {
			logger.warn("Airport with name {} not found", name);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	@GetMapping("/airport/{id}")
	public ResponseEntity<AirportDTO> getAirportById(@RequestParam String id) {

		try {
			logger.info("Received request to fetch details for airport with id: {}", id);
			AirportDTO airportDTO = airportService.getAirportById(id);
			logger.info("Airport details fetched successfully for airport with id : {}", id);
			return ResponseEntity.status(HttpStatus.OK).body(airportDTO);
		} catch (AirportNotFoundException e) {
			logger.warn("Flight with id {} not found", id);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	@GetMapping("/airports")
	ResponseEntity<List<AirportDTO>> getAllAirports() {

		logger.info("Received request to fetch all flights");
		List<AirportDTO> airports = airportService.getAllAirports();
		logger.info("Fetched details of {} airports", airports.size());
		return ResponseEntity.status(HttpStatus.OK).body(airports);
	}

	@DeleteMapping("/airports/{id}")
	public ResponseEntity<String> deleteAirportById(@RequestParam String id) {

		try {
			logger.info("Received request to remove flight with Id : {}", id);
			airportService.removeAirportById(id);
			logger.info("Flight with Id {} removed successfully", id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Deleted");
		} catch (AirportNotFoundException e) {
			logger.warn("Flight with Id {} not found for removal", id);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aiport not found");
		}
	}
	
	@GetMapping("/airports/schedules")
	public ResponseEntity<List<ScheduleFlightDTO>> getAllSchedules(){
		logger.info("Received a request to get scheduled flights");
		List<ScheduleFlightDTO> flights = airportService.getNumberOfScheduleFlights();
		return ResponseEntity.status(HttpStatus.OK).body(flights);
		
	}

}
