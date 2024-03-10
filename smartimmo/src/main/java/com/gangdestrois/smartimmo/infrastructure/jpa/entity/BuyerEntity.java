package com.gangdestrois.smartimmo.infrastructure.jpa.entity;

import com.gangdestrois.smartimmo.domain.buyer.enums.BuyerStatus;
import com.gangdestrois.smartimmo.domain.buyer.model.Buyer;
import jakarta.persistence.*;

import java.time.LocalDate;

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
    @Enumerated(EnumType.STRING)
    private BuyerStatus status;
    @Column(name = "search_start_date")
    private LocalDate searchStartDate;
    @Column(name = "search_end_date")
    private LocalDate searchEndDate;

    public BuyerEntity(Long id, ProspectEntity prospect, BuyerStatus status) {
        this.id = id;
        this.prospect = prospect;
        this.status = status;
    }

    public BuyerEntity() {
    }

    public Buyer toModel() {
        return new Buyer(
                id, prospect.toModel(), status, searchStartDate, searchEndDate
        );
    }

    public static BuyerEntity fromModelToEntity(Buyer buyer) {
        return new BuyerEntity(buyer.getId(), ProspectEntity.fromModelToEntity(buyer.getProspect()), buyer.getStatus());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BuyerStatus getStatus() {
        return status;
    }

    public LocalDate getSearchStartDate() {
        return searchStartDate;
    }

    public LocalDate getSearchEndDate() {
        return searchEndDate;
    }

    public ProspectEntity getProspect() {
        return prospect;
    }
}