package com.example.labo_service.service;

import com.example.labo_service.Client.AnalyseFeignClient;
import com.example.labo_service.Client.ContactClient;
import com.example.labo_service.Client.UserClient;
import com.example.labo_service.Entite.Analyse;
import com.example.labo_service.Entite.FullLaboratoireResponse;
import com.example.labo_service.Entite.FullLaboratoirewithUSER;
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
    private final UserClient userClient;
    private final AnalyseFeignClient analyseFeignClient;


    //methode d'ajout un labo
    public Laboratoire save(Laboratoire laboratoire) {
        return laboratoireRepository.save(laboratoire);
    }

    //methode d'afficher un labo
    public List<Laboratoire> findAll() {
        return laboratoireRepository.findAll();
    }

    //methode de modifier un labo exister
    public Laboratoire updateLaboratoire(long id, Laboratoire updatedLaboratoire) {
        return laboratoireRepository.findById(id)
                .map(laboratoire -> {
                    laboratoire.setNom(updatedLaboratoire.getNom());
                    laboratoire.setLogo(updatedLaboratoire.getLogo());
                    laboratoire.setNrc(updatedLaboratoire.getNrc());
                    laboratoire.setDateActivation(updatedLaboratoire.getDateActivation());
                    laboratoire.setActive(updatedLaboratoire.isActive()); // Ajout de la mise à jour de 'active'
                    return laboratoireRepository.save(laboratoire);
                })
                .orElseThrow(() -> new RuntimeException("Laboratoire avec ID " + id + " introuvable."));
    }



    //methode de supprimer  un labo exister
    public void deleteLaboratoire(long id) {
        if (laboratoireRepository.existsById(id)) {
            laboratoireRepository.deleteById(id);
        } else {
            throw new RuntimeException("Laboratoire avec ID " + id + " introuvable.");
        }
    }

    // methode afficher un labo avec ses contactes
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

    // methode afficher un labo avec ses users
    public FullLaboratoirewithUSER findLabowithUsers(long idLaboratoire) {
        var labo = laboratoireRepository.findById(idLaboratoire)
                .orElse(Laboratoire.builder()
                        .nom("NOT_FOUND")
                        .logo("NOT_FOUND")
                        .nrc("NOT_FOUND")
                        .dateActivation(LocalDate.now())
                        .build());
        var user = userClient.findAllUSERSbyLabo(idLaboratoire);
        return FullLaboratoirewithUSER.builder()
                .nom(labo.getNom())
                .logo(labo.getLogo())
                .nrc(labo.getNrc())
                .dateActivation(labo.getDateActivation())
                .users(user)
                .build();
    }

    //chercher les labo par son id
    public Laboratoire findById(long id) {
        return laboratoireRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Laboratoire avec ID " + id + " introuvable."));
    }

    //chercher par le nom du labo
    public List<Laboratoire> findByNom(String nom) {
        return laboratoireRepository.findByNomContainingIgnoreCase(nom);
    }

    public List<Analyse> getAnalysesByLaboratoireId(Long idLabo) {
        return analyseFeignClient.getAnalysesByLaboratoireId(idLabo);
    }


}
