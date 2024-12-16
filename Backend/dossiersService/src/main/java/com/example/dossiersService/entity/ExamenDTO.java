package com.example.dossiersService.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ExamenDTO {
    private Long id;
    private Long idDossier;
    private Long idEpreuve;
    private Long idTestAnalyse;
    private String resultat;

}