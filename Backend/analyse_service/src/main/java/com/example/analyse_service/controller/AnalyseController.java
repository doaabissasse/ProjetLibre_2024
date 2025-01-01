package com.example.analyse_service.controller;


import com.example.analyse_service.entity.Analyse;
import com.example.analyse_service.entity.TestAnalyse;
import com.example.analyse_service.entity.epreuve;
import com.example.analyse_service.service.AnalyseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequiredArgsConstructor
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
    public Analyse createAnalyse(@RequestBody Analyse analyse) {
        return analyseService.save(analyse);
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
