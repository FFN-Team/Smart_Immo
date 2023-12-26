package com.gangdestrois.smartimmo.infrastructure.jpa;

import com.gangdestrois.smartimmo.domain.property.model.Address;
import com.gangdestrois.smartimmo.domain.property.port.AddressSpi;
import com.gangdestrois.smartimmo.infrastructure.jpa.entity.AddressEntity;
import com.gangdestrois.smartimmo.infrastructure.jpa.repository.AddressRepository;

import java.util.List;
import java.util.Optional;

public class AddressDataAdapter implements AddressSpi {
    private final AddressRepository addressRepository;

    public AddressDataAdapter(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public Optional<Address> findById(Long id) {
        return addressRepository.findById(id).map(AddressEntity::toModel);
    }

    @Override
    public List<Address> findByPropertyIsNull() {
        return addressRepository.findByPropertyIsNull()
            .stream()
            .map(AddressEntity::toModel)
            .toList();
    }

    @Override
    public List<Address> findByPropertyIsNullOrIdIs(Long id) {
        return addressRepository.findByPropertyIsNullOrIdIs(id)
            .stream()
            .map(AddressEntity::toModel)
            .toList();
    }

    @Override
    public boolean existsById(Long id) {
        return addressRepository.existsById(id);
    }
}
