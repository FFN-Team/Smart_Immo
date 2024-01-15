package com.gangdestrois.smartimmo.domain.event;

import com.gangdestrois.smartimmo.domain.event.model.Event;
import com.gangdestrois.smartimmo.domain.event.port.NotificationSpi;
import com.gangdestrois.smartimmo.domain.prospect.model.Prospect;

import java.util.Optional;

public class ProspectNotificationStrategy extends AbstractNotificationStrategy<Prospect> {

    public ProspectNotificationStrategy(NotificationSpi notificationSpi, EventManager eventManager) {
        super(notificationSpi, eventManager);
    }

    @Override
    public Long save(Event<Prospect> event) {
        return super.getNotificationSpi().saveProspectNotification(event);
    }

    @Override
    public Optional<Event<Prospect>> findNotificationById(Long notificationId) {
        return super.getNotificationSpi().findProspectNotificationById(notificationId);
    }

    public Event<Prospect> saveNotification(Prospect potentialProject) {
        var elementNotification = potentialProject.mapToEvent();
        elementNotification.setId(save(elementNotification));
        return elementNotification;
    }
}
