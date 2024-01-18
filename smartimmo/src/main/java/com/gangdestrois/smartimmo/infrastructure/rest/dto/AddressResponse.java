package com.gangdestrois.smartimmo.infrastructure.rest.dto;

import com.gangdestrois.smartimmo.domain.property.model.Address;
import io.soabase.recordbuilder.core.RecordBuilder;

@RecordBuilder
public record AddressResponse(Long id, Integer flat_number, Integer floor, Integer street_number, StreetResponse street, AreaResponse area, CityResponse city) {
    public static AddressResponse fromModel(Address address) {
        return new AddressResponse(
                address.id(),
                address.flat_number(),
                address.floor(),
                address.streetNumber(),
                StreetResponse.fromModel(address.street()),
                AreaResponse.fromModel(address.area()),
                CityResponse.fromModel(address.city())
        );
    }
}
