package com.example.labo_service.repository;

import com.example.labo_service.Entite.Laboratoire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LaboratoireRepository extends JpaRepository <Laboratoire, Long> {
    List<Laboratoire> findByNomContainingIgnoreCase(String nom);
}
