package com.gangdestrois.smartimmo.domain.property.port;

import com.gangdestrois.smartimmo.domain.property.entite.Address;
import com.gangdestrois.smartimmo.domain.property.entite.Property;
import com.gangdestrois.smartimmo.infrastructure.jpa.entity.AddressEntity;

import java.util.List;
import java.util.Optional;

public interface PropertySpi {
    List<Property> findAll();

    Optional<Property> findById(Long id);

    Property save(Property property);

    boolean existsByAddress(Address address);

    boolean existsByAddressAndIdNot(Address address, Long id);

    boolean existsById(Long id);

    void deleteById(Long id);
}