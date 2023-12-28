package com.gangdestrois.smartimmo.infrastructure.jpa.entity;


import com.gangdestrois.smartimmo.domain.property.model.Property;
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

    @OneToOne(mappedBy = "property")
    private PropertyOwnerEntity propertyOwnerEntity;

    public PropertyEntity(Long id, String propertyName, String description, int roomsNumber, double livableArea) {
        this.id = id;
        this.propertyName = propertyName;
        this.description = description;
        this.roomsNumber = roomsNumber;
        this.livableArea = livableArea;
    }

    public PropertyEntity() {}
    public Property toModel() {
        return new Property(id, propertyName, description, roomsNumber, livableArea);
    }
    public static PropertyEntity fromModelToEntity(Property property){
        return new PropertyEntity(property.id(), property.propertyName(),property.description(),
                property.roomsNumber(), property.livableArea());
    }
}
