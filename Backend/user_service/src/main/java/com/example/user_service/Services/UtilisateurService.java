package com.example.user_service.Services;

import com.example.user_service.Entities.Utilisateur;
import com.example.user_service.Repositories.UtilisateurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UtilisateurService {
    private final UtilisateurRepository utilisateurRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurRepository.findAll();
    }

    public Utilisateur getUtilisateurById(long id) {
        return utilisateurRepository.findById(id).orElse(null);
    }

    public Utilisateur saveUser(Utilisateur user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return utilisateurRepository.save(user);
    }

    public List<Utilisateur> getAllUtilisateursByLabo(long laboId) {
        return utilisateurRepository.findAllUtilisateursByFkIdLaboratoire(laboId);
    }

    public Optional<Utilisateur> findByUsername(String username) {
        return utilisateurRepository.findByUsername(username);
    }

}