package com.example.adresses_service.service;

import com.example.adresses_service.Entite.Adresse;
import com.example.adresses_service.repository.AdresseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdresseService {

    @Autowired
    private AdresseRepository adresseRepository;

    public List<Adresse> getAllAdresses() {
        return adresseRepository.findAll();
    }

    public Optional<Adresse> getAdresseById(Long id) {
        return adresseRepository.findById(id);
    }

    public Adresse saveAdresse(Adresse adresse) {
        return adresseRepository.save(adresse);
    }

    public void deleteAdresse(Long id) {
        adresseRepository.deleteById(id);
    }

}