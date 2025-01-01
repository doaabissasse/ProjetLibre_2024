package com.example.patient_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableConfigurationProperties
public class PatientServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PatientServiceApplication.class, args);
	}

}
