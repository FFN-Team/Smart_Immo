package com.gangdestrois.smartimmo.domain.property;

import com.gangdestrois.smartimmo.domain.property.entite.Property;
import com.gangdestrois.smartimmo.domain.property.port.PropertyApi;
import com.gangdestrois.smartimmo.domain.property.port.PropertySpi;

import java.util.List;

public class PropertyManager implements PropertyApi {
    PropertySpi propertySpi;

    public PropertyManager(PropertySpi propertySpi) {
        this.propertySpi = propertySpi;
    }

    @Override
    public List<Property> findAll() {
        return propertySpi.findAll();
    }
}
