package com.example.analyse_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AnalyseServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnalyseServiceApplication.class, args);
	}

}
