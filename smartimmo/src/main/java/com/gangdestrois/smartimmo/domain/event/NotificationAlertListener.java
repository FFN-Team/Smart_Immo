package com.gangdestrois.smartimmo.domain.event;

import com.gangdestrois.smartimmo.common.DomainComponent;
import com.gangdestrois.smartimmo.domain.event.model.Event;
import com.gangdestrois.smartimmo.domain.event.port.EventTypeNotificationSpi;

import java.util.HashSet;

@DomainComponent
public class NotificationAlertListener implements EventListener {
    private final EventTypeNotificationSpi eventTypeNotificationSpi;

    public NotificationAlertListener(EventTypeNotificationSpi eventTypeNotificationSpi) {
        this.eventTypeNotificationSpi = eventTypeNotificationSpi;
    }

    @Override
    public void update(Event notification) {
        var notifications = eventTypeNotificationSpi.findEventsGroupByEventType();
        if (notifications.containsKey(notification.getEventType()))
            notifications.get(notification.getEventType()).add(notification);
        else {
            var events = new HashSet<Event>();
            events.add(notification);
            notifications.put(notification.getEventType(), events);
        }
        eventTypeNotificationSpi.saveAll(notifications);
    }
}
