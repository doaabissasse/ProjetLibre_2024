package com.example.patient_service.service;

import com.example.patient_service.Client.DossierFeignClient;
import com.example.patient_service.entity.DossierDto;
import com.example.patient_service.entity.Patient;
import com.example.patient_service.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    private final PatientRepository patientRepository;
    private final DossierFeignClient dossierFeignClient;


    @Autowired
    public PatientService(PatientRepository patientRepository, DossierFeignClient dossierFeignClient) {
        this.patientRepository = patientRepository;
        this.dossierFeignClient = dossierFeignClient;
    }

    public Patient createPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public Patient updatePatient(Long id, Patient patientDetails) {
        return patientRepository.findById(id).map(patient -> {
            patient.setNom(patientDetails.getNom());
            patient.setPrenom(patientDetails.getPrenom());
            patient.setDateNaissance(patientDetails.getDateNaissance());
            patient.setLieuDeNaissance(patientDetails.getLieuDeNaissance());
            patient.setSexe(patientDetails.getSexe());
            patient.setAdresse(patientDetails.getAdresse());
            patient.setEmail(patientDetails.getEmail());
            patient.setTelephone(patientDetails.getTelephone());
            patient.setTypePieceIdentite(patientDetails.getTypePieceIdentite());
            patient.setNumPieceIdentite(patientDetails.getNumPieceIdentite());
            patient.setVisiblePour(patientDetails.getVisiblePour());
            return patientRepository.save(patient);
        }).orElseThrow(() -> new RuntimeException("Patient non trouvé pour l'id: " + id));
    }

    public void deletePatient(Long id) {
        patientRepository.findById(id).ifPresent(patientRepository::delete);
    }

    public Patient getPatientById(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient non trouvé avec id : " + id));
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public List<DossierDto> getDossiersByPatientId(Long idPatient) {
        return dossierFeignClient.getDossiersByPatientId(idPatient);
    }

}