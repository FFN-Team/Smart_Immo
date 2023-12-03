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
    @Query(value = "select pp.* from potential_project pp where due_date <= :date", nativeQuery = true)
    List<PotentialProjectEntity> findByDueDate(@Param("date") LocalDate date);

    @Query(value = """
            select pp.* FROM potential_project pp INNER JOIN notification n
            ON pp.potential_project_id = n.potential_project_id 
            WHERE n.state <> 'ARCHIVED' AND n.state <> 'DEALT'
            """, nativeQuery = true)
    List<PotentialProjectEntity> findPotentialProjectEntitiesByNotification();
}
