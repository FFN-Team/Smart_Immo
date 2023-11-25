package com.gangdestrois.smartimmo.domain.project;

import com.gangdestrois.smartimmo.domain.common.DomainComponent;
import com.gangdestrois.smartimmo.domain.notification.Event;
import com.gangdestrois.smartimmo.domain.notification.EventManager;
import com.gangdestrois.smartimmo.domain.project.port.PotentialProjectApi;
import com.gangdestrois.smartimmo.domain.project.port.ProjectSpi;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

import static com.gangdestrois.smartimmo.domain.notification.EventType.PROJECT_DUE_DATE_APPROACHING;

@DomainComponent
public class ProjectManager implements PotentialProjectApi {
    private final ProjectSpi projectSpi;
    private final EventManager eventManager;

    public ProjectManager(ProjectSpi projectSpi, EventManager eventManager) {
        this.projectSpi = projectSpi;
        this.eventManager = eventManager;
    }

    public Set<Event> notifyPotentialProjects() {
         projectSpi.findPotentialProjectsByDueDate(LocalDate.now().plusMonths(6))
                .forEach(potentialProject -> eventManager
                        .notify(PROJECT_DUE_DATE_APPROACHING, potentialProject.mapToEvent()));
         return eventManager.eventsFromEventType(PROJECT_DUE_DATE_APPROACHING);
    }
}
