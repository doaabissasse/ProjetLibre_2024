package com.example.examen_service.entity;

import jakarta.persistence.*;

@Entity
@Table(schema = "examen")
public class Examen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idDossier;

    private Long idEpreuve;

    private Long idTestAnalyse;

    private String resultat;

    @Version
    private Long version;
    public Examen() {
        super();
    }

    public Examen(Long id, Long idDossier, Long idEpreuve, Long idTestAnalyse, String resultat) {
        this.id = id;
        this.idDossier = idDossier;
        this.idEpreuve = idEpreuve;
        this.idTestAnalyse = idTestAnalyse;
        this.resultat = resultat;
    }


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getIdDossier() {
        return idDossier;
    }
    public void setIdDossier(Long idDossier) {
        this.idDossier = idDossier;
    }
    public Long getIdEpreuve() {
        return idEpreuve;
    }
    public void setIdEpreuve(Long idEpreuve) {
        this.idEpreuve = idEpreuve;
    }
    public Long getIdTestAnalyse() {
        return idTestAnalyse;
    }
    public void setIdTestAnalyse(Long idTestAnalyse) {
        this.idTestAnalyse = idTestAnalyse;
    }
    public String getResultat() {
        return resultat;
    }
    public void setResultat(String resultat) {
        this.resultat = resultat;
    }

}
