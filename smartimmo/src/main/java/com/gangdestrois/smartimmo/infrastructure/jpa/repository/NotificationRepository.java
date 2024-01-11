package com.gangdestrois.smartimmo.infrastructure.jpa.repository;

import com.gangdestrois.smartimmo.domain.event.enums.EventType;
import com.gangdestrois.smartimmo.domain.event.enums.Status;
import com.gangdestrois.smartimmo.infrastructure.jpa.entity.NotificationEntity;
import com.gangdestrois.smartimmo.infrastructure.jpa.entity.PotentialProjectEntity;
import com.gangdestrois.smartimmo.infrastructure.jpa.entity.ProspectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NotificationRepository extends JpaRepository<NotificationEntity, Long> {

    @Query(value = """
            SELECT n FROM NotificationEntity n WHERE n.potentialProject = :potentialProject
            AND n.status = :status AND n.type = :type
            """, nativeQuery = false)
    List<NotificationEntity> findNotificationEntitiesByPotentialProjectAndStatusAndType(PotentialProjectEntity potentialProject,
                                                                                        Status status,
                                                                                        EventType type);

    List<NotificationEntity> findNotificationEntitiesByProspectAndStatusAndType(ProspectEntity prospect,
                                                                                Status status,
                                                                                EventType type);

    List<NotificationEntity> findNotificationEntitiesByType(EventType eventType);
}
