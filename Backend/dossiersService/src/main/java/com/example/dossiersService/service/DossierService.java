package com.example.dossiersService.service;

import com.example.dossiersService.entity.Dossier;
import com.example.dossiersService.repository.DossierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class DossierService {

    private final DossierRepository dossierRepository;

    @Autowired
    public DossierService(DossierRepository dossierRepository) {
        this.dossierRepository = dossierRepository;
    }

    public Dossier createDossier(Dossier dossier) {
        dossier.setDate(LocalDate.now()); // Set current date by default
        return dossierRepository.save(dossier);
    }

    public Optional<Dossier> getDossierById(Long id) {
        return dossierRepository.findById(id);
    }

    public List<Dossier> getDossiersByPatientId(Long idPatient) {
        return dossierRepository.findByIdPatient(idPatient);
    }

    public Dossier updateDossier(Long id, Dossier updatedDossier) {
        return dossierRepository.findById(id).map(dossier -> {
            dossier.setIdPatient(updatedDossier.getIdPatient());
            dossier.setIdUtilisateur(updatedDossier.getIdUtilisateur());
            dossier.setDate(updatedDossier.getDate());
            return dossierRepository.save(dossier);
        }).orElseThrow(() -> new RuntimeException("Dossier not found with id: " + id));
    }

    public void deleteDossier(Long id) {
        dossierRepository.deleteById(id);
    }
}