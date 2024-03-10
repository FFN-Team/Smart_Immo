package com.gangdestrois.smartimmo.infrastructure.jpa.repository;

import com.gangdestrois.smartimmo.infrastructure.jpa.entity.AddressEntity;
import com.gangdestrois.smartimmo.infrastructure.jpa.entity.PropertyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRepository extends JpaRepository<PropertyEntity, Long> {
    @Override
    List<PropertyEntity> findAll();

    boolean existsByAddress(AddressEntity address);

    boolean existsByAddressAndIdNot(AddressEntity address, Long id);
}
