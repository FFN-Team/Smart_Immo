package com.gangdestrois.smartimmo.domain.property.entite;

public record Property(Long id, String propertyName, String description, int roomsNumber, double livableArea, Address address){
}
