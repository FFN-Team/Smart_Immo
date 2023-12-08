package com.gangdestrois.smartimmo.infrastructure.jpa.repository;


import com.gangdestrois.smartimmo.infrastructure.jpa.entity.BuyerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BuyerRepository extends JpaRepository<BuyerEntity,Long> {
    @Override
    List<BuyerEntity> findAll();

}