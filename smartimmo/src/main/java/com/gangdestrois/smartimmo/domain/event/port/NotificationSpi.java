package com.gangdestrois.smartimmo.domain.event.port;

import com.gangdestrois.smartimmo.domain.event.Event;
import com.gangdestrois.smartimmo.domain.potentialProject.model.PotentialProject;

import java.util.List;
import java.util.Optional;

public interface NotificationSpi {
    List<Event<PotentialProject>> findAllProjectNotification();

    Integer save(Event<PotentialProject> event);

    Optional<Event<PotentialProject>> findProjectNotificationById(Integer projectNotificationId);
}
