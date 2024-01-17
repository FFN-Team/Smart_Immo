package com.gangdestrois.smartimmo.infrastructure.jpa.repository;

import com.gangdestrois.smartimmo.domain.event.enums.EventType;
import com.gangdestrois.smartimmo.domain.event.enums.NotificationStatus;
import com.gangdestrois.smartimmo.infrastructure.jpa.entity.NotificationEntity;
import com.gangdestrois.smartimmo.infrastructure.jpa.entity.PotentialProjectEntity;
import com.gangdestrois.smartimmo.infrastructure.jpa.entity.ProspectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NotificationRepository extends JpaRepository<NotificationEntity, Long> {

    @Query(value = """
            SELECT n FROM NotificationEntity n WHERE n.potentialProject = :potentialProject
            AND n.notificationStatus = :notificationStatus AND n.type = :type
            """, nativeQuery = false)
    List<NotificationEntity> findNotificationEntitiesByPotentialProjectAndStatusAndType(PotentialProjectEntity potentialProject,
                                                                                        NotificationStatus notificationStatus,
                                                                                        EventType type);

    List<NotificationEntity> findNotificationEntitiesByProspectAndNotificationStatusAndType(ProspectEntity prospect,
                                                                                            NotificationStatus notificationStatus,
                                                                                            EventType type);

    List<NotificationEntity> findNotificationEntitiesByType(EventType eventType);
}
