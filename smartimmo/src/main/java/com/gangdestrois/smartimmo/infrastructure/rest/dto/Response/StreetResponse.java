package com.gangdestrois.smartimmo.infrastructure.rest.dto.Response;

import com.gangdestrois.smartimmo.domain.property.model.Street;
import io.soabase.recordbuilder.core.RecordBuilder;

@RecordBuilder
public record StreetResponse(Long id, String name, Double surface_area) {
    public static StreetResponse fromModel(Street street) {
        return new StreetResponse(
                street.id(),
                street.streetName(),
                street.streetSurfaceArea()
        );
    }
}
