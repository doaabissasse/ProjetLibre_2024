package com.example.dossiersService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class DossiersServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DossiersServiceApplication.class, args);
	}

}
