package com.gangdestrois.smartimmo.domain.notification;

import com.gangdestrois.smartimmo.domain.notification.port.SubscriptionSpi;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class EventManager {
    private final Map<EventType, List<EventListener>> listeners;
    private final SubscriptionSpi subscriptionSpi;

    public EventManager(SubscriptionSpi subscriptionSpi) {
        this.subscriptionSpi = subscriptionSpi;
        listeners = this.subscriptionSpi.findAll();
    }

    public Set<Event> eventsFromEventType(EventType... eventTypes) {
        return Arrays.stream(eventTypes)
                .map(eventType -> listeners.get(eventType).stream()
                        .map(EventListener::eventsFromEventType)
                        .flatMap(Set::stream)
                        .collect(Collectors.toSet()))
                .flatMap(Set::stream)
                .collect(Collectors.toSet());
    }

    public void subscribe(EventType eventType, EventListener listener) {
        listeners.get(eventType).add(listener);
        subscriptionSpi.saveAll(listeners);
    }

    public void unSubscribe(EventType eventType, EventListener listener) {
        listeners.get(eventType).remove(listener);
        subscriptionSpi.saveAll(listeners);
    }

    public void notify(EventType eventType, Event event) {
        listeners.get(eventType).forEach(eventListener -> eventListener.update(eventType, event));
    }
}
