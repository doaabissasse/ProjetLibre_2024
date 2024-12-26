package com.example.contactes_service.Entite;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Adresse {
    private Long id;
    private String numVoie;
    private String nomVoie;
    private String codePostal;
    private String ville;
    private String commune;
}