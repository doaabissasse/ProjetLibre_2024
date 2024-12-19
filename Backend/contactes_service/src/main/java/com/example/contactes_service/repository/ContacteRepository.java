package com.example.contactes_service.repository;

import com.example.contactes_service.Entite.contacte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContacteRepository extends JpaRepository <contacte, Long> {
    List<contacte> findAllByIdLaboratoire(long idLaboratoire);
    List<contacte> findAllByIdAdresse(long idAdresse);

}
