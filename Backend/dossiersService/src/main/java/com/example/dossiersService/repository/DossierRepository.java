package com.example.dossiersService.repository;

import com.example.dossiersService.entity.Dossier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DossierRepository extends JpaRepository<Dossier, Long> {
    Optional<Dossier> findById(Long id);

    List<Dossier> findByIdPatient(Long idPatient);
}
