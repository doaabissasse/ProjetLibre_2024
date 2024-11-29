package com.example.dossiers_service.service;


import com.example.dossiers_service.Entite.Adresse;
import com.example.dossiers_service.repository.AdresseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdresseService {

    private final AdresseRepository adresseRepository;

    public AdresseService(AdresseRepository adresseRepository) {
        this.adresseRepository = adresseRepository;
    }

    // Créer une nouvelle adresse
    public Adresse createAdresse(Adresse adresse) {
        return adresseRepository.save(adresse);
    }

    // Récupérer toutes les adresses
    public List<Adresse> getAllAdresses() {
        return adresseRepository.findAll();
    }

    // Récupérer une adresse par ID
    public Optional<Adresse> getAdresseById(Long id) {
        return adresseRepository.findById(id);
    }

    // Récupérer des adresses par ville
    public List<Adresse> getAdressesByVille(String ville) {
        return adresseRepository.findByVille(ville);
    }

    // Supprimer une adresse
    public void deleteAdresse(Long id) {
        adresseRepository.deleteById(id);
    }
}

