package com.example.adresses_service.Entite;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "adresse")
@Data
public class Adresse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numVoie;
    private String nomVoie;
    private String codePostal;
    private String ville;
    private String commune;
}