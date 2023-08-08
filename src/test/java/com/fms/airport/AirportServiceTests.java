package com.fms.airport;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fms.airport.entity.Airport;
import com.fms.airport.exception.AirportNotFoundException;
import com.fms.airport.service.AirportService;

@SpringBootTest
class AirportServiceTests {
	
	@Autowired
	private AirportService airportService;
	
	@Test
	void addAirportTest() {
		assertNotNull(airportService.addAirport(new Airport("V56786","Rajiv Gandhi","Shamshabad")));
	}
	
	@Test
	void getAllAirportsTest() {
		assertNotNull(airportService.getAllAirports());
	}
	
	@Test 
	void getAirportByIdTest()throws AirportNotFoundException{
		assertNotNull(airportService.getAirportById("V56786"));
	}
	
	@Test
	void getAirportByNameTest() throws AirportNotFoundException{
		assertNotNull(airportService.getAirportByName("Ragiv Gandhi"));
	}
	
	@Test
	void getAirportByIdExceptionTest() throws AirportNotFoundException{
		assertThrows(AirportNotFoundException.class, ()-> airportService.getAirportById("1234"));
	}
	
	@Test
	void getAirportByNameExceptionTest() throws AirportNotFoundException{
		assertThrows(AirportNotFoundException.class, ()-> airportService.getAirportByName("abcdsds"));
	}
	
	@Test
	void removeAirportByIdTest() throws AirportNotFoundException{
		assertNotNull(airportService.removeAirportById("V56786"));
	}
	
	@Test
	void removeAirportByIdExceptionTest() throws AirportNotFoundException{
		assertThrows(AirportNotFoundException.class, ()-> airportService.removeAirportById("1234"));
	}

	
}
