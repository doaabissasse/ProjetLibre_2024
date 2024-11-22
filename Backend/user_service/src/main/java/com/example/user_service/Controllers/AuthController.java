package com.example.user_service.Controllers;

import com.example.user_service.Entities.Utilisateur;
import com.example.user_service.Services.UtilisateurService;
import com.example.user_service.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UtilisateurService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public String login(@RequestBody Utilisateur user) {
        Utilisateur existingUser = userService.findByUsername(user.getUsername()).orElseThrow(() -> new RuntimeException("User not found"));
        if (existingUser.getPassword().equals(user.getPassword())) {
            return jwtUtil.generateToken(user.getUsername());
        }
        throw new RuntimeException("Invalid credentials");
    }
}