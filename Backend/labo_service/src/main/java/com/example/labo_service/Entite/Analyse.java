package com.example.labo_service.Entite;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Analyse {
    private Long id;
    private Long fkIdLaboratoire;
    private String nom;
    private String description;
}
