package com.fms.airport.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fms.airport.DTO.AirportDTO;
import com.fms.airport.DTO.ScheduleFlightDTO;
import com.fms.airport.entity.Airport;
import com.fms.airport.exception.AirportAlreadyExistsException;
import com.fms.airport.exception.AirportNotFoundException;
import com.fms.airport.repository.AirportRepository;

import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.Collections;

@Service
public class AirportServiceImpl implements AirportService{

	@Autowired
	private AirportRepository airportRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private WebClient webclient;
	
	@Override
	public String  addAirport(AirportDTO newAirportDTO){
		
		Airport airport = modelMapper.map(newAirportDTO, Airport.class);
		
		airportRepository.save(airport);
		
		return "new Airport successfully added";
	}
	

	@Override
	public AirportDTO getAirportById(String id) throws AirportNotFoundException {
		Optional<Airport> optAirport = this.airportRepository.findByAirportId(id);
		if(optAirport.isEmpty()) {
			throw new AirportNotFoundException("Airport doesn't exist for given id");
		}
		Airport airport = optAirport.get();
		
		return modelMapper.map(airport, AirportDTO.class);
		
	}
	

	@Override
	public List<AirportDTO> getAllAirports() {
		List<Airport> Airport = airportRepository.findAll();

		List<AirportDTO> airportDTO = Airport.stream()

		.map(list -> modelMapper

		.map(list, AirportDTO.class ))

		.collect(Collectors.toList());

		return airportDTO;
		
		
	}


//	@Override
//	public AirportDTO changeNameById(String newName, String id) throws AirportNotFoundException {
//		
//		return null;
//	}
//	

	@Override
	public String removeAirportById(String id) throws AirportNotFoundException {
		Optional<Airport> optAirport = this.airportRepository.findByAirportId(id);
		if(optAirport == null) {
			throw new AirportNotFoundException("Airport doesn't exist for given id");
		}
		Airport airport = optAirport.get();
		airportRepository.delete(airport);
		
		
		return "Airport deleted successfully";
	}
	
	@Override
	public AirportDTO getAirportByName(String name) throws AirportNotFoundException {
		Optional<Airport> optAirport = this.airportRepository.findByAirportName(name);
		if(optAirport.isEmpty()) {
			throw new AirportNotFoundException("Airport doesn't exist for given id");
		}
		Airport airport = optAirport.get();
		
		return modelMapper.map(airport, AirportDTO.class);
	}


	@Override
	public List<ScheduleFlightDTO> getNumberOfScheduleFlights() {
		Mono<ScheduleFlightDTO[]> response = webclient.get()
				.uri("http://localhost:8093/api/scheduleFlight/schedules")
				.accept(MediaType.APPLICATION_JSON)
		.retrieve()
		. bodyToMono(ScheduleFlightDTO[].class).log();
		
		ScheduleFlightDTO[] scheduleFlights = response.block();
		
		
		return Arrays.stream(scheduleFlights)
				.collect(Collectors.toList());
	}
	

}
