package com.example.users_service.controllers;

import com.example.users_service.entities.User;
import com.example.users_service.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/utilisateurs")
public class UserController {
        @Autowired
        private UtilisateurService utilisateurService;

        @GetMapping
        public List<User> getAllUtilisateurs() {
            return utilisateurService.getAllUtilisateurs();
        }

        @GetMapping("/{id}")
        public User getUtilisateurById(@PathVariable Integer id) {
            return utilisateurService.getUtilisateurById(id);
        }



        @GetMapping("/laboratoire/{labo-id}")
        public List<User> getAllUtilisateurs(@PathVariable("labo-id") long fkIdLaboratoire) {
            return utilisateurService.getAllUtilisateursByLabo(fkIdLaboratoire);
        }
    }
