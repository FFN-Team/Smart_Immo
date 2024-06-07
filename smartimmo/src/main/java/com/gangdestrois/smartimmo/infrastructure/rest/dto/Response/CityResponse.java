package com.gangdestrois.smartimmo.infrastructure.rest.dto.Response;

import com.gangdestrois.smartimmo.domain.property.model.City;
import io.soabase.recordbuilder.core.RecordBuilder;

@RecordBuilder
public record CityResponse(Long id, String name, Double surface_area) {
    public static CityResponse fromModel(City city) {
        return new CityResponse(
                city.id(),
                city.cityName(),
                city.citySurfaceArea()
        );
    }
}
