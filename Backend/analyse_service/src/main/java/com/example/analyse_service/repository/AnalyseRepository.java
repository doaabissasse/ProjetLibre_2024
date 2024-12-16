package com.example.analyse_service.repository;

import com.example.analyse_service.entity.Analyse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnalyseRepository extends JpaRepository<Analyse, Long> {
    List<Analyse> findByFkIdLaboratoire(Long fkIdLaboratoire);

}
