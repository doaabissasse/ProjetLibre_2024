package com.example.dossiersService.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "dossiers")
public class Dossier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long idPatient;     // ID of the patient associated with the dossier

    @Column(nullable = false)
    private Long idUtilisateur; // ID of the user (e.g., the one who created/modified the dossier)

    @Column(nullable = false)
    private LocalDate date;     // Date of creation or last update

    // Constructors
    public Dossier() {
    }

    public Dossier(Long id, Long idPatient, Long idUtilisateur, LocalDate date) {
        this.id = id;
        this.idPatient = idPatient;
        this.idUtilisateur = idUtilisateur;
        this.date = date;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(Long idPatient) {
        this.idPatient = idPatient;
    }

    public Long getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Long idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    // Override toString, equals, and hashCode if necessary
    @Override
    public String toString() {
        return "Dossier{" +
                "id=" + id +
                ", idPatient=" + idPatient +
                ", idUtilisateur=" + idUtilisateur +
                ", date=" + date +
                '}';
    }
}