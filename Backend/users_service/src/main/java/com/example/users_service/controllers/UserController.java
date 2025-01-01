package com.example.users_service.controllers;

import com.example.users_service.entities.DossierDTO;
import com.example.users_service.entities.User;
import com.example.users_service.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/utilisateurs")
public class UserController {
        @Autowired
        private UtilisateurService utilisateurService;

        @GetMapping
        public List<User> getAllUtilisateurs() {

            return utilisateurService.getAllUtilisateurs()
                    .stream()
                    .filter(user -> user.getId() != 1) // Exclure l'utilisateur avec l'ID 1
                    .collect(Collectors.toList());
        }

        @GetMapping("/{id}")
        public User getUtilisateurById(@PathVariable Integer id) {
            return utilisateurService.getUtilisateurById(id);
        }



    @GetMapping("/laboratoire/{labo-id}")
    public List<User> getAllUtilisateurs(@PathVariable("labo-id") long fkIdLaboratoire) {
        return utilisateurService.getAllUtilisateursByLabo(fkIdLaboratoire)
                .stream()
                .filter(user -> user.getId() != 1) // Exclure l'utilisateur avec l'ID 1
                .collect(Collectors.toList());
    }


    @GetMapping("/count")
    public long countUtilisateurs() {
        return utilisateurService.countAllUsers();
    }

    @GetMapping("/role/User")
    public List<User> getUsersWithRole() {
        return utilisateurService.getUsersByRole("User");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUtilisateur(@PathVariable Integer id) {
        try {
            utilisateurService.deleteUser(id);
            return ResponseEntity.noContent().build(); // Suppression réussie
        } catch (Exception e) {
            return ResponseEntity.notFound().build(); // Utilisateur non trouvé
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUtilisateur(@PathVariable Integer id, @RequestBody User updatedUser) {
        try {
            // Appel de la méthode de mise à jour
            User updatedUserResult = utilisateurService.updateUtilisateur(id, updatedUser);
            return ResponseEntity.ok(updatedUserResult);  // Retourner l'utilisateur mis à jour
        } catch (Exception e) {
            return ResponseEntity.notFound().build();  // Si l'utilisateur n'est pas trouvé
        }
    }

    @GetMapping("/{idUtilisateur}/dossiers")
    public List<DossierDTO> getDossiersByIdUtilisateur(@PathVariable Long idUtilisateur) {
        return utilisateurService.getDossiersByIdUtilisateur(idUtilisateur);
    }

}
