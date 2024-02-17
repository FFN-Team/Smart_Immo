package com.gangdestrois.smartimmo.infrastructure.jpa.repository;


import com.gangdestrois.smartimmo.domain.event.enums.EventType;
import com.gangdestrois.smartimmo.infrastructure.jpa.entity.EventTypeNotificationEntity;
import com.gangdestrois.smartimmo.infrastructure.jpa.entity.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventTypeNotificationRepository extends JpaRepository<EventTypeNotificationEntity, Long> {
    @Query(value = """
            SELECT n FROM EventTypeNotificationEntity en
            INNER JOIN NotificationEntity n ON n.id = en.notification.id
            WHERE en.eventType = :eventType
            """)
    List<NotificationEntity> findNotificationsByEventType(EventType eventType);

    @Override
    List<EventTypeNotificationEntity> findAll();

}
