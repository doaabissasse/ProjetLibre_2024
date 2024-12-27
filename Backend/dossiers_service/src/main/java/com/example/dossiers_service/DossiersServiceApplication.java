package com.example.dossiers_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class DossiersServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DossiersServiceApplication.class, args);
    }

}
