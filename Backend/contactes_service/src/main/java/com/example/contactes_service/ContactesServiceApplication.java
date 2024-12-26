package com.example.contactes_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ContactesServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContactesServiceApplication.class, args);
	}

}
