package com.gangdestrois.smartimmo.domain.property.model;

import com.gangdestrois.smartimmo.domain.document.util.Holder;

public record Property(Long id, String propertyName, String description, int roomsNumber, double livableArea,
                       Address address) implements Holder {
}
