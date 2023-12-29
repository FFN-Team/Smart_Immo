package com.gangdestrois.smartimmo.infrastructure.jpa.entity;

import com.gangdestrois.smartimmo.domain.property.model.Property;
import jakarta.persistence.*;

import static java.util.Objects.nonNull;

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

    @OneToOne(mappedBy = "property", cascade = CascadeType.REMOVE)
    private PropertyOwnerEntity propertyOwnerEntity;

    @OneToOne(targetEntity = AddressEntity.class)
    @JoinColumn(name = "fk_address", referencedColumnName = "id_address")
    private AddressEntity address;

    public PropertyEntity() {
    }

    public PropertyEntity(Long id, String propertyName, String description,
                          int roomsNumber, double livableArea, AddressEntity address) {
        this.id = id;
        this.propertyName = propertyName;
        this.description = description;
        this.roomsNumber = roomsNumber;
        this.livableArea = livableArea;
        this.address = address;
    }

    public Property toModel() {
        return new Property(
            id,
            propertyName,
            description,
            roomsNumber,
            livableArea,
            nonNull(address) ? address.toModel() : null
        );
    }

    public static PropertyEntity toEntity(Property property){
        return new PropertyEntity(
            property.id(),
            property.propertyName(),
            property.description(),
            property.roomsNumber(),
            property.livableArea(),
            AddressEntity.toEntity(property.address())
        );
    }
}
