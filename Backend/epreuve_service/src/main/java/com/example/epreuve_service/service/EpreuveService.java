package com.example.epreuve_service.service;


import com.example.epreuve_service.entity.epreuve;
import com.example.epreuve_service.repository.EpreuveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EpreuveService {

    @Autowired
    private EpreuveRepository epreuveRepository;

    public List<epreuve> getAllEpreuves() {
        return epreuveRepository.findAll();
    }

    public epreuve getEpreuveById(Long id) {
        return epreuveRepository.findById(id).orElse(null);
    }

    public epreuve createEpreuve(epreuve epreuve) {
        return epreuveRepository.save(epreuve);
    }

    public epreuve updateEpreuve(Long id, epreuve updatedEpreuve) {
        return epreuveRepository.findById(id).map(existingEpreuve -> {
            existingEpreuve.setNom(updatedEpreuve.getNom()); // Utilisation correcte de getNom()
            existingEpreuve.setIdAnalyse(updatedEpreuve.getIdAnalyse());
            return epreuveRepository.save(existingEpreuve);
        }).orElseThrow(() -> new RuntimeException("Epreuve not found with id: " + id));
    }

    public void deleteEpreuve(Long id) {
        epreuveRepository.deleteById(id);
    }
}
