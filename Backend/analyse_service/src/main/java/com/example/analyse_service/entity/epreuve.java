package com.example.analyse_service.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
public class epreuve {
    private Long id;

    private Long idAnalyse;

    private String nom;
}
