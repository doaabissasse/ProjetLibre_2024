package com.example.analyse_service.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TestAnalyse {
    private Long id;
    private Long idAnalyse;          // L'ID de l'analyse associée
    private String nomTest;          // Nom du test
    private String sousEpreuve;      // Sous-épreuve du test
    private Double intervalMinDeReference; // Intervalle min de référence
    private Double intervalMaxDeReference; // Intervalle max de référence
    private String uniteDeReference;   // Unité de référence
    private String details;
}
