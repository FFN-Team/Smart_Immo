package com.gangdestrois.smartimmo.domain.property;

import com.gangdestrois.smartimmo.domain.property.entite.Address;
import com.gangdestrois.smartimmo.domain.property.entite.Property;
import com.gangdestrois.smartimmo.domain.property.port.PropertyApi;
import com.gangdestrois.smartimmo.domain.property.port.PropertySpi;
import org.springframework.data.domain.Example;

import java.util.List;
import java.util.Optional;

public class PropertyManager implements PropertyApi {
    PropertySpi propertySpi;

    public PropertyManager(PropertySpi propertySpi) {
        this.propertySpi = propertySpi;
    }

    @Override
    public List<Property> findAll() {
        return propertySpi.findAll();
    }

    @Override
    public Optional<Property> findById(Long id) {
        return propertySpi.findById(id);
    }

    @Override
    public Property save(Property property){
        return propertySpi.save(property);
    }

    @Override
    public boolean existsByAddress(Address address) {
        return propertySpi.existsByAddress(address);
    }

    @Override
    public boolean existsByAddressAndIdNot(Address address, Long id) {
        return propertySpi.existsByAddressAndIdNot(address, id);
    }

    @Override
    public boolean existsById(Long id) {
        return propertySpi.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        propertySpi.deleteById(id);
    }
}
