package com.example.contactes_service.service;

import com.example.contactes_service.Entite.Adresse;
import com.example.contactes_service.Entite.contacte;
import com.example.contactes_service.client.AdresseClient;
import com.example.contactes_service.repository.ContacteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class contacteService {

    @Autowired
    private ContacteRepository contacteRepository;
    private final AdresseClient adresseClient;



    //methode d'ajout un contacte
    public contacte save(contacte contacte)
    {
        return contacteRepository.save(contacte);
    }

    //methode d'afficher tous les contactes
    public List<contacte> findAll()
    {
        return contacteRepository.findAll();
    }

    //methode d'afficher  contacte par id d'un labo
    public List<contacte> findAlllbyaboratoire(long laboId) {
        return contacteRepository.findAllByIdLaboratoire(laboId);
    }

    //methode d'afficher  contacte par id d'un labo
    public List<contacte> findAlllbyAdresse(long adresseId) {
        return contacteRepository.findAllByIdAdresse(adresseId);
    }

    //methode de modifier un contacte exister
    public contacte updatecontacte(long id, contacte updatedcontacte) {
        return contacteRepository.findById(id)
                .map(contacte -> {
                    contacte.setTel(updatedcontacte.getTel());
                    contacte.setFax(updatedcontacte.getFax());
                    contacte.setEmail(updatedcontacte.getEmail());
                    return contacteRepository.save(contacte);
                })
                .orElseThrow(() -> new RuntimeException("contacteavec ID " + id + " introuvable."));
    }



    //methode de supprimer  un contacte exister
    public void deletecontacte(long id) {
        if (contacteRepository.existsById(id)) {
            // Récupérer le contact
            contacte contact = contacteRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Contact avec ID " + id + " introuvable."));

            // Récupérer l'adresse associée au contact
            long adresseId = contact.getIdAdresse();

            // Supprimer le contact
            contacteRepository.deleteById(id);

            // Supprimer l'adresse via le client adresse
            adresseClient.deleteAdresse(adresseId);
        } else {
            throw new RuntimeException("Contacte avec ID " + id + " introuvable.");
        }
    }


    //chercher les labo par son id
    public contacte findById(long id) {
        return contacteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("contacte avec ID " + id + " introuvable."));
    }

    public Adresse getAdresseByContacteId(long contacteId) {
        Optional<contacte> contacte = contacteRepository.findById(contacteId);

        if (contacte.isPresent()) {
            long adresseId = contacte.get().getIdAdresse();
            return adresseClient.getAdresseById(adresseId);
        }

        throw new RuntimeException("Contacte not found with ID: " + contacteId);
    }

    public void deleteAllContactsByLaboratoireId(long laboId) {
        List<contacte> contacts = contacteRepository.findAllByIdLaboratoire(laboId);
        contacteRepository.deleteAll(contacts);
    }

}

