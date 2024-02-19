package com.gangdestrois.smartimmo.infrastructure.jpa;

import com.gangdestrois.smartimmo.domain.event.Notify;
import com.gangdestrois.smartimmo.domain.event.enums.EventType;
import com.gangdestrois.smartimmo.domain.event.model.Event;
import com.gangdestrois.smartimmo.domain.event.port.EventTypeNotificationSpi;
import com.gangdestrois.smartimmo.infrastructure.jpa.entity.EventTypeNotificationEntity;
import com.gangdestrois.smartimmo.infrastructure.jpa.entity.NotificationEntity;
import com.gangdestrois.smartimmo.infrastructure.jpa.repository.EventTypeNotificationRepository;
import com.gangdestrois.smartimmo.infrastructure.jpa.repository.NotificationRepository;
import com.gangdestrois.smartimmo.infrastructure.rest.error.ExceptionEnum;
import com.gangdestrois.smartimmo.infrastructure.rest.error.NotFoundException;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

import static java.util.Objects.nonNull;

@Component
public class EventTypeNotificationDataAdapter implements EventTypeNotificationSpi {
    private static final Logger logger = LogManager.getLogger(EventTypeNotificationDataAdapter.class);
    private final EventTypeNotificationRepository eventTypeNotificationRepository;
    private final NotificationRepository notificationRepository;

    @Autowired
    public EventTypeNotificationDataAdapter(EventTypeNotificationRepository eventTypeNotificationRepository, NotificationRepository notificationRepository) {
        this.eventTypeNotificationRepository = eventTypeNotificationRepository;
        this.notificationRepository = notificationRepository;
    }

    public Map<EventType, Set<Event<? extends Notify>>> findEventsGroupByEventType() {
        Map<EventType, Set<Event<? extends Notify>>> map = new HashMap<>();
        eventTypeNotificationRepository.findAll()
                .forEach(eventTypeNotificationEntity -> eventTypeNotificationEntity.toModel(map));
        return map;
    }

    @Override
    @Transactional
    public void saveAll(Map<EventType, Set<Event<? extends Notify>>> notifications) {
        List<EventTypeNotificationEntity> eventTypeNotificationEntities = new ArrayList<>();
        for (EventType eventType : notifications.keySet()) {
            for (Event<? extends Notify> event : notifications.get(eventType)) {
                if (nonNull(event.getId()) && notificationRepository.findById(event.getId()).isPresent()) {
                    eventTypeNotificationEntities.add(
                            new EventTypeNotificationEntity(eventType,
                                    notificationRepository.findById(event.getId()).get())
                    );
                } else if (nonNull(event.getId()) && notificationRepository.findById(event.getId()).isEmpty()) {
                    logger.log(Level.ERROR, "Notification not found.");
                }
            }
        }
        eventTypeNotificationRepository.saveAll(eventTypeNotificationEntities);
    }

    @Override
    public void save(Event<? extends Notify> notification) {
        var notificationToSave = notificationRepository.findById(notification.getId())
                .orElseThrow(() -> new NotFoundException(ExceptionEnum.NOTIFICATION_NOT_FOUND,
                        String.format("Notification not found for id : %d.", notification.getId())));
        eventTypeNotificationRepository.save(new EventTypeNotificationEntity(notification.getEventType(), notificationToSave));
    }

    @Override
    public List<Event<Notify>> findNotificationByEventType(EventType eventType) {
        return eventTypeNotificationRepository.findNotificationsByEventType(eventType)
                .stream().map(NotificationEntity::toModel)
                .toList();
    }
}
