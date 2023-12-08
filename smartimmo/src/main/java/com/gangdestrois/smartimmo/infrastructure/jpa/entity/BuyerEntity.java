package com.gangdestrois.smartimmo.infrastructure.jpa.entity;

import jakarta.persistence.*;

@Entity
@Table(name="acquereur")
public class BuyerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="acquereur_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ref_prospect")
    private ProspectEntity prospect;

    @Column(name="statut")
    private String status;              /////////// mettre un enum ici ? /////////////

    @Column(name="date_debut_recherche")
    private String searchStartDate;

    @Column(name="date_fin_recherche")
    private String searchEndDate;


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getStatus() {
        return status;
    }
    public String getSearchStartDate() {
        return searchStartDate;
    }
    public String getSearchEndDate() {
        return searchEndDate;
    }
    public ProspectEntity getProspect() {return prospect;}

}