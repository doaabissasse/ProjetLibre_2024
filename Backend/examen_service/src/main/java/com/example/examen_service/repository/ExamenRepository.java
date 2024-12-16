package com.example.examen_service.repository;

import com.example.examen_service.entity.Examen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamenRepository extends JpaRepository<Examen, Long> {
    List<Examen> findByIdDossier(Long idDossier);
    List<Examen> findByIdEpreuve(Long idEpreuve);
    List<Examen> findByIdTestAnalyse(Long idTestAnalyse);
}