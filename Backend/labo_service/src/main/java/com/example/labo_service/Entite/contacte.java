package com.example.labo_service.Entite;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class contacte {
    private long id;
    private long idLaboratoire;
    private long idAdresse;
    private String tel;
    private String fax;
    private String email;
}