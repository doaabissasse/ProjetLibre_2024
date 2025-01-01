package com.example.users_service.auth;

import com.example.users_service.entities.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
 @Setter
@Builder
public class AuthenticationResponse {

    private String token;
    private String role;
    private User user; // Ajout de l'utilisateur pour le retour complet
}
