package com.gangdestrois.smartimmo.domain.event;

import com.gangdestrois.smartimmo.domain.event.port.NotificationSpi;
import com.gangdestrois.smartimmo.domain.potentialProject.model.PotentialProject;

import java.util.Optional;

public class PotentialProjectNotificationStrategy implements NotificationStrategy<PotentialProject> {
    private final NotificationSpi notificationSpi;

    public PotentialProjectNotificationStrategy(NotificationSpi notificationSpi) {
        this.notificationSpi = notificationSpi;
    }

    @Override
    public Long save(Event event) {
        return notificationSpi.savePotentialProjectNotification(event);
    }

    @Override
    public Optional<Event<PotentialProject>> findNotificationById(Long notificationId) {
        return notificationSpi.findProjectNotificationById(notificationId);
    }
}
