package com.gangdestrois.smartimmo.domain.event.port;

import com.gangdestrois.smartimmo.domain.event.enums.EventType;
import com.gangdestrois.smartimmo.domain.event.enums.Status;
import com.gangdestrois.smartimmo.domain.event.model.Event;
import com.gangdestrois.smartimmo.domain.potentialProject.model.PotentialProject;
import com.gangdestrois.smartimmo.domain.prospect.model.Prospect;

import java.util.List;
import java.util.Optional;

public interface NotificationSpi {
    Long savePotentialProjectNotification(Event<PotentialProject> event);

    Long saveProspectNotification(Event<Prospect> event);

    Optional<Event<PotentialProject>> findProjectNotificationById(Long projectNotificationId);

    Optional<Event> findNotificationById(Long id);

    List<Event> findNotificationByElementIdAndStatusAndEventType(Long elementId, Status status, EventType eventType);

    Event save(Event event);

    List<Event> findNotificationByEventType(EventType eventType);
}
