package com.example.labo_service.Entite;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="laboratoire")
public class Laboratoire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nom;
    private String logo;
    private String nrc;
    private boolean active;

    @Column(name = "date_activation")
    private LocalDate dateActivation;
}
