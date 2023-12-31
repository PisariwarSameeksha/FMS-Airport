package com.fms.airport;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import com.fms.airport.DTO.AirportDTO;
import com.fms.airport.entity.Airport;
import com.fms.airport.exception.AirportAlreadyExistsException;
import com.fms.airport.exception.AirportNotFoundException;
import com.fms.airport.repository.AirportRepository;
import com.fms.airport.service.AirportService;
import com.fms.airport.service.AirportServiceImpl;


@SpringBootTest
class AirportServiceTests {
	
	@InjectMocks
	private AirportServiceImpl airportService;
	
	@Mock
	private AirportRepository airportRepository;
	
	@Mock
	private ModelMapper modelMapper;
	
	
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	@DisplayName("Test to add")
	@Order(1)
	void addAirportTest1() throws AirportAlreadyExistsException {
		
		AirportDTO airportDTO = new AirportDTO();
		airportDTO.setAirportName("RGI airport");
		airportDTO.setAirportLocation("Hyderabad");
		
	
		String result = airportService.addAirport(airportDTO);
		assertEquals("new Airport successfully added", result);
	}
	
	
	@Test
	@DisplayName("Test to Modify Exception")
	@Order(3)
	void ModifyAirportByIDNotFoundExceptionTest() {
		
		Integer airportId= 1;				
		assertThrows(AirportNotFoundException.class, 
				()-> airportService.changeAirportDetails(new AirportDTO(), airportId));
	}
	



	   @Test
	   @DisplayName("Test to Find Flight")
	   @Order(7)
	   void testGetAirportById() throws AirportNotFoundException, AirportAlreadyExistsException {
	        
		   Integer Id = 12;

	        AirportService airportService = mock(AirportService.class);

	 
	        AirportDTO existingAirport = new AirportDTO();
	        existingAirport.setAirportId(Id);
	        existingAirport.setAirportName("Test Airport");
	        existingAirport.setAirportLocation("Test Location");
	        

	        when(airportService.addAirport(existingAirport)).thenReturn("Airport added successfully");
	        when(airportService.getAirportById(Id)).thenReturn(existingAirport);

	      
	        String addAirportResponse = airportService.addAirport(existingAirport);
	        assertNotNull(addAirportResponse);
	        assertEquals("Airport added successfully", addAirportResponse);

	     
	        AirportDTO result = airportService.getAirportById(Id);

	        assertEquals(existingAirport.getAirportLocation(), result.getAirportLocation());
	        assertEquals(existingAirport.getAirportName(), result.getAirportName());
	       
	    }

	    @Test
	    @DisplayName("Test to Find Airport Exception")
		@Order(8)
	    void testGetFlightByFlightNumberFlightNumberNotFound() {
	    
	        Integer Id = 12;;
	        
	        assertThrows(AirportNotFoundException.class,
	                () -> airportService.getAirportById(Id));
	    }

	    @Test
	    @DisplayName("Test to Get all Flight")
		@Order(9)
	    void testGetAllFlights() {
	       
	    	Airport airport1 = new Airport();
	    	airport1.setAirportName("Test name");
	        airport1.setAirportLocation("Test location");
	        

	        Airport airport2 = new Airport();
	    	airport2.setAirportName("Test name1");
	        airport2.setAirportLocation("Test location1");
	        

	        Mockito.when(airportRepository.findAll()).thenReturn(Arrays.asList(airport1, airport2));
	        List<AirportDTO> result = airportService.getAllAirports();

	        assertEquals(2, result.size());
	    }
	
	
	@Test
	void addAirportTest() throws AirportAlreadyExistsException {
		assertNotNull(airportService.addAirport(new AirportDTO(56786,"Rajiv Gandhi","Shamshabad")));
	}
	
	@Test
	void getAllAirportsTest() {
		assertNotNull(airportService.getAllAirports());
	}
	

	
	 
	
	@Test
	void getAirportByIdExceptionTest() throws AirportNotFoundException{
		assertThrows(AirportNotFoundException.class, ()-> airportService.getAirportById(1234));
	}
	
	

	
	

	
}
