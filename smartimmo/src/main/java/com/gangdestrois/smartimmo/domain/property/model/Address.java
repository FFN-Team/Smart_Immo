package com.gangdestrois.smartimmo.domain.property.model;

public record Address(Long id, Integer streetNumber, Street street, City city) {
    @Override
    public String toString() {
        return String.format("%d %s %s", streetNumber, street.streetName(), city.cityName());
    }
}
