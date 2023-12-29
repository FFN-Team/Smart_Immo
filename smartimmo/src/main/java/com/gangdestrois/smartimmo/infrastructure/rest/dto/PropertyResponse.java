package com.gangdestrois.smartimmo.infrastructure.rest.dto;

import com.gangdestrois.smartimmo.domain.property.model.Property;
import io.soabase.recordbuilder.core.RecordBuilder;

@RecordBuilder
public record PropertyResponse(Long id, String nomBien, String description, int nbPiece, double surfaceHabitable) {
    public static PropertyResponse fromModel(Property property) {
        return new PropertyResponse(property.id(), property.propertyName(), property.description(), property.roomsNumber(), property.livableArea());
    }
}
