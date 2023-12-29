package com.gangdestrois.smartimmo.domain.event;

import com.gangdestrois.smartimmo.domain.event.port.NotificationApi;
import com.gangdestrois.smartimmo.domain.event.port.NotificationSpi;

import java.util.Optional;

public class NotificationManager implements NotificationApi {
    private final NotificationSpi notificationSpi;

    public NotificationManager(NotificationSpi notificationSpi) {
        this.notificationSpi = notificationSpi;
    }

    @Override
    public Optional<Event> findNotificationById(Long id) {
        return notificationSpi.findNotificationById(id);
    }

    @Override
    public Event save(Event event) {
        return notificationSpi.save(event);
    }
}
