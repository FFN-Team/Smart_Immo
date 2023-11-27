package com.gangdestrois.smartimmo.domain.event;

import com.gangdestrois.smartimmo.domain.common.DomainComponent;
import com.gangdestrois.smartimmo.domain.event.port.EventTypeNotificationSpi;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@DomainComponent
public class NotificationAlertListener implements EventListener {
    private final Map<EventType, Set<Event>> notifications;
    private final EventTypeNotificationSpi eventTypeNotificationSpi;

    public NotificationAlertListener(EventTypeNotificationSpi eventTypeNotificationSpi) {
        this.eventTypeNotificationSpi = eventTypeNotificationSpi;
        this.notifications = this.eventTypeNotificationSpi.findEventsGroupByEventType();
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
        Set<Event> events = new HashSet<>();
        for (EventType eventType : eventTypes) {
            for (Event event : notifications.get(eventType)) {
                events.add(event);
            }
        }
        return events;
    }
}
