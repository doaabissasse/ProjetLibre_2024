package com.example.users_service.Client;

import com.example.users_service.entities.DossierDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

// Nom du microservice dossier (assurez-vous que le nom correspond à votre configuration Spring Cloud)
@FeignClient(name = "dossiersService", url = "http://localhost:8093/api/dossiers")
public interface DossierFeignClient {

    // Endpoint pour récupérer les dossiers par idUtilisateur
    @GetMapping("/by-utilisateur/{idUtilisateur}")
    List<DossierDTO> getDossiersByIdUtilisateur(@PathVariable Long idUtilisateur);
}