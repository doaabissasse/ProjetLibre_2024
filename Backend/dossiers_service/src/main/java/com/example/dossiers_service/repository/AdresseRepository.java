package com.example.dossiers_service.repository;

import com.example.dossiers_service.Entite.Adresse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdresseRepository extends JpaRepository<Adresse, Long> {
    List<Adresse> findByCodePostal(String codePostal);
    List<Adresse> findByVille(String ville);
}

