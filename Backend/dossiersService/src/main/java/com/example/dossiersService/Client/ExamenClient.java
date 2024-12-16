package com.example.dossiersService.Client;

import com.example.dossiersService.entity.ExamenDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "examen-service", url = "http://localhost:8091/api/examens")
public interface ExamenClient {

    @GetMapping("/dossier/{idDossier}")
    List<ExamenDTO> getExamensByDossierId(@PathVariable("idDossier") Long idDossier);
}