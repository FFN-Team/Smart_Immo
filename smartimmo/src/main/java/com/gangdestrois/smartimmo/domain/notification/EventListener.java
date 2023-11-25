package com.gangdestrois.smartimmo.domain.notification;

import java.util.Set;

public interface EventListener {
    void update(EventType eventType, Event event);

    Set<Event> eventsFromEventType(EventType... eventTypes);
}
