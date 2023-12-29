package com.gangdestrois.smartimmo.infrastructure.jpa.repository;

import com.gangdestrois.smartimmo.infrastructure.jpa.entity.PropertyCriteriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PropertyCriteriaRepository extends JpaRepository<PropertyCriteriaEntity,Long> {
    @Override
    List<PropertyCriteriaEntity> findAll();
    PropertyCriteriaEntity findPropertyCriteriaEntitiesByBuyer_Id(Long id);

}