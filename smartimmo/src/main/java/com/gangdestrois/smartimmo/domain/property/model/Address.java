package com.gangdestrois.smartimmo.domain.property.model;

public record Address(Long id, Integer flat_number, Integer floor, Integer streetNumber, Street street, Area area,
                      City city) {
    @Override
    public String toString() {
        return String.format("%d %s %s", streetNumber, street.streetName(), city.cityName());
    }
}
