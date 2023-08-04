package com.fms.airport.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Airport {
	
	@Id
	String airportId;
	
	@NotNull
	String airportName;
	
	@NotNull
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
	public Airport(String airportId, String airportName, String airportLocation) {
		super();
		this.airportId = airportId;
		this.airportName = airportName;
		this.airportLocation = airportLocation;
	}
	
	
	

}
