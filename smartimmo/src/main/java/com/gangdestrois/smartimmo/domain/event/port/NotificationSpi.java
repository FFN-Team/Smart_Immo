package com.gangdestrois.smartimmo.domain.event.port;

import com.gangdestrois.smartimmo.domain.event.Event;
import com.gangdestrois.smartimmo.domain.event.EventType;
import com.gangdestrois.smartimmo.domain.event.Status;
import com.gangdestrois.smartimmo.domain.potentialProject.model.PotentialProject;
import com.gangdestrois.smartimmo.domain.prospect.model.Prospect;

import java.util.List;
import java.util.Optional;

public interface NotificationSpi {
    Long savePotentialProjectNotification(Event<PotentialProject> event);

    Long saveProspectNotification(Event<Prospect> event);

    Optional<Event<PotentialProject>> findProjectNotificationById(Long projectNotificationId);

    Optional<Event<Prospect>> findProspectNotificationById(Long prospectNotificationId);

    Optional<Event> findNotificationById(Long id);

    List<Event> findNotificationByElementIdAndStatusAndEventType(Long elementId, List<Status> status, EventType eventType);

    Event save(Event event);

    List<Event> findNotificationByEventType(EventType eventType);
}
