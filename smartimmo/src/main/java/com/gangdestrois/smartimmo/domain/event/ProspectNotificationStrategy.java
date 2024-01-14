package com.gangdestrois.smartimmo.domain.event;

import com.gangdestrois.smartimmo.domain.event.model.Event;
import com.gangdestrois.smartimmo.domain.event.port.NotificationSpi;
import com.gangdestrois.smartimmo.domain.prospect.model.Prospect;

import java.util.Optional;

public class ProspectNotificationStrategy implements NotificationStrategy<Prospect> {
    private final NotificationSpi notificationSpi;

    public ProspectNotificationStrategy(NotificationSpi notificationStrategy) {
        this.notificationSpi = notificationStrategy;
    }

    @Override
    public Long save(Event<Prospect> event) {
        return notificationSpi.saveProspectNotification(event);
    }

    @Override
    public Optional<Event<Prospect>> findNotificationById(Long notificationId) {
        return notificationSpi.findProspectNotificationById(notificationId);
    }
}
