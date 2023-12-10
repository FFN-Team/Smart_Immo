package com.gangdestrois.smartimmo.domain.prospect.model;

import com.gangdestrois.smartimmo.domain.property.entite.Property;

import java.time.LocalDate;

public record Owner(LocalDate acquisitionDate, Property property, boolean isMain) {
}
