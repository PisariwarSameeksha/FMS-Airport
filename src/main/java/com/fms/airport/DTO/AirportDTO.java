package com.fms.airport.DTO;

public class AirportDTO {
	
	String airportId;
	String airportName;
	String airportLocation;
	public String getAirportId() {
		return airportId;
	}
	public void setAirportId(String airportId) {
		this.airportId = airportId;
	}
	public String getAirportName() {
		return airportName;
	}
	public void setAirportName(String airportName) {
		this.airportName = airportName;
	}
	public String getAirportLocation() {
		return airportLocation;
	}
	public void setAirportLocation(String airportLocation) {
		this.airportLocation = airportLocation;
	}
	
	
	public AirportDTO() {
		super();
	}
	public AirportDTO(String airportId, String airportName, String airportLocation) {
		super();
		this.airportId = airportId;
		this.airportName = airportName;
		this.airportLocation = airportLocation;
	}

}
