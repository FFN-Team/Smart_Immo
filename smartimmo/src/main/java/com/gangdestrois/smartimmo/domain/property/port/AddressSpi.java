package com.gangdestrois.smartimmo.domain.property.port;

import com.gangdestrois.smartimmo.domain.property.model.Address;

import java.util.List;
import java.util.Optional;

public interface AddressSpi {
    Optional<Address> findById(Long id);
    List<Address> findByPropertyIsNull();
    List<Address> findByPropertyIsNullOrIdIs(Long id);
    boolean existsById(Long id);
}
