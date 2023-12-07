package com.gangdestrois.smartimmo.infrastructure.jpa.entity;

import com.gangdestrois.smartimmo.domain.acquereur.entite.Acquereur;
import jakarta.persistence.*;

@Entity
@Table(name="acquereur")
public class AcquereurEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="acquereur_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ref_prospect")
    private ProspectEntity prospect;

    @Column(name="statut")
    private String statut;              /////////// mettre un enum ici ? /////////////

    @Column(name="date_debut_recherche")
    private String dateDebutRecherche;

    @Column(name="date_fin_recherche")
    private String dateFinRecherche;


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getStatut() {
        return statut;
    }
    public String getDateDebutRecherche() {
        return dateDebutRecherche;
    }
    public String getDateFinRecherche() {
        return dateFinRecherche;
    }
    public ProspectEntity getProspect() {return prospect;}

}