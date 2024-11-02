package com.example.labo_service.Client;

import com.example.labo_service.Entite.contacte;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name= "contactes-service", url="${application.config.contacts-url}")
public interface ContactClient {
    @GetMapping("/laboratoire/{labo_id}")
    List<contacte> findAllContratbyLabo(@PathVariable("labo_id") long laboID);
}