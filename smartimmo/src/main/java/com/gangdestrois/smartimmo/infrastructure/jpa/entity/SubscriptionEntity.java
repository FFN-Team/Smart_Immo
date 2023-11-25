package com.gangdestrois.smartimmo.infrastructure.jpa.entity;

import com.gangdestrois.smartimmo.domain.notification.EventListener;
import com.gangdestrois.smartimmo.domain.notification.EventType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SubscriptionEntity {
    @Column(name = "event_type")
    @Enumerated(EnumType.STRING)
    private EventType eventType;

    @Column(name = "listener")
    private String eventListener;

    public Map<EventType, List<EventListener>> toModel(Map<EventType, List<EventListener>> map) {
        if (map.containsKey(eventType)) map.get(eventType).add(eventListener);
        else {
            var eventListeners = new ArrayList<EventListener>();
            eventListeners.add(eventListener);
            map.put(eventType, eventListeners);
        }
        return map;
    }
}
