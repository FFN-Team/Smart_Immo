package com.gangdestrois.smartimmo.domain.property.port;

import com.gangdestrois.smartimmo.domain.property.model.Address;

import java.util.List;
import java.util.Optional;

public interface AddressApi {
    Optional<Address> findById(Long id);

    List<Address> findNonAssignedAddresses();

    List<Address> findNonAssignedAddressesPlusAddressWithId(Long id);

    boolean existsById(Long id);
}
