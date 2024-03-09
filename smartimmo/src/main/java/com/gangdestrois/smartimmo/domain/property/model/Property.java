package com.gangdestrois.smartimmo.domain.property.model;

import com.gangdestrois.smartimmo.domain.utils.Model;

public record Property(Long id, String propertyName, String description, int roomsNumber, double livableArea,
                       Address address)
        implements Model {
}
