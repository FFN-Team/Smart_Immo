package com.gangdestrois.smartimmo.domain.event;

import com.gangdestrois.smartimmo.common.DomainComponent;
import com.gangdestrois.smartimmo.domain.event.enums.EventType;
import com.gangdestrois.smartimmo.domain.event.enums.NotificationStatus;
import com.gangdestrois.smartimmo.domain.event.model.Event;
import com.gangdestrois.smartimmo.domain.event.port.NotificationSpi;
import com.gangdestrois.smartimmo.domain.event.port.SubscriptionSpi;

import java.util.Arrays;
import java.util.List;

import static java.util.Objects.nonNull;

@DomainComponent
public class EventManager {
    private final SubscriptionSpi subscriptionSpi;
    private final NotificationSpi notificationSpi;

    public EventManager(SubscriptionSpi subscriptionSpi, NotificationSpi notificationSpi) {
        this.subscriptionSpi = subscriptionSpi;
        this.notificationSpi = notificationSpi;
    }

    public List<Event<? extends Notify>> eventsFromEventType(EventType... eventTypes) {
        return Arrays.stream(eventTypes)
                .map(notificationSpi::findNotificationByEventType)
                .flatMap(List::stream)
                .toList();
    }

    public void subscribe(EventType eventType, EventListener listener) {
        if (subscriptionSpi.findAll().containsKey(eventType) &&
                !subscriptionSpi.findAll().get(eventType).contains(listener)) {
            subscriptionSpi.save(eventType, listener);
        } else if (!subscriptionSpi.findAll().containsKey(eventType))
            subscriptionSpi.save(eventType, listener);
    }

    public void unSubscribe(EventType eventType, EventListener listener) {
        if (!subscriptionSpi.findAll().containsKey(eventType))
            throw new EnumConstantNotPresentException(eventType.getClass(), eventType.name());
        subscriptionSpi.remove(eventType, listener);
    }

    public void notify(Event<? extends Notify> event) {
        subscriptionSpi.findAll()
                .get(event.getEventType())
                .forEach(eventListener -> eventListener.update(event));
    }

    public List<Event<>> makeNotifications(List<? extends Notify> elementToNotify, EventType eventType,
                                            NotificationStrategy<> notificationStrategy) {
        elementToNotify.stream()
                .filter(element -> notificationSpi.findNotificationByElementIdAndStatusAndEventType(
                        element.id(), NotificationStatus.statusesNotAlreadyDealt(), eventType).size() == 0)
                .forEach(element -> {
                    Event elementNotification = element.mapToEvent();
                    elementNotification.setId(notificationStrategy.save(elementNotification));
                    notify(elementNotification);
                });
        return eventsFromEventType(eventType).stream()
                .filter(event -> nonNull(event.getId()))
                .filter(event -> event.status().isNotAlreadyDealt())
                .map(projectNotification -> notificationStrategy.findNotificationById(projectNotification.getId())
                        .orElse(null))
                .toList();
    }
}
