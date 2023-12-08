package com.gangdestrois.smartimmo.domain.property.port;

import com.gangdestrois.smartimmo.domain.property.entite.Property;

import java.util.List;

public interface PropertyApi {
    List<Property> findAll();
}
