package com.gangdestrois.smartimmo.domain.notification;

import java.util.*;
import java.util.stream.Collectors;

public class EventManager {
    private final Map<EventType, List<EventListener>> listeners = new HashMap<>();

    public EventManager() {
        for (EventType eventType : EventType.values()) {
            this.listeners.put(eventType, new ArrayList<>());
        }
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
    }

    public void unSubscribe(EventType eventType, EventListener listener) {
        listeners.get(eventType).remove(listener);
    }

    public void notify(EventType eventType, Event event) {
        listeners.get(eventType).forEach(eventListener -> eventListener.update(eventType, event));
    }
}
