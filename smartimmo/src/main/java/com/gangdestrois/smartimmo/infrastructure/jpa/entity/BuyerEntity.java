package com.gangdestrois.smartimmo.infrastructure.jpa.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "buyer")
public class BuyerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_buyer")
    private Long id;
    @ManyToOne(targetEntity = ProspectEntity.class)
    @JoinColumn(name = "fk_prospect", referencedColumnName = "id_prospect")
    private ProspectEntity prospect;
    @Column(name = "status")
    private String status;              /////////// mettre un enum ici ? /////////////
    @Column(name = "search_start_date")
    private String searchStartDate;
    @Column(name = "search_end_date")
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

    public ProspectEntity getProspect() {
        return prospect;
    }

}