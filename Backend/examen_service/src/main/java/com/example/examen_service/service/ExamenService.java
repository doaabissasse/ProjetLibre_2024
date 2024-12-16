package com.example.examen_service.service;

import com.example.examen_service.entity.Examen;
import com.example.examen_service.repository.ExamenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExamenService {

    @Autowired
    private ExamenRepository examenRepository;

    public Examen saveExamen(Examen examen) {
        return examenRepository.save(examen);
    }

    public Optional<Examen> getExamenById(Long id) {
        return examenRepository.findById(id);
    }

    public List<Examen> getAllExamens() {
        return examenRepository.findAll();
    }

    public Examen updateExamen(Long id, Examen updatedExamen) {
        Optional<Examen> existingExamen = examenRepository.findById(id);
        if (existingExamen.isPresent()) {
            Examen examen = existingExamen.get();
            examen.setIdDossier(updatedExamen.getIdDossier());
            examen.setIdEpreuve(updatedExamen.getIdEpreuve());
            examen.setIdTestAnalyse(updatedExamen.getIdTestAnalyse());
            examen.setResultat(updatedExamen.getResultat());
            return examenRepository.save(examen);
        } else {
            throw new RuntimeException("Examen not found with id " + id);
        }
    }

    public void deleteExamen(Long id) {
        examenRepository.deleteById(id);
    }
}