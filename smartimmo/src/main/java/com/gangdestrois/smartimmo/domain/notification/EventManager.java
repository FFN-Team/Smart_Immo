package com.gangdestrois.smartimmo.domain.notification;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class EventManager {
    private final Map<EventType, EventListener> listeners;

    public EventManager() {
        this.listeners = new HashMap<>();
    }

    public Set<Event> eventsFromEventType(EventType... eventTypes) {
        return Arrays.stream(eventTypes)
                .map(eventType -> listeners.get(eventType).eventsFromEventType(eventType))
                .flatMap(Set::stream)
                .collect(Collectors.toSet());
    }

    public void subscribe(EventType eventType, EventListener listener) {
        listeners.put(eventType, listener);
    }

    public void unSubscribe(EventType eventType, EventListener listener) {
        listeners.remove(eventType, listener);
    }

    public void notify(EventType eventType, Event event) {
        listeners.get(eventType).update(eventType, event);
    }
}
