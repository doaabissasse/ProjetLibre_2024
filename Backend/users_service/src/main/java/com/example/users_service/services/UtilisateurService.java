package com.example.users_service.services;

import com.example.users_service.entities.User;
import com.example.users_service.tockenRepositories.TockenRepository;
import com.example.users_service.tockens.Token;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurService {

    @Autowired
    private com.example.users_service.repositories.UserRepository utilisateurRepository;

    @Autowired
    private TockenRepository tockenRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Optional<User> findByUsername(String email) {
        return utilisateurRepository.findByEmail(email);
    }

    public List<User> getAllUtilisateurs() {
        return utilisateurRepository.findAll();
    }

    public User getUtilisateurById(Integer id) {
        return utilisateurRepository.findById(id).orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));
    }

    public User createUtilisateur(User utilisateur) {
        utilisateur.setPassword(passwordEncoder.encode(utilisateur.getPassword()));
        return utilisateurRepository.save(utilisateur);
    }


    public List<User> getAllUtilisateursByLabo(long fkIdLaboratoire) {
        return utilisateurRepository.findAllUtilisateursByFkIdLaboratoire(fkIdLaboratoire);
    }
    public User findByEmail(String email) {
        return utilisateurRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User  not found with email: " + email));
    }

    public long countAllUsers() {
        return utilisateurRepository.count();
    }

    public List<User> getUsersByRole(String roleName) {
        return utilisateurRepository.findUsersByRole(roleName);
    }
    public void deleteUser(Integer userId) {
        Optional<User> user = utilisateurRepository.findById(userId);
        if (user.isPresent()) {
            // Supprimer tous les tokens associés à cet utilisateur
            List<Token> tokens = tockenRepository.findByUser(user.get());
            tockenRepository.deleteAll(tokens);

            utilisateurRepository.delete(user.get());
        }
    }


    public User updateUtilisateur(Integer userId, User updatedUser) {
        // Vérifier si l'utilisateur existe
        User user = utilisateurRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé avec ID: " + userId));

        // Mettre à jour les champs de l'utilisateur
        user.setEmail(updatedUser.getEmail());
        user.setNomComplet(updatedUser.getNomComplet());
        user.setProfession(updatedUser.getProfession());
        user.setNumTel(updatedUser.getNumTel());
        user.setSignature(updatedUser.getSignature());
        user.setFkIdLaboratoire(updatedUser.getFkIdLaboratoire());
        user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));  // Si vous voulez permettre la modification du mot de passe
        user.setEnabled(updatedUser.isEnabled());
        user.setAccountLocked(updatedUser.isAccountLocked());


        // Sauvegarder l'utilisateur mis à jour
        return utilisateurRepository.save(user);
    }

}
