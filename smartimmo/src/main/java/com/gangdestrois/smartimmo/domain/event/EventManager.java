package com.gangdestrois.smartimmo.domain.event;

import com.gangdestrois.smartimmo.common.DomainComponent;
import com.gangdestrois.smartimmo.domain.event.enums.EventType;
import com.gangdestrois.smartimmo.domain.event.model.Event;
import com.gangdestrois.smartimmo.domain.event.port.EventTypeNotificationSpi;
import com.gangdestrois.smartimmo.domain.event.port.SubscriptionSpi;

import java.util.Arrays;
import java.util.List;

@DomainComponent
public class EventManager {
    private final SubscriptionSpi subscriptionSpi;
    private final EventTypeNotificationSpi eventTypeNotificationSpi;

    public EventManager(SubscriptionSpi subscriptionSpi, EventTypeNotificationSpi eventTypeNotificationSpi) {
        this.subscriptionSpi = subscriptionSpi;
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
}
