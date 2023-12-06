package com.gangdestrois.smartimmo.infrastructure.jpa;

import com.gangdestrois.smartimmo.domain.event.Event;
import com.gangdestrois.smartimmo.domain.event.EventType;
import com.gangdestrois.smartimmo.domain.event.ProjectNotification;
import com.gangdestrois.smartimmo.domain.event.port.EventTypeNotificationSpi;
import com.gangdestrois.smartimmo.infrastructure.jpa.entity.EventTypeNotificationEntity;
import com.gangdestrois.smartimmo.infrastructure.jpa.entity.NotificationEntity;
import com.gangdestrois.smartimmo.infrastructure.jpa.repository.EventTypeNotificationRepository;
import com.gangdestrois.smartimmo.infrastructure.jpa.repository.NotificationRepository;
import jakarta.transaction.Transactional;

import java.util.*;

import static java.util.Objects.nonNull;

public class EventTypeNotificationDataAdapter implements EventTypeNotificationSpi {
    private final EventTypeNotificationRepository eventTypeNotificationRepository;
    private final NotificationRepository notificationRepository;

    public EventTypeNotificationDataAdapter(EventTypeNotificationRepository eventTypeNotificationRepository, NotificationRepository notificationRepository) {
        this.eventTypeNotificationRepository = eventTypeNotificationRepository;
        this.notificationRepository = notificationRepository;
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
        List<EventTypeNotificationEntity> eventTypeNotificationEntities = new ArrayList<>();
        for (EventType eventType : notifications.keySet()) {
            for (Event event : notifications.get(eventType)) {
                if (nonNull(event.getId()) && notificationRepository.findById(event.getId()).isPresent()) {
                    eventTypeNotificationEntities.add(
                            new EventTypeNotificationEntity(eventType,
                                    notificationRepository.findById(event.getId()).get())
                    );
                }
            }
        }
        eventTypeNotificationRepository.saveAll(eventTypeNotificationEntities);
    }
}
