package com.gangdestrois.smartimmo.infrastructure.jpa.entity;

import com.gangdestrois.smartimmo.domain.property.model.City;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "city")
public class CityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_city")
    private Long id;

    @Column(name = "city_name")
    private String cityName;

    @Column(name = "city_surface_area")
    private Double citySurfaceArea;

    @OneToMany(mappedBy = "city")
    private List<AddressEntity> addresses;

    public CityEntity() {
    }

    public CityEntity(Long id, String cityName, Double citySurfaceArea) {
        this.id = id;
        this.cityName = cityName;
        this.citySurfaceArea = citySurfaceArea;
    }

    public City toModel() {
        return new City(
                id,
                cityName,
                citySurfaceArea
        );
    }

    public static CityEntity toEntity(City city) {
        return new CityEntity(
                city.id(),
                city.cityName(),
                city.citySurfaceArea()
        );
    }
}
