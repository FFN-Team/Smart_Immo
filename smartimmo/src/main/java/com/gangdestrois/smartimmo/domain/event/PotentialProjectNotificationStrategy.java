package com.gangdestrois.smartimmo.domain.event;

import com.gangdestrois.smartimmo.domain.event.model.Event;
import com.gangdestrois.smartimmo.domain.event.port.NotificationSpi;
import com.gangdestrois.smartimmo.domain.potentialProject.model.PotentialProject;

import java.util.Optional;

public class PotentialProjectNotificationStrategy extends AbstractNotificationStrategy<PotentialProject> {

    public PotentialProjectNotificationStrategy(NotificationSpi notificationSpi, EventManager eventManager) {
        super(notificationSpi, eventManager);
    }

    @Override
    public Long save(Event<PotentialProject> event) {
        return super.getNotificationSpi().savePotentialProjectNotification(event);
    }

    @Override
    public Optional<Event<PotentialProject>> findNotificationById(Long notificationId) {
        return super.getNotificationSpi().findProjectNotificationById(notificationId);
    }

    public void notify(PotentialProject potentialProject) {
        var event = potentialProject.mapToEvent();
        if (getEventManager().nonSubscribe(event.getEventType())) return;
        getEventManager().notify(event);
    }
}
