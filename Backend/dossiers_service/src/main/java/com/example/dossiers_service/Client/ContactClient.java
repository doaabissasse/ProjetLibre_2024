package com.example.dossiers_service.Client;


import com.example.dossiers_service.Entite.contacte;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name= "contactes-service" , url="http://localhost:8084/contactes")
public interface ContactClient {
    @GetMapping("/adresse/{adresse_id}")
    List<contacte> findAllContratbyAdresse(@PathVariable("adresse_id") long adresseId);
}