package com.example.user_service.Controllers;

import com.example.user_service.Entities.Utilisateur;
import com.example.user_service.Services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/utilisateurs")
public class UtilisateurController {

    @Autowired
    private UtilisateurService utilisateurService;

    @GetMapping
    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurService.getAllUtilisateurs();
    }

    @GetMapping("/{id}")
    public Utilisateur getUtilisateurById(@PathVariable long id) {
        return utilisateurService.getUtilisateurById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Utilisateur createUtilisateur(@RequestBody Utilisateur utilisateur) {
        return utilisateurService.createUtilisateur(utilisateur);
    }

    @GetMapping("/laboratoire/{labo-id}")
    public List<Utilisateur> getAllUtilisateurs(
            @PathVariable("labo-id") long fkIdLaboratoire
    ) {
        return  utilisateurService.getAllUtilisateursByLabo(fkIdLaboratoire);
    }

}