package com.gangdestrois.smartimmo.infrastructure.jpa.repository;

import com.gangdestrois.smartimmo.infrastructure.jpa.entity.PotentialProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PotentialProjectRepository extends JpaRepository<PotentialProjectEntity, Integer> {
    @Query(value = """
            select pp.* FROM potential_project pp INNER JOIN notification n
            ON pp.id_potential_project = n.fk_potential_project 
            WHERE n.state <> 'ARCHIVED' AND n.state <> 'DEALT'
            """, nativeQuery = true)
    List<PotentialProjectEntity> findPotentialProjectEntitiesByNotificationToDisplay();

    @Query(value = "select pp.* from potential_project pp where notification_date <= :today", nativeQuery = true)
    List<PotentialProjectEntity> findByNotificationDate(@Param("today") LocalDate today);
}
