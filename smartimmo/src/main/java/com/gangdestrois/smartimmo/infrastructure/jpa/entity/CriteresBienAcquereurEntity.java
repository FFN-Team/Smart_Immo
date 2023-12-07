package com.gangdestrois.smartimmo.infrastructure.jpa.entity;

import com.gangdestrois.smartimmo.domain.acquereur.entite.Acquereur;
import com.gangdestrois.smartimmo.domain.acquereur.entite.CriteresBien;
import jakarta.persistence.*;

@Entity
@Table(name="critere_bien")
public class CriteresBienAcquereurEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_critere_bien")
    private Long id;

    @OneToOne
    @JoinColumn(name = "ref_acquereur", referencedColumnName = "acquereur_id")
    private AcquereurEntity acquereur;

    @Column(name="nombre_piece")
    private int nombrePiece;

    @Column(name = "surface_minimum")
    private double surfaceMinimum;
    ////////////////// mettre tous les autres champs après ////////////////////////


    public CriteresBien toCBAModel(){ return new CriteresBien(id, nombrePiece,
            surfaceMinimum);}

    public Acquereur toAcquereurModel(){
        return new Acquereur(this.acquereur.getId(), this.acquereur.getProspect().toModel(), this.acquereur.getStatut(),
                this.acquereur.getDateDebutRecherche(), this.acquereur.getDateFinRecherche(),
                this.toCBAModel()
        );
    }
}