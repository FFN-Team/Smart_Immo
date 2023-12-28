package com.gangdestrois.smartimmo.infrastructure.jpa.entity;

import com.gangdestrois.smartimmo.domain.buyer.enums.BuyerStatusEnum;
import com.gangdestrois.smartimmo.domain.buyer.model.Buyer;
import jakarta.persistence.*;

import java.util.Date;

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
    private BuyerStatusEnum status;
    @Column(name = "search_start_date")
    private Date searchStartDate;
    @Column(name = "search_end_date")
    private Date searchEndDate;

    public BuyerEntity(Long id, ProspectEntity prospect, BuyerStatusEnum status) {
        this.id = id;
        this.prospect = prospect;
        this.status = status;
    }

    public BuyerEntity() {  }

    public Buyer toModel(){
        return new Buyer(
                id, prospect.toModel(), status, searchStartDate, searchEndDate
        );
    }

    public static BuyerEntity fromModelToEntity(Buyer buyer){
        return new BuyerEntity(buyer.getId(),ProspectEntity.fromModelToEntity(buyer.getProspect()), buyer.getStatus());
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public BuyerStatusEnum getStatus() {
        return status;
    }
    public Date getSearchStartDate() {
        return searchStartDate;
    }
    public Date getSearchEndDate() {
        return searchEndDate;
    }
    public ProspectEntity getProspect() {
        return prospect;
    }
}