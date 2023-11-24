package com.gangdestrois.smartimmo.domain.notification;

public interface EventListener {
    void update(EventType eventType, Event event);
}
