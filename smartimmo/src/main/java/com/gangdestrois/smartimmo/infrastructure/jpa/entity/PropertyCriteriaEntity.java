package com.gangdestrois.smartimmo.infrastructure.jpa.entity;

import com.gangdestrois.smartimmo.domain.buyer.model.Buyer;
import com.gangdestrois.smartimmo.domain.buyer.model.PropertyCriteria;
import jakarta.persistence.*;

@Entity
@Table(name="critere_bien")
public class PropertyCriteriaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_critere_bien")
    private Long id;

    @OneToOne
    @JoinColumn(name = "ref_acquereur", referencedColumnName = "acquereur_id")
    private BuyerEntity buyer;

    @Column(name="nombre_piece")
    private int roomsNumber;

    @Column(name = "surface_minimum")
    private double minimumSurface;
    ////////////////// mettre tous les autres champs après ////////////////////////


    public PropertyCriteria toPropertyCriteriaModel(){ return new PropertyCriteria(id, roomsNumber,
            minimumSurface);}

    public Buyer toBuyerModel(){
        return new Buyer(this.buyer.getId(), this.buyer.getProspect().toModel(), this.buyer.getStatus(),
                this.buyer.getSearchStartDate(), this.buyer.getSearchEndDate(),
                this.toPropertyCriteriaModel()
        );
    }
}