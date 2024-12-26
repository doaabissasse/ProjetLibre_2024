package com.example.contactes_service.client;


import com.example.contactes_service.Entite.Adresse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// "adresse-service" correspond au nom du microservice Adresse dans Eureka ou votre configuration
@FeignClient(name = "adresse-service", url = "${application.config.adresse-url}")
public interface AdresseClient {

    @GetMapping("/{id}")
    Adresse getAdresseById(@PathVariable("id") long id);

    @DeleteMapping("/{id}")
    void deleteAdresse(@PathVariable("id") long id);
}