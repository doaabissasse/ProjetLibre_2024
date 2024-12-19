package com.example.patient_service.entity;

import java.time.LocalDate;

public class DossierDto {
    private Long id;
    private Long idPatient;
    private Long idUtilisateur;
    private LocalDate date;

    // Getters et Setters
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

    @Override
    public String toString() {
        return "DossierDto{" +
                "id=" + id +
                ", idPatient=" + idPatient +
                ", idUtilisateur=" + idUtilisateur +
                ", date=" + date +
                '}';
    }
}
