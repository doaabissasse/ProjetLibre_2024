package com.example.test_analyse_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TestAnalyseServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestAnalyseServiceApplication.class, args);
	}

}
