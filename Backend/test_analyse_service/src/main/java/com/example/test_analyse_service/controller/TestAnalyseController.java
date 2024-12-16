package com.example.test_analyse_service.controller;

import com.example.test_analyse_service.entity.ExamenDTO;
import com.example.test_analyse_service.entity.TestAnalyse;
import com.example.test_analyse_service.service.TestAnalyseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/test-analyses")
public class TestAnalyseController {

    private final TestAnalyseService testAnalyseService;

    @Autowired
    public TestAnalyseController(TestAnalyseService testAnalyseService) {
        this.testAnalyseService = testAnalyseService;
    }

    // Endpoint pour créer un nouveau TestAnalyse
    @PostMapping
    public ResponseEntity<TestAnalyse> createTestAnalyse(@RequestBody TestAnalyse testAnalyse) {
        TestAnalyse createdTestAnalyse = testAnalyseService.saveTestAnalyse(testAnalyse);
        return ResponseEntity.ok(createdTestAnalyse);
    }

    // Endpoint pour récupérer un TestAnalyse par ID
    @GetMapping("/{id}")
    public ResponseEntity<TestAnalyse> getTestAnalyseById(@PathVariable Long id) {
        Optional<TestAnalyse> testAnalyse = testAnalyseService.getTestAnalyseById(id);
        return testAnalyse.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint pour récupérer tous les TestAnalyse
    @GetMapping
    public ResponseEntity<List<TestAnalyse>> getAllTestAnalyses() {
        List<TestAnalyse> testAnalyses = testAnalyseService.getAllTestAnalyses();
        return ResponseEntity.ok(testAnalyses);
    }

    @GetMapping("/by-analyse/{idAnalyse}")
    public List<TestAnalyse> getTestsByAnalyseId(@PathVariable Long idAnalyse) {
        return testAnalyseService.getTestsByIdAnalyse(idAnalyse);
    }

    // Endpoint pour mettre à jour un TestAnalyse
    @PutMapping("/{id}")
    public ResponseEntity<TestAnalyse> updateTestAnalyse(@PathVariable Long id, @RequestBody TestAnalyse updatedTestAnalyse) {
        try {
            TestAnalyse testAnalyse = testAnalyseService.updateTestAnalyse(id, updatedTestAnalyse);
            return ResponseEntity.ok(testAnalyse);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint pour supprimer un TestAnalyse
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTestAnalyse(@PathVariable Long id) {
        testAnalyseService.deleteTestAnalyse(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/test-analyse/{idTestAnalyse}/examens")
    public List<ExamenDTO> getExamensByTestAnalyse(@PathVariable Long idTestAnalyse) {
        return testAnalyseService.getExamensByTestAnalyse(idTestAnalyse);
    }
}

