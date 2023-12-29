package com.gangdestrois.smartimmo.domain.property.port;

import com.gangdestrois.smartimmo.domain.property.model.Property;
import com.gangdestrois.smartimmo.domain.property.model.Address;

import java.util.List;
import java.util.Optional;

public interface PropertyApi {
    List<Property> findAll();

    Optional<Property> findById(Long id);

    Property save(Property property);

    boolean existsByAddress(Address address);

    boolean existsPropertyWithAddressIsAndIdIsNot(Address address, Long id);

    boolean existsById(Long id);

    void deleteById(Long id);
}
