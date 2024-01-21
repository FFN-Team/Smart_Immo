package com.gangdestrois.smartimmo.domain.event;

import com.gangdestrois.smartimmo.common.DomainComponent;
import com.gangdestrois.smartimmo.domain.event.enums.EventType;
import com.gangdestrois.smartimmo.domain.event.model.Event;
import com.gangdestrois.smartimmo.domain.event.port.NotificationApi;
import com.gangdestrois.smartimmo.domain.event.port.NotificationSpi;
import com.gangdestrois.smartimmo.domain.event.port.EventTypeNotificationSpi;
import com.gangdestrois.smartimmo.domain.event.port.SubscriptionSpi;
import com.gangdestrois.smartimmo.infrastructure.rest.dto.EventResponse;
import com.gangdestrois.smartimmo.infrastructure.rest.dto.NotificationStatusRequest;
import com.gangdestrois.smartimmo.infrastructure.rest.error.explicitException.NotFoundException;

import java.util.Arrays;
import java.util.List;

@DomainComponent
public class EventManager implements NotificationApi {
    private final SubscriptionSpi subscriptionSpi;
    private final NotificationSpi notificationSpi;
    private final EventTypeNotificationSpi eventTypeNotificationSpi;

    public EventManager(SubscriptionSpi subscriptionSpi, NotificationSpi notificationSpi, EventTypeNotificationSpi eventTypeNotificationSpi) {
        this.subscriptionSpi = subscriptionSpi;
        this.notificationSpi = notificationSpi;
        this.eventTypeNotificationSpi = eventTypeNotificationSpi;
    }

    public List<Event<Notify>> eventsFromEventType(EventType... eventTypes) {
        return Arrays.stream(eventTypes)
                .map(eventTypeNotificationSpi::findNotificationByEventType)
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

    public boolean isSubscribe(EventType eventType) {
        return subscriptionSpi.findAll()
                .get(eventType)
                .size() > 0;
    }

    public boolean nonSubscribe(EventType eventType) {
        return !isSubscribe(eventType);
    }

    public void notify(Event<? extends Notify> event) {
        subscriptionSpi.findAll()
                .get(event.getEventType())
                .forEach(eventListener -> eventListener.update(event));
    }

    @Override
    public EventResponse save(Long notificationId, NotificationStatusRequest notificationStatusRequest) {
        Event<Notify> originalEvent = notificationSpi.findNotificationById(notificationId)
                .orElseThrow(() -> new NotFoundException(notificationId, "notification"));
        Event<Notify> eventToSave = new Event<Notify>(
                notificationId,
                notificationStatusRequest.status(),
                originalEvent.message(),
                originalEvent.priority(),
                originalEvent.getElement(),
                originalEvent.getEventType()
        );
        Event<Notify> savedEvent = notificationSpi.save(eventToSave);
        return EventResponse.fromModel(savedEvent);
    }
}
