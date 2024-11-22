package com.example.user_service.Repositories;

import com.example.user_service.Entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur,Long> {

    List<Utilisateur> findAllUtilisateursByFkIdLaboratoire( long fkIdLaboratoire);
    Optional<Utilisateur> findByUsername(String username);
}