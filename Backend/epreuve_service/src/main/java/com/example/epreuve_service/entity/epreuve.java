package com.example.epreuve_service.entity;
import jakarta.persistence.*;

@Entity
@Table(schema = "epreuve")
public class epreuve {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idAnalyse;

    private String nom;

    public epreuve() {
        super();
    }
    public epreuve(Long id, Long idAnalyse, String nom) {
        this.id = id;
        this.idAnalyse = idAnalyse;
        this.nom = nom;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdAnalyse() {
        return idAnalyse;
    }

    public void setIdAnalyse(Long idAnalyse) {
        this.idAnalyse = idAnalyse;
    }

    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
}
