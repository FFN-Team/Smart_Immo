package com.gangdestrois.smartimmo.domain.event;

import com.gangdestrois.smartimmo.common.DomainComponent;
import com.gangdestrois.smartimmo.domain.event.model.Event;
import com.gangdestrois.smartimmo.domain.event.port.NotificationApi;
import com.gangdestrois.smartimmo.domain.event.port.NotificationSpi;

import java.util.Optional;

@DomainComponent
public class NotificationManager implements NotificationApi {
    private final NotificationSpi notificationSpi;

    public NotificationManager(NotificationSpi notificationSpi) {
        this.notificationSpi = notificationSpi;
    }

    @Override
    public Optional<Event<? extends Notify>> findNotificationById(Long id) {
        return notificationSpi.findNotificationById(id);
    }

    @Override
    public Event save(Event event) {
        return notificationSpi.save(event);
    }
}
