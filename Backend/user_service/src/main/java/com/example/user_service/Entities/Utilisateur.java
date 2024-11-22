package com.example.user_service.Entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String email;
    private String nomComplet;
    private String profession;
    private String numTel;
    private String signature;
    private String role;
    private long fkIdLaboratoire;

    private String username;
    private String password;
}