package com.gangdestrois.smartimmo.infrastructure.jpa.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "sales_history")
public class SalesHistoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sales_history")
    private Long id;

    @Column(name = "date_of_sale")
    private LocalDate dateOfSale;

    @Column(name = "price")
    private Double price;

    @OneToOne(targetEntity = PropertyOwnerEntity.class)
    @JoinColumn(name = "fk_property_owner", referencedColumnName = "id_property_owner")
    private PropertyOwnerEntity propertyOwner;
}
