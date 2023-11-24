package com.gangdestrois.smartimmo.domain.notification;

import java.util.HashMap;
import java.util.Map;

public class EventManager {
    Map<EventType, EventListener> listeners;

    public EventManager() {
        this.listeners = new HashMap<>();
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
