package com.example.contactes_service.service;

import com.example.contactes_service.Entite.contacte;
import com.example.contactes_service.repository.ContacteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class contacteService {

    @Autowired
    private ContacteRepository contacteRepository;

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
            contacteRepository.deleteById(id);
        } else {
            throw new RuntimeException("contacte avec ID " + id + " introuvable.");
        }
    }


    //chercher les labo par son id
    public contacte findById(long id) {
        return contacteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("contacte avec ID " + id + " introuvable."));
    }

}

