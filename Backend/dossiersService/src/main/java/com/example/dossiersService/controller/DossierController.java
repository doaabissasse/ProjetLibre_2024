package com.example.dossiersService.controller;

import com.example.dossiersService.entity.Dossier;
import com.example.dossiersService.entity.ExamenDTO;
import com.example.dossiersService.service.DossierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/dossiers")
public class DossierController {

    private final DossierService dossierService;

    @Autowired
    public DossierController(DossierService dossierService) {
        this.dossierService = dossierService;
    }

    @PostMapping

    public ResponseEntity<Dossier> createDossier(@RequestBody Dossier dossier) {
        try {
            Dossier createdDossier = dossierService.createDossier(dossier);
            return ResponseEntity.ok(createdDossier);
        } catch (OptimisticLockingFailureException e) {
            // Gérer le conflit, par exemple, en informant l'utilisateur
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(null); // Vous pouvez également renvoyer un message d'erreur
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dossier> getDossierById(@PathVariable Long id) {
        Optional<Dossier> dossier = dossierService.getDossierById(id);
        return dossier.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PutMapping("/{id}")
    public ResponseEntity<Dossier> updateDossier(@PathVariable Long id, @RequestBody Dossier updatedDossier) {
        Dossier dossier = dossierService.updateDossier(id, updatedDossier);
        return ResponseEntity.ok(dossier);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDossier(@PathVariable Long id) {
        dossierService.deleteDossier(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/patient/{idPatient}")
    public List<Dossier> getDossiersByPatientId(@PathVariable Long idPatient) {
        return dossierService.getDossiersByPatientId(idPatient);
    }

    @GetMapping("/by-utilisateur/{idUtilisateur}")
    public List<Dossier> getDossiersByIdUtilisateur(@PathVariable Long idUtilisateur) {
        return dossierService.getDossiersByIdUtilisateur(idUtilisateur);
    }

    @GetMapping("/{idDossier}/examens")
    public List<ExamenDTO> getExamensByDossierId(@PathVariable Long idDossier) {
        return dossierService.getExamensByDossierId(idDossier);
    }
}