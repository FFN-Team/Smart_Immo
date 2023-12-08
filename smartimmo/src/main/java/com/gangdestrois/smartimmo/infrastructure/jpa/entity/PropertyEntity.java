package com.gangdestrois.smartimmo.infrastructure.jpa.entity;


import com.gangdestrois.smartimmo.domain.property.entite.Property;
import jakarta.persistence.*;

@Entity
@Table(name="bien")
public class PropertyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="bien_id")
    private Long id;

    @Column(name="nom_bien")
    private String propertyName;

    @Column(name = "description")
    private String description;

    @Column(name="nb_piece")
    private int roomsNumber;

    @Column(name = "surface_habitable")
    private double livableArea;

    public Property toModel() {
        return new Property(id, propertyName, description, roomsNumber, livableArea);
    }
}
