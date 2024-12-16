package com.example.analyse_service.service;


import com.example.analyse_service.entity.Analyse;
import com.example.analyse_service.repository.AnalyseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnalyseService {

    @Autowired
    private AnalyseRepository analyseRepository;

    public List<Analyse> getAllAnalyses() {
        return analyseRepository.findAll();
    }

    public Analyse getAnalyseById(Long id) {
        return analyseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Analyse not found with id: " + id));
    }

    public Analyse createAnalyse(Analyse analyse) {
        return analyseRepository.save(analyse);
    }

    public Analyse updateAnalyse(Long id, Analyse updatedAnalyse) {
        Optional<Analyse> existingAnalyse = analyseRepository.findById(id);

        if (existingAnalyse.isPresent()) {
            Analyse analyse = existingAnalyse.get();
            analyse.setFkIdLaboratoire(updatedAnalyse.getFkIdLaboratoire());
            analyse.setNom(updatedAnalyse.getNom());
            analyse.setDescription(updatedAnalyse.getDescription());
            return analyseRepository.save(analyse);
        } else {
            throw new RuntimeException("Analyse not found with id: " + id);
        }
    }

    public void deleteAnalyse(Long id) {
        analyseRepository.deleteById(id);
    }
}
