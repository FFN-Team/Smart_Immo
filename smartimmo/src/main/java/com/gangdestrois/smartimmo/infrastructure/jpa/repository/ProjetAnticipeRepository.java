package com.gangdestrois.smartimmo.infrastructure.jpa.repository;

import com.gangdestrois.smartimmo.infrastructure.jpa.entity.ProjetAnticipeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjetAnticipeRepository extends JpaRepository<ProjetAnticipeEntity, Long> {
    List<ProjetAnticipeEntity> findByDatePrevue();
}