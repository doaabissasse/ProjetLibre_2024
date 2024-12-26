package com.example.adresses_service.repository;

import com.example.adresses_service.Entite.Adresse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdresseRepository extends JpaRepository<Adresse, Long> {

}

