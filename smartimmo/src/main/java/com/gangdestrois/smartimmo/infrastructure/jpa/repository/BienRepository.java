package com.gangdestrois.smartimmo.infrastructure.jpa.repository;

import com.gangdestrois.smartimmo.infrastructure.jpa.entity.BienEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BienRepository extends JpaRepository<BienEntity,Long> {
    @Override
    List<BienEntity> findAll();
}
