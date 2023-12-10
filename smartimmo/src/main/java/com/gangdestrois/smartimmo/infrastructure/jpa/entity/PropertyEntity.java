package com.gangdestrois.smartimmo.infrastructure.jpa.entity;


import com.gangdestrois.smartimmo.domain.property.entite.Property;
import jakarta.persistence.*;

@Entity
@Table(name = "property")
public class PropertyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_property")
    private Long id;

    @Column(name = "property_name")
    private String propertyName;

    @Column(name = "description")
    private String description;

    @Column(name = "room_number")
    private int roomsNumber;

    @Column(name = "livable_area")
    private double livableArea;

    @OneToOne(targetEntity = PropertyOwnerEntity.class)
    private PropertyOwnerEntity propertyOwnerEntity;

    public Property toModel() {
        return new Property(id, propertyName, description, roomsNumber, livableArea);
    }
}
