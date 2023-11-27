package com.gangdestrois.smartimmo.infrastructure.jpa.repository;


import com.gangdestrois.smartimmo.domain.event.EventType;
import com.gangdestrois.smartimmo.infrastructure.jpa.entity.EventTypeNotificationEntity;
import com.gangdestrois.smartimmo.infrastructure.jpa.entity.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventTypeNotificationRepository extends JpaRepository<EventTypeNotificationEntity, Integer> {
    List<NotificationEntity> findNotificationsByEventType(EventType eventType);

    @Override
    List<EventTypeNotificationEntity> findAll();

}
