package com.gangdestrois.smartimmo.infrastructure.rest.dto;

import com.gangdestrois.smartimmo.domain.property.model.Address;
import io.soabase.recordbuilder.core.RecordBuilder;

@RecordBuilder
public record AddressResponse(Long id, Integer street_number, StreetResponse street, CityResponse city) {
    public static AddressResponse fromModel(Address address) {
        return new AddressResponse(
                address.id(),
                address.streetNumber(),
                StreetResponse.fromModel(address.street()),
                CityResponse.fromModel(address.city())
        );
    }
}
