package com.example.labo_service.service;

import com.example.labo_service.Client.ContactClient;
import com.example.labo_service.Entite.FullLaboratoireResponse;
import com.example.labo_service.Entite.Laboratoire;
import com.example.labo_service.repository.LaboratoireRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LaboratoireService {

    private final LaboratoireRepository laboratoireRepository;
    private final ContactClient client;

    public Laboratoire save(Laboratoire laboratoire) {
        return laboratoireRepository.save(laboratoire);
    }

    public List<Laboratoire> findAll() {
        return laboratoireRepository.findAll();
    }

    public FullLaboratoireResponse findLabowithContactes(long idLaboratoire) {
        var labo = laboratoireRepository.findById(idLaboratoire)
                .orElse(Laboratoire.builder()
                        .nom("NOT_FOUND")
                        .logo("NOT_FOUND")
                        .nrc("NOT_FOUND")
                        .dateActivation(LocalDate.now())
                        .build());
        var contactes = client.findAllContratbyLabo(idLaboratoire);
        return FullLaboratoireResponse.builder()
                .nom(labo.getNom())
                .logo(labo.getLogo())
                .nrc(labo.getNrc())
                .dateActivation(labo.getDateActivation())
                .contactes(contactes)
                .build();
    }
}
