package com.gangdestrois.smartimmo.infrastructure.jpa.repository;

import com.gangdestrois.smartimmo.infrastructure.jpa.entity.CriteresBienAcquereurEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CriteresBienAcquereurRepository extends JpaRepository<CriteresBienAcquereurEntity,Long> {
    @Override
    List<CriteresBienAcquereurEntity> findAll();

}