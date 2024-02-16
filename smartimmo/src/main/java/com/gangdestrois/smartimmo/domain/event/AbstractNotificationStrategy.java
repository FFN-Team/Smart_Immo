package com.gangdestrois.smartimmo.domain.event;

import com.gangdestrois.smartimmo.domain.event.enums.EventType;
import com.gangdestrois.smartimmo.domain.event.enums.NotificationStatus;
import com.gangdestrois.smartimmo.domain.event.model.Event;
import com.gangdestrois.smartimmo.domain.event.port.NotificationSpi;

import java.util.List;

import static java.util.Objects.nonNull;

public abstract class AbstractNotificationStrategy<T extends Notify> implements NotificationStrategy<T> {
    private final NotificationSpi notificationSpi;
    private final EventManager eventManager;

    AbstractNotificationStrategy(NotificationSpi notificationSpi, EventManager eventManager) {
        this.notificationSpi = notificationSpi;
        this.eventManager = eventManager;
    }

    @Override
    public void makeNotification(List<T> elementToNotify, EventType eventType) {
        elementToNotify.stream()
                .filter(element -> notificationSpi.findNotificationByElementIdAndStatusAndEventType(
                        element.id(), NotificationStatus.statusesNotAlreadyDealt(), eventType).size() == 0)
                .forEach(this::notify);
    }

    @Override
    public List<Event<T>> getNotifications(EventType eventType) {
        return eventManager.eventsFromEventType(eventType).stream()
                .filter(event -> nonNull(event.getId()))
                .filter(event -> event.status().isNotAlreadyDealt())
                .map(projectNotification -> findNotificationById(projectNotification.getId()).orElse(null))
                .toList();
    }

    public NotificationSpi getNotificationSpi() {
        return notificationSpi;
    }

    public EventManager getEventManager() {
        return eventManager;
    }
}
