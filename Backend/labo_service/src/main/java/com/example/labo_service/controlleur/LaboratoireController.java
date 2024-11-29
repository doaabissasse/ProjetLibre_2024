package com.example.labo_service.controlleur;

import com.example.labo_service.Entite.FullLaboratoireResponse;
import com.example.labo_service.Entite.FullLaboratoirewithUSER;
import com.example.labo_service.Entite.Laboratoire;
import com.example.labo_service.service.LaboratoireService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequiredArgsConstructor
@RequestMapping("/laboratoires")
public class  LaboratoireController {

    private final LaboratoireService laboratoireService; // Injecté via le constructeur

    //ajouter una laboratoire
    @PostMapping
    public Laboratoire addLaboratoire(@RequestBody Laboratoire laboratoire) {
        return laboratoireService.save(laboratoire);
    }
    //afficher tous les laboratoire
    @GetMapping
    public List<Laboratoire> listLaboratoires() {
        return laboratoireService.findAll();
    }

    //modifier un labo avec ce id
    @PutMapping("/{id}")
    public Laboratoire updateLaboratoire(@PathVariable long id, @RequestBody Laboratoire updatedLaboratoire) {
        return laboratoireService.updateLaboratoire(id, updatedLaboratoire);
    }

    //supprimer un labo avec ce id
    @DeleteMapping("/{id}")
    public void deleteLaboratoire(@PathVariable long id) {
        laboratoireService.deleteLaboratoire(id);
    }



    //afficher tous les contactes de labo avec id_labo
    @GetMapping("/contactes/{labo_id}")
    public FullLaboratoireResponse listLaboratoireContactes(@PathVariable("labo_id") long idLaboratoire) {
        return laboratoireService.findLabowithContactes(idLaboratoire);
    }

    //afficher tous les users de labo avec id_labo
    @GetMapping("/users/{labo_id}")
    public FullLaboratoirewithUSER listLaboratoireUsers(@PathVariable("labo_id") long idLaboratoire) {
        return laboratoireService.findLabowithUsers(idLaboratoire);
    }

    //chercher par id du labo
    @GetMapping("/{id}")
    public Laboratoire getLaboratoireById(@PathVariable long id) {
        return laboratoireService.findById(id);
    }

    //chercher par nom du labo
    @GetMapping("/search")
    public List<Laboratoire> searchLaboratoiresByNom(@RequestParam String nom) {
        return laboratoireService.findByNom(nom);
    }

}
