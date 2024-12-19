package com.example.epreuve_service.Client;

import com.example.epreuve_service.entity.ExamenDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "examen-service", url = "http://localhost:8091/api/examens")
public interface ExamenClient {

    @GetMapping("/epreuve/{idEpreuve}")
    List<ExamenDTO> getExamensByEpreuveId(@PathVariable("idEpreuve") Long idEpreuve);
}