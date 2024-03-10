package com.gangdestrois.smartimmo.domain.property.model;

public record Property(Long id, String propertyName, String description, int roomsNumber, double livableArea,
                       Address address) {
}
