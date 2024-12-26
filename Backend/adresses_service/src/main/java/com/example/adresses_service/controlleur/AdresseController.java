package com.example.adresses_service.controlleur;

import com.example.adresses_service.Entite.Adresse;
import com.example.adresses_service.service.AdresseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/adresses")
public class AdresseController {

    @Autowired
    private AdresseService adresseService;

    @GetMapping
    public List<Adresse> getAllAdresses() {
        return adresseService.getAllAdresses();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Adresse> getAdresseById(@PathVariable Long id) {
        return adresseService.getAdresseById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Adresse createAdresse(@RequestBody Adresse adresse) {
        return adresseService.saveAdresse(adresse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Adresse> updateAdresse(@PathVariable Long id, @RequestBody Adresse updatedAdresse) {
        return adresseService.getAdresseById(id)
                .map(existingAdresse -> {
                    existingAdresse.setNumVoie(updatedAdresse.getNumVoie());
                    existingAdresse.setNomVoie(updatedAdresse.getNomVoie());
                    existingAdresse.setCodePostal(updatedAdresse.getCodePostal());
                    existingAdresse.setVille(updatedAdresse.getVille());
                    existingAdresse.setCommune(updatedAdresse.getCommune());
                    Adresse savedAdresse = adresseService.saveAdresse(existingAdresse);
                    return ResponseEntity.ok(savedAdresse);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdresse(@PathVariable Long id) {
        if (adresseService.getAdresseById(id).isPresent()) {
            adresseService.deleteAdresse(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}