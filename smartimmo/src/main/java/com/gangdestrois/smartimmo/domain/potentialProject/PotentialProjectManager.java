package com.gangdestrois.smartimmo.domain.potentialProject;

import com.gangdestrois.smartimmo.common.DomainComponent;
import com.gangdestrois.smartimmo.domain.event.EventListener;
import com.gangdestrois.smartimmo.domain.event.EventManager;
import com.gangdestrois.smartimmo.domain.event.enums.Status;
import com.gangdestrois.smartimmo.domain.event.model.Event;
import com.gangdestrois.smartimmo.domain.event.port.NotificationSpi;
import com.gangdestrois.smartimmo.domain.potentialProject.model.PotentialProject;
import com.gangdestrois.smartimmo.domain.potentialProject.port.PotentialProjectApi;
import com.gangdestrois.smartimmo.domain.potentialProject.port.PotentialProjectSpi;
import com.gangdestrois.smartimmo.domain.project.Project;
import com.gangdestrois.smartimmo.domain.project.port.ProjectSpi;
import com.gangdestrois.smartimmo.domain.prospect.model.Prospect;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.gangdestrois.smartimmo.domain.event.enums.EventType.PROJECT_DUE_DATE_APPROACHING;
import static java.util.Objects.nonNull;

@DomainComponent
public class PotentialProjectManager implements PotentialProjectApi {
    private final PotentialProjectSpi potentialProjectSpi;
    private final EventManager eventManager;
    private final NotificationSpi notificationSpi;
    private final ProjectSpi projectSpi;

    public PotentialProjectManager(PotentialProjectSpi potentialProjectSpi,
                                   EventManager eventManager,
                                   NotificationSpi notificationSpi,
                                   ProjectSpi projectSpi) {
        this.potentialProjectSpi = potentialProjectSpi;
        this.notificationSpi = notificationSpi;
        this.eventManager = eventManager;
        this.projectSpi = projectSpi;
    }

    @Override
    public List<Event<PotentialProject>> notifyPotentialProjects() {
        potentialProjectSpi.findPotentialProjectToNotify()
                .stream()
                .filter(potentialProject -> notificationSpi.findNotificationByElementIdAndStatusAndEventType(
                                potentialProject.id(), Status.TO_READ, PROJECT_DUE_DATE_APPROACHING)
                        .size() == 0)
                .forEach(potentialProject -> {
                    var projectNotification = potentialProject.mapToEvent();
                    projectNotification.setId(notificationSpi.savePotentialProjectNotification(projectNotification));
                    eventManager.notify(projectNotification);
                });
        return eventManager.eventsFromEventType(PROJECT_DUE_DATE_APPROACHING).stream()
                .filter(event -> nonNull(event.getId()))
                .filter(event -> event.status().isNotAlreadyDealt())
                .map(projectNotification -> notificationSpi.findProjectNotificationById(projectNotification.getId())
                        .orElse(null))
                .collect(Collectors.toList());
    }

    @Override
    public void subscription(EventListener eventListener) {
        eventManager.subscribe(PROJECT_DUE_DATE_APPROACHING, eventListener);
    }

    @Override
    public Optional<Prospect> findProspectByPotentialProjectId(Long potentialProjectId) {
        return projectSpi.findProjectByPotentialProjectId(potentialProjectId).map(Project::prospect);
    }
}
