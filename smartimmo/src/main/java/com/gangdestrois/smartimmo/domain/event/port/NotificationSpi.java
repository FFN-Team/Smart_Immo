package com.gangdestrois.smartimmo.domain.event.port;

import com.gangdestrois.smartimmo.domain.event.Event;
import com.gangdestrois.smartimmo.domain.potentialProject.model.PotentialProject;
import com.gangdestrois.smartimmo.domain.prospect.model.Prospect;

import java.util.List;
import java.util.Optional;

public interface NotificationSpi {
    List<Event<PotentialProject>> findAllProjectNotification();

    Long savePotentialProjectNotification(Event<PotentialProject> event);

    Long saveProspectNotification(Event<Prospect> event);

    Optional<Event<PotentialProject>> findProjectNotificationById(Long projectNotificationId);
}
