package com.example.analyse_service.controller;


import com.example.analyse_service.entity.Analyse;
import com.example.analyse_service.entity.TestAnalyse;
import com.example.analyse_service.entity.epreuve;
import com.example.analyse_service.service.AnalyseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/analyses")
public class AnalyseController {

    @Autowired
    private AnalyseService analyseService;

    @GetMapping
    public List<Analyse> getAllAnalyses() {
        return analyseService.getAllAnalyses();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Analyse> getAnalyseById(@PathVariable Long id) {
        Analyse analyse = analyseService.getAnalyseById(id);
        return ResponseEntity.ok(analyse);
    }

    @PostMapping
    public ResponseEntity<Analyse> createAnalyse(@RequestBody Analyse analyse) {
        Analyse createdAnalyse = analyseService.createAnalyse(analyse);
        return ResponseEntity.ok(createdAnalyse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Analyse> updateAnalyse(@PathVariable Long id, @RequestBody Analyse updatedAnalyse) {
        Analyse analyse = analyseService.updateAnalyse(id, updatedAnalyse);
        return ResponseEntity.ok(analyse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnalyse(@PathVariable Long id) {
        analyseService.deleteAnalyse(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-laboratoire/{idLabo}")
    public List<Analyse> getAnalysesByLaboratoireId(@PathVariable Long idLabo) {
        return analyseService.getAnalysesByLaboratoireId(idLabo);
    }

    @GetMapping("/{idAnalyse}/epreuves")
    public List<epreuve> getEpreuvesByAnalyseId(@PathVariable Long idAnalyse) {
        return analyseService.getEpreuvesByAnalyseId(idAnalyse);
    }
    @GetMapping("/{idAnalyse}/tests")
    public List<TestAnalyse> getTestsByAnalyseId(@PathVariable Long idAnalyse) {
        return analyseService.getTestsByAnalyseId(idAnalyse);
    }
}
