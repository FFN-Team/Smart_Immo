package com.gangdestrois.smartimmo.domain.project;

import com.gangdestrois.smartimmo.domain.common.DomainComponent;
import com.gangdestrois.smartimmo.domain.notification.Event;
import com.gangdestrois.smartimmo.domain.notification.EventListener;
import com.gangdestrois.smartimmo.domain.notification.EventManager;
import com.gangdestrois.smartimmo.domain.project.port.PotentialProjectApi;
import com.gangdestrois.smartimmo.domain.project.port.ProjectSpi;

import java.time.LocalDate;
import java.util.Set;

import static com.gangdestrois.smartimmo.domain.notification.EventType.PROJECT_DUE_DATE_APPROACHING;

@DomainComponent
public class PotentialProjectManager implements PotentialProjectApi {
    private final ProjectSpi projectSpi;
    private final EventManager eventManager;

    public PotentialProjectManager(ProjectSpi projectSpi, EventManager eventManager) {
        this.projectSpi = projectSpi;
        this.eventManager = eventManager;
    }

    @Override
    public Set<Event> notifyPotentialProjects(EventListener notificationAlertListener) {
        eventManager.subscribe(PROJECT_DUE_DATE_APPROACHING, notificationAlertListener);
        projectSpi.findPotentialProjectsByDueDate(LocalDate.now().plusMonths(6))
                .forEach(potentialProject -> eventManager
                        .notify(PROJECT_DUE_DATE_APPROACHING, potentialProject.mapToEvent()));
        return eventManager.eventsFromEventType(PROJECT_DUE_DATE_APPROACHING);
    }
}
