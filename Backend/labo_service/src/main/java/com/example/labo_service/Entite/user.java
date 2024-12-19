package com.example.labo_service.Entite;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class user {

    private String email;
    private String nomComplet;
    private String profession;
    private String numTel;
    private String signature;

}

