package com.example.contactes_service.controlleur;

import com.example.contactes_service.Entite.Adresse;
import com.example.contactes_service.Entite.contacte;
import com.example.contactes_service.service.contacteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequiredArgsConstructor
@RequestMapping("/contactes")
public class contacteController {

    @Autowired
    private contacteService contacteService;


    @GetMapping("/{contacteId}/adresse")
    public Adresse getAdresseByContacteId(@PathVariable long contacteId) {
        return contacteService.getAdresseByContacteId(contacteId);
    }

    //ajouter una contacte
    @PostMapping
    public contacte addcontacte(@RequestBody contacte contacte)
    {
        return contacteService.save(contacte);
    }

    //afficher tous les contactes
    @GetMapping
    public List<contacte> listcontactes()
    {
        return contacteService.findAll();
    }

    //afficher tous un contacte avec ses contactes
    @GetMapping("/laboratoire/{labo_id}")
    public List<contacte> listcontactes(
            @PathVariable("labo_id") long laboID
    )
    {
        return contacteService.findAlllbyaboratoire(laboID);
    }

    //afficher tous un contacte avec ses contactes
    @GetMapping("/adresse/{adresse_id}")
    public List<contacte> listcontactesAdresse(
            @PathVariable("adresse_id") long adresseID
    )
    {
        return contacteService.findAlllbyAdresse(adresseID);
    }

    //modifier un contacte avec ce id
    @PutMapping("/{id}")
    public contacte updatecontacte(@PathVariable long id, @RequestBody contacte updatedcontacte) {
        return contacteService.updatecontacte(id, updatedcontacte);
    }

    //supprimer un contacte avec ce id
    @DeleteMapping("/{id}")
    public void deletecontacte(@PathVariable long id) {
        contacteService.deletecontacte(id);
    }

    //chercher par id du contacte
    @GetMapping("/{id}")
    public contacte getcontacteById(@PathVariable long id) {
        return contacteService.findById(id);
    }


    @DeleteMapping("/laboratoire/{labo_id}")
    public void deleteAllContactsByLaboratoireId(@PathVariable("labo_id") long laboId) {
        contacteService.deleteAllContactsByLaboratoireId(laboId);
    }
}