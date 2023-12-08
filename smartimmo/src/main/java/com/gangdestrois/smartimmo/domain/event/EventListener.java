package com.gangdestrois.smartimmo.domain.event;

import com.gangdestrois.smartimmo.common.DomainComponent;

import java.util.Set;

@DomainComponent
public interface EventListener {
    void update(EventType eventType, Event event);

    Set<Event> eventsFromEventType(EventType... eventTypes);
}
