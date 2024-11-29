package com.example.dossiers_service.controlleur;


import com.example.dossiers_service.Entite.Adresse;
import com.example.dossiers_service.service.AdresseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/adresses")
public class AdresseController {

    private final AdresseService adresseService;

    public AdresseController(AdresseService adresseService) {
        this.adresseService = adresseService;
    }

    // Ajouter une nouvelle adresse
    @PostMapping
    public ResponseEntity<Adresse> createAdresse(@RequestBody Adresse adresse) {
        Adresse savedAdresse = adresseService.createAdresse(adresse);
        return ResponseEntity.ok(savedAdresse);
    }

    // Récupérer toutes les adresses
    @GetMapping
    public ResponseEntity<List<Adresse>> getAllAdresses() {
        List<Adresse> adresses = adresseService.getAllAdresses();
        return ResponseEntity.ok(adresses);
    }

    // Récupérer une adresse par ID
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Adresse>> getAdresseById(@PathVariable Long id) {
        Optional<Adresse> adresse = adresseService.getAdresseById(id);
        return adresse.isPresent() ? ResponseEntity.ok(adresse) : ResponseEntity.notFound().build();
    }

    // Récupérer des adresses par ville
    @GetMapping("/ville/{ville}")
    public ResponseEntity<List<Adresse>> getAdressesByVille(@PathVariable String ville) {
        List<Adresse> adresses = adresseService.getAdressesByVille(ville);
        return ResponseEntity.ok(adresses);
    }

    // Supprimer une adresse par ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdresse(@PathVariable Long id) {
        adresseService.deleteAdresse(id);
        return ResponseEntity.noContent().build();
    }
}

