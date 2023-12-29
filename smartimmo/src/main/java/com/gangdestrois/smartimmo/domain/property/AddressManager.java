package com.gangdestrois.smartimmo.domain.property;

import com.gangdestrois.smartimmo.domain.property.model.Address;
import com.gangdestrois.smartimmo.domain.property.port.AddressApi;
import com.gangdestrois.smartimmo.domain.property.port.AddressSpi;

import java.util.List;
import java.util.Optional;

public class AddressManager implements AddressApi {
    private final AddressSpi addressSpi;

    public AddressManager(AddressSpi addressSpi) {
        this.addressSpi = addressSpi;
    }

    @Override
    public Optional<Address> findById(Long id) {
        return addressSpi.findById(id);
    }

    @Override
    public List<Address> findByPropertyIsNull() {
        return addressSpi.findByPropertyIsNull();
    }
}
