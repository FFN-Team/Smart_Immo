package com.gangdestrois.smartimmo.infrastructure.jpa.entity;

import com.gangdestrois.smartimmo.domain.property.entite.Street;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "street")
public class StreetEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_street")
    private Long id;

    @Column(name = "street_name")
    private String streetName;

    @Column(name = "street_surface_area")
    private Double streetSurfaceArea;

    @OneToMany(mappedBy = "street")
    private List<AddressEntity> addresses;

    public StreetEntity() {
    }

    public StreetEntity(Long id, String streetName, Double streetSurfaceArea) {
        this.id = id;
        this.streetName = streetName;
        this.streetSurfaceArea = streetSurfaceArea;
    }

    public Street toModel() {
        return new Street(
            id,
            streetName,
            streetSurfaceArea
        );
    }

    public static StreetEntity toEntity(Street street){
        return new StreetEntity(
            street.id(),
            street.streetName(),
            street.streetSurfaceArea()
        );
    }
}
