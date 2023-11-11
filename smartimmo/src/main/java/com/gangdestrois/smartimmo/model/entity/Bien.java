package com.gangdestrois.smartimmo.model.entity;


import jakarta.persistence.*;

@Entity
@Table(name="bien")
public class Bien {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="bien_id")
    private Long id;

    @Column(name="nom_bien")
    private String nomBien;

    @Column(name = "description")
    private String description;

    public String getNomBien() {
        return nomBien;
    }

    public String getDescription() {
        return description;
    }
}
