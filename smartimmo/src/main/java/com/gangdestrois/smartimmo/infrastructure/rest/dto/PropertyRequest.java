package com.gangdestrois.smartimmo.infrastructure.rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gangdestrois.smartimmo.domain.property.model.Address;
import com.gangdestrois.smartimmo.domain.property.model.Property;
import io.soabase.recordbuilder.core.RecordBuilder;
import jakarta.validation.constraints.*;

@JsonIgnoreProperties
@RecordBuilder
public record PropertyRequest
        (
                @NotNull @Size(max = 50) String property_name,
                @NotNull @Size(max = 300) String description,
                @NotNull @Min(value = 1) @Max(value = 1000) Integer number_of_rooms,
                @NotNull @DecimalMin(value = "1.0") @DecimalMax(value = "1000000.0") Double livable_area,
                @NotNull Long id_address
        ) {
    public Property toModel(Long id, Address address) {
        return new Property(id, property_name, description, number_of_rooms, livable_area, address);
    }

    public Long getIdAddress() {
        return id_address;
    }
}
