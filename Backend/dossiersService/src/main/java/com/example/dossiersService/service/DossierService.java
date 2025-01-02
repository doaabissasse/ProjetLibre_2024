package com.example.dossiersService.service;

import com.example.dossiersService.Client.ExamenClient;
import com.example.dossiersService.entity.Dossier;
import com.example.dossiersService.entity.ExamenDTO;
import com.example.dossiersService.repository.DossierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class DossierService {

    private final DossierRepository dossierRepository;
    private final ExamenClient examenClient;


    @Autowired
    public DossierService(DossierRepository dossierRepository, ExamenClient examenClient) {
        this.dossierRepository = dossierRepository;
        this.examenClient = examenClient;
    }

    public Dossier createDossier(Dossier dossier) {
        return dossierRepository.save(dossier);
    }

    public Optional<Dossier> getDossierById(Long id) {
        return dossierRepository.findById(id);
    }

    public List<Dossier> getDossiersByPatientId(Long idPatient) {
        return dossierRepository.findByIdPatient(idPatient);
    }

    public List<Dossier> getDossiersByIdUtilisateur(Long idUtilisateur) {
        return dossierRepository.findByIdUtilisateur(idUtilisateur);
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

    public List<ExamenDTO> getExamensByDossierId(Long idDossier) {
        return examenClient.getExamensByDossierId(idDossier);
    }
}