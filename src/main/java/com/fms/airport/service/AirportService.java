package com.fms.airport.service;

import java.util.List;

import com.fms.airport.DTO.AirportDTO;
import com.fms.airport.entity.Airport;
import com.fms.airport.exception.AirportAlreadyExistsException;
import com.fms.airport.exception.AirportNotFoundException;



public interface AirportService {

	String addAirport(AirportDTO newAirportDTO);

	AirportDTO getAirportById(String id) throws AirportNotFoundException;

	AirportDTO getAirportByName(String name) throws AirportNotFoundException;

	List<AirportDTO> getAllAirports();

	String removeAirportById(String id) throws AirportNotFoundException;
	
	
}
