package com.gangdestrois.smartimmo.domain.property.port;

import com.gangdestrois.smartimmo.domain.document.util.HolderSpi;
import com.gangdestrois.smartimmo.domain.property.model.Address;
import com.gangdestrois.smartimmo.domain.property.model.Property;

import java.util.List;

public interface PropertySpi extends HolderSpi<Property> {
    List<Property> findAll();

    Property save(Property property);

    boolean existsByAddress(Address address);

    boolean existsByAddressAndIdNot(Address address, Long id);

    boolean existsById(Long id);

    void deleteById(Long id);
}