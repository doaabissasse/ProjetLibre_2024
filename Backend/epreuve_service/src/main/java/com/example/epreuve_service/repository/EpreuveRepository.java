package com.example.epreuve_service.repository;

import com.example.epreuve_service.entity.epreuve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EpreuveRepository extends JpaRepository<epreuve, Long> {
}
