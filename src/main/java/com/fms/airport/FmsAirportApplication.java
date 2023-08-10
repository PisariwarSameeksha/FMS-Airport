package com.fms.airport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories

public class FmsAirportApplication {

	public static void main(String[] args) {
		SpringApplication.run(FmsAirportApplication.class, args);
	}

}
