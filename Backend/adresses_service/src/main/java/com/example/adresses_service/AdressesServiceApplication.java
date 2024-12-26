package com.example.adresses_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class AdressesServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdressesServiceApplication.class, args);
	}

}
