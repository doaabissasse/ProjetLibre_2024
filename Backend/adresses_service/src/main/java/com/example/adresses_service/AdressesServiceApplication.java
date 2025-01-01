package com.example.adresses_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableFeignClients // Précisez le package contenant les clients Feign
@EnableConfigurationProperties
public class AdressesServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdressesServiceApplication.class, args);
	}

}
