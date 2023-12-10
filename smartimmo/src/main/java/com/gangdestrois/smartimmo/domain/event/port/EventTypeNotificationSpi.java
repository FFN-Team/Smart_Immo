package com.gangdestrois.smartimmo.domain.event.port;

import com.gangdestrois.smartimmo.domain.event.Event;
import com.gangdestrois.smartimmo.domain.event.EventType;

import java.util.Map;
import java.util.Set;

public interface EventTypeNotificationSpi {

    Map<EventType, Set<Event>> findEventsGroupByEventType();

    void saveAll(Map<EventType, Set<Event>> notifications);
}
