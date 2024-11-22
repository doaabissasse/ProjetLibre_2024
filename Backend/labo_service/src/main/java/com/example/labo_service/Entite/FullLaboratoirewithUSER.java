package com.example.labo_service.Entite;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FullLaboratoirewithUSER {
    private String nom;
    private String logo;
    private String nrc;
    private boolean active;
    private LocalDate dateActivation;
    private List<user> users; // Utiliser la classe Contact ici
}
