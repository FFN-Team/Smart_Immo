package com.gangdestrois.smartimmo.domain.potentialProject;

import com.gangdestrois.smartimmo.common.DomainComponent;
import com.gangdestrois.smartimmo.domain.event.Event;
import com.gangdestrois.smartimmo.domain.event.EventListener;
import com.gangdestrois.smartimmo.domain.event.EventManager;
import com.gangdestrois.smartimmo.domain.event.port.NotificationSpi;
import com.gangdestrois.smartimmo.domain.potentialProject.port.PotentialProjectApi;
import com.gangdestrois.smartimmo.domain.potentialProject.port.ProjectSpi;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

import static com.gangdestrois.smartimmo.domain.event.EventType.PROJECT_DUE_DATE_APPROACHING;

@DomainComponent
public class PotentialProjectManager implements PotentialProjectApi {
    private final ProjectSpi projectSpi;
    private final EventManager eventManager;
    private final NotificationSpi notificationSpi;

    public PotentialProjectManager(ProjectSpi projectSpi,
                                   EventManager eventManager,
                                   NotificationSpi notificationSpi) {
        this.projectSpi = projectSpi;
        this.notificationSpi = notificationSpi;
        this.eventManager = eventManager;
    }

    @Override
    public Set<Event> notifyPotentialProjects() {
        projectSpi.findPotentialProjectsByDueDate(LocalDate.now().plusMonths(6))
                .stream()
                .filter(potentialProject -> !projectSpi.findPotentialProjectsByNotificationToDisplay()
                        .contains(potentialProject))
                .forEach(potentialProject -> {
                    var event = potentialProject.mapToEvent();
                    event.setId(notificationSpi.save(event));
                    eventManager.notify(PROJECT_DUE_DATE_APPROACHING, event);
                });
        return eventManager.eventsFromEventType(PROJECT_DUE_DATE_APPROACHING).stream()
                .filter(event -> !event.state().isAlreadyDealt())
                .collect(Collectors.toSet());
    }

    @Override
    public void subscription(EventListener eventListener) {
        eventManager.subscribe(PROJECT_DUE_DATE_APPROACHING, eventListener);
    }
}
