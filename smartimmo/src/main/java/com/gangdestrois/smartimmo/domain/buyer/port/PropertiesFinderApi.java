package com.gangdestrois.smartimmo.domain.buyer.port;

import com.gangdestrois.smartimmo.domain.property.entite.Property;

import java.util.List;


public interface PropertiesFinderApi {
    List<Property> findPropertiesForBuyer(int id);
}
