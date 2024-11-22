package com.example.contactes_service.Entite;


import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="contacte")
public class contacte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long idLaboratoire;
    private String tel;
    private String fax;
    private String email;
}
