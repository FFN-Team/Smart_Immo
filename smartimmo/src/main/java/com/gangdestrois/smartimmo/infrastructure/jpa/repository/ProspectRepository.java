package com.gangdestrois.smartimmo.infrastructure.jpa.repository;

import com.gangdestrois.smartimmo.infrastructure.jpa.entity.ProspectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProspectRepository extends JpaRepository<ProspectEntity,Long> {
    @Override
    List<ProspectEntity> findAll();
}