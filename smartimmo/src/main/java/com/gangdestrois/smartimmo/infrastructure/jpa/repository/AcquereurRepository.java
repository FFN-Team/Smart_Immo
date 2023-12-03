package com.gangdestrois.smartimmo.infrastructure.jpa.repository;


import com.gangdestrois.smartimmo.infrastructure.jpa.entity.AcquereurEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AcquereurRepository extends JpaRepository<AcquereurEntity,Long> {
    @Override
    List<AcquereurEntity> findAll();

}