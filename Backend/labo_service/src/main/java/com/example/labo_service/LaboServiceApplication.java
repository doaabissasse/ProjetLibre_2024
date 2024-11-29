package com.example.labo_service;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableFeignClients // Précisez le package contenant les clients Feign
@EnableConfigurationProperties
public class LaboServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(LaboServiceApplication.class, args);
	}
}

