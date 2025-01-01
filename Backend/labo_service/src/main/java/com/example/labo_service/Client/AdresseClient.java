package com.example.labo_service.Client;

import com.example.labo_service.Entite.Adresse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "adresse-service", url = "${application.config.adresse-url}")
public interface AdresseClient {
    @GetMapping("/laboratoire")
    List<Adresse> findByIds(@RequestParam("ids") List<Long> ids);

}

