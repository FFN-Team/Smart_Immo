package com.gangdestrois.smartimmo.domain.notification;

import com.gangdestrois.smartimmo.domain.notification.port.EventTypeNotificationSpi;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class NotificationAlertListener implements EventListener {
    private final Map<EventType, Set<Event>> notifications;
    private final EventTypeNotificationSpi eventTypeNotificationSpi;

    public NotificationAlertListener(EventTypeNotificationSpi eventTypeNotificationSpi) {
        this.eventTypeNotificationSpi = eventTypeNotificationSpi;
        this.notifications =  this.eventTypeNotificationSpi.findEventsGroupByEventType();
    }

    @Override
    public void update(EventType eventType, Event notification) {
        if (notifications.containsKey(eventType)) notifications.get(eventType).add(notification);
        else {
            var events = new HashSet<Event>();
            events.add(notification);
            notifications.put(eventType, events);
        }
        eventTypeNotificationSpi.saveAll(notifications);
    }

    public Set<Event> eventsFromEventType(EventType... eventTypes) {
        return Arrays.stream(eventTypes)
                .map(eventType -> notifications.get(eventType))
                .flatMap(Set::stream)
                .collect(Collectors.toSet());
    }
}
