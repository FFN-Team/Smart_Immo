package com.gangdestrois.smartimmo.infrastructure.jpa.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "property_owner")
public class PropertyOwnerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_property_owner")
    private Long id;
    @Column(name = "acquisition_date")
    private LocalDate acquisitionDate;
    @OneToOne(targetEntity = OwnerEntity.class)
    @JoinColumn(name = "fk_owner", referencedColumnName = "id_owner")
    private OwnerEntity owner;
    @OneToOne(targetEntity = PropertyEntity.class)
    @JoinColumn(name = "fk_property", referencedColumnName = "id_property")
    private PropertyEntity property;
    @Column(name = "main")
    private boolean main;

    public LocalDate getAcquisitionDate() {
        return this.acquisitionDate;
    }

    public PropertyEntity getProperty() {
        return this.property;
    }

    public boolean getMain() {
        return this.main;
    }
}
