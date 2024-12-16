package com.example.dossiers_service.service;


import com.example.dossiers_service.Client.ContactClient;
import com.example.dossiers_service.Entite.Adresse;
import com.example.dossiers_service.Entite.contacte;
import com.example.dossiers_service.repository.AdresseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdresseService {

    private final AdresseRepository adresseRepository;
    private final ContactClient contactFeignClient;


    @Autowired
    public AdresseService(AdresseRepository adresseRepository, ContactClient contactFeignClient) {
        this.adresseRepository = adresseRepository;
        this.contactFeignClient = contactFeignClient;
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

    public List<contacte> getContactByAdresseId(Long idAdresse) {
        return contactFeignClient.findAllContratbyAdresse(idAdresse);
    }

}

