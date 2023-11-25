package com.gangdestrois.smartimmo.domain.notification.port;

import com.gangdestrois.smartimmo.domain.notification.Event;
import com.gangdestrois.smartimmo.domain.notification.EventType;
import com.gangdestrois.smartimmo.domain.notification.ProjectNotification;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface EventTypeNotificationSpi {
    List<ProjectNotification> findByEventType(EventType eventType);

    Map<EventType, Set<Event>> findEventsGroupByEventType();

    void save(Map<EventType, Set<Event>> notifications);
}
