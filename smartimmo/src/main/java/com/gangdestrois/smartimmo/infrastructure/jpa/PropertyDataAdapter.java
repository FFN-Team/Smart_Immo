package com.gangdestrois.smartimmo.infrastructure.jpa;

import com.gangdestrois.smartimmo.domain.property.entite.Property;
import com.gangdestrois.smartimmo.domain.property.port.PropertySpi;
import com.gangdestrois.smartimmo.infrastructure.jpa.entity.PropertyEntity;
import com.gangdestrois.smartimmo.infrastructure.jpa.repository.PropertyRepository;

import java.util.List;

public class PropertyDataAdapter implements PropertySpi {
    private final PropertyRepository propertyRepository;

    public PropertyDataAdapter(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    @Override
    public List<Property> findAll() {
        return propertyRepository.findAll()
                .stream()
                .map(PropertyEntity::toModel)
                .toList();
    }

    @Override
    public Property findPropertyById(Long id) {
        return propertyRepository.findById(id).get().toModel();
    }
}
