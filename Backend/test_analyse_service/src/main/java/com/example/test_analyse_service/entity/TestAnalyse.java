package com.example.test_analyse_service.entity;

import jakarta.persistence.*;

@Entity
@Table(schema = "testAnalyse")
public class TestAnalyse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idAnalyse;          // L'ID de l'analyse associée
    private String nomTest;          // Nom du test
    private String sousEpreuve;      // Sous-épreuve du test
    private Double intervalMinDeReference; // Intervalle min de référence
    private Double intervalMaxDeReference; // Intervalle max de référence
    private String uniteDeReference;   // Unité de référence
    private String details;           // Détails supplémentaires

    // Constructeurs
    public TestAnalyse() {
    }

    public TestAnalyse(Long id, Long idAnalyse, String nomTest, String sousEpreuve, Double intervalMinDeReference, Double intervalMaxDeReference, String uniteDeReference, String details) {
        this.id = id;
        this.idAnalyse = idAnalyse;
        this.nomTest = nomTest;
        this.sousEpreuve = sousEpreuve;
        this.intervalMinDeReference = intervalMinDeReference;
        this.intervalMaxDeReference = intervalMaxDeReference;
        this.uniteDeReference = uniteDeReference;
        this.details = details;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdAnalyse() {
        return idAnalyse;
    }

    public void setIdAnalyse(Long idAnalyse) {
        this.idAnalyse = idAnalyse;
    }

    public String getNomTest() {
        return nomTest;
    }

    public void setNomTest(String nomTest) {
        this.nomTest = nomTest;
    }

    public String getSousEpreuve() {
        return sousEpreuve;
    }

    public void setSousEpreuve(String sousEpreuve) {
        this.sousEpreuve = sousEpreuve;
    }

    public Double getIntervalMinDeReference() {
        return intervalMinDeReference;
    }

    public void setIntervalMinDeReference(Double intervalMinDeReference) {
        this.intervalMinDeReference = intervalMinDeReference;
    }

    public Double getIntervalMaxDeReference() {
        return intervalMaxDeReference;
    }

    public void setIntervalMaxDeReference(Double intervalMaxDeReference) {
        this.intervalMaxDeReference = intervalMaxDeReference;
    }

    public String getUniteDeReference() {
        return uniteDeReference;
    }

    public void setUniteDeReference(String uniteDeReference) {
        this.uniteDeReference = uniteDeReference;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}