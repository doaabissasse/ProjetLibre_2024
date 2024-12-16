package com.example.test_analyse_service.repository;

import com.example.test_analyse_service.entity.TestAnalyse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestAnalyseRepository extends JpaRepository<TestAnalyse, Long> {
    List<TestAnalyse> findByIdAnalyse(Long idAnalyse);
}