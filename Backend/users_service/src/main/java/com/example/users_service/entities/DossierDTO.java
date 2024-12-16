package com.example.users_service.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DossierDTO {
    private Long id;
    private Long idPatient;
    private Long idUtilisateur;
    private LocalDate date;
}