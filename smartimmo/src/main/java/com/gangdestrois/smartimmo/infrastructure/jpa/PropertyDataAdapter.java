package com.gangdestrois.smartimmo.infrastructure.jpa;

import com.gangdestrois.smartimmo.domain.property.model.Property;
import com.gangdestrois.smartimmo.domain.property.model.Address;
import com.gangdestrois.smartimmo.domain.property.port.PropertySpi;
import com.gangdestrois.smartimmo.infrastructure.jpa.entity.AddressEntity;
import com.gangdestrois.smartimmo.infrastructure.jpa.entity.PropertyEntity;
import com.gangdestrois.smartimmo.infrastructure.jpa.repository.PropertyRepository;

import java.util.List;
import java.util.Optional;

public class PropertyDataAdapter implements PropertySpi {
    private final PropertyRepository propertyRepository;

    public PropertyDataAdapter(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    @Override
    public List<Property> findAll() {
        List<PropertyEntity> list= propertyRepository.findAll();
        List<Property> list2 = list.stream()
                .map(PropertyEntity::toModel)
                .toList();
        return list2;
    }

    @Override
    public Optional<Property> findById(Long id) {
        return propertyRepository.findById(id).map(PropertyEntity::toModel);
    }

    @Override
    public Property save(Property property) {
        PropertyEntity receivedPropertyEntity = PropertyEntity.fromModelToEntity(property);
        PropertyEntity savedPropertyEntity = propertyRepository.save(receivedPropertyEntity);

        return savedPropertyEntity.toModel();
    }

    @Override
    public boolean existsByAddress(Address address) {
        AddressEntity receivedAddressEntity = AddressEntity.toEntity(address);
        return propertyRepository.existsByAddress(receivedAddressEntity);
    }

    @Override
    public boolean existsByAddressAndIdNot(Address address, Long id) {
        AddressEntity receivedAddressEntity = AddressEntity.toEntity(address);
        return propertyRepository.existsByAddressAndIdNot(receivedAddressEntity, id);
    }

    @Override
    public boolean existsById(Long id) {
        return propertyRepository.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        propertyRepository.deleteById(id);
    }
}
