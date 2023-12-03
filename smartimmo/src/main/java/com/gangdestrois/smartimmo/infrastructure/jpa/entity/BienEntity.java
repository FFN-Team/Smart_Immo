package com.gangdestrois.smartimmo.infrastructure.jpa.entity;


import com.gangdestrois.smartimmo.domain.bien.Bien;
import jakarta.persistence.*;

@Entity
@Table(name="bien")
public class BienEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="bien_id")
    private Long id;

    @Column(name="nom_bien")
    private String nomBien;

    @Column(name = "description")
    private String description;

    @Column(name="nb_piece")
    private int nbPiece;

    @Column(name = "surface_habitable")
    private double surfaceHabitable;

    public Bien toModel() {
        return new Bien(id, nomBien, description, nbPiece, surfaceHabitable);
    }
}
