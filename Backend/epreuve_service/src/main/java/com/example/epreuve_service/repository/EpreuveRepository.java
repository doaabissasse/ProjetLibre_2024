package com.example.epreuve_service.repository;

import com.example.epreuve_service.entity.epreuve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EpreuveRepository extends JpaRepository<epreuve, Long> {
    List<epreuve> findByIdAnalyse(Long idAnalyse);

}
