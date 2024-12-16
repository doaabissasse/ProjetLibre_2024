package com.example.test_analyse_service.service;

import com.example.test_analyse_service.entity.TestAnalyse;
import com.example.test_analyse_service.repository.TestAnalyseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TestAnalyseService {

    private final TestAnalyseRepository testAnalyseRepository;

    @Autowired
    public TestAnalyseService(TestAnalyseRepository testAnalyseRepository) {
        this.testAnalyseRepository = testAnalyseRepository;
    }

    // Méthode pour sauvegarder un TestAnalyse
    public TestAnalyse saveTestAnalyse(TestAnalyse testAnalyse) {
        return testAnalyseRepository.save(testAnalyse);
    }

    // Méthode pour récupérer un TestAnalyse par ID
    public Optional<TestAnalyse> getTestAnalyseById(Long id) {
        return testAnalyseRepository.findById(id);
    }

    // Méthode pour récupérer tous les TestAnalyse
    public List<TestAnalyse> getAllTestAnalyses() {
        return testAnalyseRepository.findAll();
    }

    // Méthode pour récupérer les TestAnalyse par idAnalyse
    public List<TestAnalyse> getTestAnalysesByIdAnalyse(Long idAnalyse) {
        return testAnalyseRepository.findByIdAnalyse(idAnalyse);
    }

    // Méthode pour mettre à jour un TestAnalyse
    public TestAnalyse updateTestAnalyse(Long id, TestAnalyse updatedTestAnalyse) {
        Optional<TestAnalyse> existingTestAnalyse = testAnalyseRepository.findById(id);
        if (existingTestAnalyse.isPresent()) {
            TestAnalyse testAnalyse = existingTestAnalyse.get();
            testAnalyse.setNomTest(updatedTestAnalyse.getNomTest());
            testAnalyse.setSousEpreuve(updatedTestAnalyse.getSousEpreuve());
            testAnalyse.setIntervalMinDeReference(updatedTestAnalyse.getIntervalMinDeReference());
            testAnalyse.setIntervalMaxDeReference(updatedTestAnalyse.getIntervalMaxDeReference());
            testAnalyse.setUniteDeReference(updatedTestAnalyse.getUniteDeReference());
            testAnalyse.setDetails(updatedTestAnalyse.getDetails());
            return testAnalyseRepository.save(testAnalyse);
        } else {
            throw new RuntimeException("TestAnalyse not found with id " + id);
        }
    }

    // Méthode pour supprimer un TestAnalyse
    public void deleteTestAnalyse(Long id) {
        testAnalyseRepository.deleteById(id);
    }
}