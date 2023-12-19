package com.gangdestrois.smartimmo.infrastructure.jpa.repository;

import com.gangdestrois.smartimmo.infrastructure.jpa.entity.PotentialProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PotentialProjectRepository extends JpaRepository<PotentialProjectEntity, Long> {
    @Query(value = """
            SELECT pp 
            FROM PotentialProjectEntity pp INNER join NotificationEntity n
            ON pp.id = n.id
            WHERE n.state <> 'ARCHIVED' AND n.state <> 'DEALT'
            """)
    List<PotentialProjectEntity> findPotentialProjectEntitiesByNotificationToDisplay();

    @Query(value = """
        select pp from PotentialProjectEntity pp where pp.notificationDate <= :today
    """)
    List<PotentialProjectEntity> findByNotificationDate(@Param("today") LocalDate today);
}
