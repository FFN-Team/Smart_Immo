package com.gangdestrois.smartimmo.infrastructure.jpa.repository;

import com.gangdestrois.smartimmo.infrastructure.jpa.entity.PotentialProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PotentialProjectRepository extends JpaRepository<PotentialProjectEntity, Integer> {
    List<PotentialProjectEntity> findByDueDate(LocalDate date);
}
