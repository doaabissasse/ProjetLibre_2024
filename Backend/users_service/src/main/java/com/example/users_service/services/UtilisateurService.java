package com.example.users_service.services;

import com.example.users_service.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurService {

    @Autowired
    private com.example.users_service.repositories.UserRepository utilisateurRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Optional<User> findByUsername(String email) {
        return utilisateurRepository.findByEmail(email);
    }

    public List<User> getAllUtilisateurs() {
        return utilisateurRepository.findAll();
    }

    public User getUtilisateurById(Integer id) {
        return utilisateurRepository.findById(id).orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));
    }

    public User createUtilisateur(User utilisateur) {
        utilisateur.setPassword(passwordEncoder.encode(utilisateur.getPassword()));
        return utilisateurRepository.save(utilisateur);
    }


    public List<User> getAllUtilisateursByLabo(long fkIdLaboratoire) {
        return utilisateurRepository.findAllUtilisateursByFkIdLaboratoire(fkIdLaboratoire);
    }


}
