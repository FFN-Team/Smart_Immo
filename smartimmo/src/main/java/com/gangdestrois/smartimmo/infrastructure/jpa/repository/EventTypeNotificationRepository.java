package com.gangdestrois.smartimmo.infrastructure.jpa.repository;


import com.gangdestrois.smartimmo.domain.notification.EventType;
import com.gangdestrois.smartimmo.infrastructure.jpa.entity.EventTypeNotificationEntity;
import com.gangdestrois.smartimmo.infrastructure.jpa.entity.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventTypeNotificationRepository extends JpaRepository<EventTypeNotificationEntity, Long> {
    List<NotificationEntity> findNotificationsByEventType(EventType eventType);

    @Query(value = """
            select event_type, notification
            from event_type_notification
            group by event_type
            """, nativeQuery = true)
    List<Object[]> findEventTypeNotificationEntityGroupByEventType();

    List<EventTypeNotificationEntity> findAll();

}
