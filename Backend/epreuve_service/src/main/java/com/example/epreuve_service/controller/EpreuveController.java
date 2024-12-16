package com.example.epreuve_service.controller;


import com.example.epreuve_service.entity.epreuve;
import com.example.epreuve_service.service.EpreuveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/epreuves")
public class EpreuveController {

    @Autowired
    private EpreuveService epreuveService;

    @GetMapping
    public List<epreuve> getAllEpreuves() {
        return epreuveService.getAllEpreuves();
    }

    @GetMapping("/{id}")
    public ResponseEntity<epreuve> getEpreuveById(@PathVariable Long id) {
        epreuve epreuve = epreuveService.getEpreuveById(id);
        if (epreuve != null) {
            return ResponseEntity.ok(epreuve);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<epreuve> createEpreuve(@RequestBody epreuve epreuve) {
        epreuve createdEpreuve = epreuveService.createEpreuve(epreuve);
        return ResponseEntity.ok(createdEpreuve);
    }

    @PutMapping("/{id}")
    public ResponseEntity<epreuve> updateEpreuve(@PathVariable Long id, @RequestBody epreuve updatedEpreuve) {
        epreuve epreuve = epreuveService.updateEpreuve(id, updatedEpreuve);
        if (epreuve != null) {
            return ResponseEntity.ok(epreuve);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEpreuve(@PathVariable Long id) {
        epreuveService.deleteEpreuve(id);
        return ResponseEntity.noContent().build();
    }
}

