package com.gangdestrois.smartimmo.domain.event.port;

import com.gangdestrois.smartimmo.domain.event.Event;
import com.gangdestrois.smartimmo.domain.event.EventType;
import com.gangdestrois.smartimmo.domain.event.ProjectNotification;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface EventTypeNotificationSpi {
    List<ProjectNotification> findByEventType(EventType eventType);

    Map<EventType, Set<Event>> findEventsGroupByEventType();

    void saveAll(Map<EventType, Set<Event>> notifications);
}
