package com.example.labo_service.Entite;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Adresse {
    private String numVoie;
    private String nomVoie;
    private String codePostal;
    private String ville;
    private String commune;
}
