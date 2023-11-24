package com.gangdestrois.smartimmo.domain.notification;

import java.util.Map;
import java.util.Set;

public class NotificationAlertListener implements EventListener {
    Map<EventType, Set<Event>> notifications;

    @Override
    public void update(EventType eventType, Event notification) {
        notifications.get(eventType).add(notification);
    }
}
