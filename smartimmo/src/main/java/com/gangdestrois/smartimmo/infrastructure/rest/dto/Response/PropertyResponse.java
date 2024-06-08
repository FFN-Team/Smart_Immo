package com.gangdestrois.smartimmo.infrastructure.rest.dto.Response;

import com.gangdestrois.smartimmo.domain.property.model.Property;
import io.soabase.recordbuilder.core.RecordBuilder;

@RecordBuilder
public record PropertyResponse(Long id, String property_name, String description, Integer number_of_rooms,
                               Double livable_area, AddressResponse address) {
    public static PropertyResponse fromModel(Property property) {
        return new PropertyResponse(
                property.id(),
                property.propertyName(),
                property.description(),
                property.roomsNumber(),
                property.livableArea(),
                AddressResponse.fromModel(property.address())
        );
    }
}
