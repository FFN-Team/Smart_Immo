package com.gangdestrois.smartimmo.domain.notification;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class NotificationAlertListener implements EventListener {
    private Map<EventType, Set<Event>> notifications;

    @Override
    public void update(EventType eventType, Event notification) {
        notifications.get(eventType).add(notification);
    }

    public Set<Event> eventsFromEventType(EventType... eventTypes) {
        return Arrays.stream(eventTypes)
                .map(eventType -> notifications.get(eventType))
                .flatMap(Set::stream)
                .collect(Collectors.toSet());
    }
}
