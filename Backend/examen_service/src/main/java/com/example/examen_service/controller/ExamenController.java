package com.example.examen_service.controller;

import com.example.examen_service.entity.Examen;
import com.example.examen_service.service.ExamenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/examens")
public class ExamenController {

    @Autowired
    private ExamenService examenService;

    // Endpoint to create a new examen
    @PostMapping
    public ResponseEntity<Examen> createExamen(@RequestBody Examen examen) {
        Examen createdExamen = examenService.saveExamen(examen);
        return ResponseEntity.ok(createdExamen);
    }

    // Endpoint to retrieve an examen by ID
    @GetMapping("/{id}")
    public ResponseEntity<Examen> getExamenById(@PathVariable Long id) {
        Optional<Examen> examen = examenService.getExamenById(id);
        return examen.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint to retrieve all examens
    @GetMapping
    public ResponseEntity<List<Examen>> getAllExamens() {
        List<Examen> examens = examenService.getAllExamens();
        return ResponseEntity.ok(examens);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Examen> updateExamen(@PathVariable Long id, @RequestBody Examen updatedExamen) {
        try {
            Examen examen = examenService.updateExamen(id, updatedExamen);
            return ResponseEntity.ok(examen);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint to delete an examen
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExamen(@PathVariable Long id) {
        examenService.deleteExamen(id);
        return ResponseEntity.noContent().build();
    }

}