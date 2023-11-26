package com.gangdestrois.smartimmo.infrastructure.jpa;

import com.gangdestrois.smartimmo.domain.notification.Event;
import com.gangdestrois.smartimmo.domain.notification.EventType;
import com.gangdestrois.smartimmo.domain.notification.ProjectNotification;
import com.gangdestrois.smartimmo.domain.notification.port.EventTypeNotificationSpi;
import com.gangdestrois.smartimmo.infrastructure.jpa.entity.EventTypeNotificationEntity;
import com.gangdestrois.smartimmo.infrastructure.jpa.entity.NotificationEntity;
import com.gangdestrois.smartimmo.infrastructure.jpa.repository.EventTypeNotificationRepository;
import jakarta.transaction.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class EventTypeNotificationDataAdapter implements EventTypeNotificationSpi {
    private final EventTypeNotificationRepository eventTypeNotificationRepository;

    public EventTypeNotificationDataAdapter(EventTypeNotificationRepository eventTypeNotificationRepository) {
        this.eventTypeNotificationRepository = eventTypeNotificationRepository;
    }

    @Override
    public List<ProjectNotification> findByEventType(EventType eventType) {
        return eventTypeNotificationRepository.findNotificationsByEventType(eventType)
                .stream()
                .map(NotificationEntity::toModel)
                .toList();
    }

    public Map<EventType, Set<Event>> findEventsGroupByEventType() {
        Map<EventType, Set<Event>> map = new HashMap<>();
        eventTypeNotificationRepository.findAll()
                .forEach(eventTypeNotificationEntity -> eventTypeNotificationEntity.toModel(map));
        return map;
    }

    @Override
    @Transactional
    public void saveAll(Map<EventType, Set<Event>> notifications) {
        var notificationsToSave = notifications.entrySet().stream()
                .flatMap(entry -> entry.getValue().stream()
                        .map(event -> new EventTypeNotificationEntity(entry.getKey(), new NotificationEntity(event))))
                .collect(Collectors.toList());
        eventTypeNotificationRepository.saveAll(notificationsToSave);
    }
}
