package com.gangdestrois.smartimmo.infrastructure.jpa.entity;

import com.gangdestrois.smartimmo.domain.property.model.Area;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "area")
public class AreaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_area")
    private Long id;

    @Column(name = "area_name")
    private String areaName;

    @Column(name = "area_surface_area")
    private Double areaSurfaceArea;

    @OneToMany(mappedBy = "area")
    private List<AddressEntity> addresses;

    public AreaEntity() {
    }

    public AreaEntity(Long id, String areaName, Double areaSurfaceArea) {
        this.id = id;
        this.areaName = areaName;
        this.areaSurfaceArea = areaSurfaceArea;
    }

    public Area toModel() {
        return new Area(
                id,
                areaName,
                areaSurfaceArea
        );
    }

    public static AreaEntity fromModelToEntity(Area area){
        return new AreaEntity(
                area.id(),
                area.areaName(),
                area.areaSurfaceArea()
        );
    }
}
