package com.gangdestrois.smartimmo.infrastructure.jpa.entity;

import com.gangdestrois.smartimmo.domain.buyer.model.Buyer;
import com.gangdestrois.smartimmo.domain.property.model.PropertyCriteria;
import jakarta.persistence.*;

@Entity
@Table(name = "property_criteria")
public class PropertyCriteriaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_property_criteria")
    private Long id;

    @OneToOne
    @JoinColumn(name = "fk_buyer", referencedColumnName = "id_buyer")
    private BuyerEntity buyer;

    @Column(name = "room_number")
    private int roomsNumber;

    @Column(name = "minimum_surface")
    private double minimumSurface;
    ////////////////// mettre tous les autres champs après ////////////////////////


    public PropertyCriteria toPropertyCriteriaModel() {
        return new PropertyCriteria(id, roomsNumber,
                minimumSurface);
    }

    public Buyer toBuyerModel() {
        return new Buyer(this.buyer.getId(), this.buyer.getProspect().toModel(), this.buyer.getStatus(),
                this.buyer.getSearchStartDate(), this.buyer.getSearchEndDate(),
                this.toPropertyCriteriaModel()
        );
    }
}