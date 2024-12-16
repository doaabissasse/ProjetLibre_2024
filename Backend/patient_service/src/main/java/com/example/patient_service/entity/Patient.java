package com.example.patient_service.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    private String prenom;

    private LocalDate dateNaissance;

    private String lieuDeNaissance;

    private String sexe; // Exemple: "M" pour masculin, "F" pour féminin.

    private String adresse;

    private String email;

    private String telephone;

    private String typePieceIdentite; // Exemple: "CIN", "Passeport", etc.

    private String numPieceIdentite; // Numéro associé au type de pièce d'identité.

    private String visiblePour; // Indique qui peut voir ce patient, par exemple "Admin", "Médecin", etc.

    public Patient() {
        super();
    }

    // Constructeur avec tous les champs
    public Patient(Long id, String nom, String prenom, LocalDate dateNaissance, String lieuDeNaissance, String sexe, String adresse, String email, String telephone, String typePieceIdentite, String numPieceIdentite, String visiblePour) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.lieuDeNaissance = lieuDeNaissance;
        this.sexe = sexe;
        this.adresse = adresse;
        this.email = email;
        this.telephone = telephone;
        this.typePieceIdentite = typePieceIdentite;
        this.numPieceIdentite = numPieceIdentite;
        this.visiblePour = visiblePour;
    }

    // Constructeur sans l'ID, utile pour les nouvelles entités
    public Patient(String nom, String prenom, LocalDate dateNaissance, String lieuDeNaissance, String sexe, String adresse, String email, String telephone, String typePieceIdentite, String numPieceIdentite, String visiblePour) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.lieuDeNaissance = lieuDeNaissance;
        this.sexe = sexe;
        this.adresse = adresse;
        this.email = email;
        this.telephone = telephone;
        this.typePieceIdentite = typePieceIdentite;
        this.numPieceIdentite = numPieceIdentite;
        this.visiblePour = visiblePour;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getLieuDeNaissance() {
        return lieuDeNaissance;
    }

    public void setLieuDeNaissance(String lieuDeNaissance) {
        this.lieuDeNaissance = lieuDeNaissance;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getTypePieceIdentite() {
        return typePieceIdentite;
    }

    public void setTypePieceIdentite(String typePieceIdentite) {
        this.typePieceIdentite = typePieceIdentite;
    }

    public String getNumPieceIdentite() {
        return numPieceIdentite;
    }

    public void setNumPieceIdentite(String numPieceIdentite) {
        this.numPieceIdentite = numPieceIdentite;
    }

    public String getVisiblePour() {
        return visiblePour;
    }

    public void setVisiblePour(String visiblePour) {
        this.visiblePour = visiblePour;
    }
}