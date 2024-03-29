package com.gangdestrois.smartimmo.domain.potentialProject;

import com.gangdestrois.smartimmo.common.DomainComponent;
import com.gangdestrois.smartimmo.domain.event.EventListener;
import com.gangdestrois.smartimmo.domain.event.EventManager;
import com.gangdestrois.smartimmo.domain.event.PotentialProjectNotificationStrategy;
import com.gangdestrois.smartimmo.domain.event.model.Event;
import com.gangdestrois.smartimmo.domain.event.port.NotificationSpi;
import com.gangdestrois.smartimmo.domain.potentialProject.model.PotentialProject;
import com.gangdestrois.smartimmo.domain.potentialProject.port.PotentialProjectApi;
import com.gangdestrois.smartimmo.domain.potentialProject.port.PotentialProjectSpi;
import com.gangdestrois.smartimmo.domain.project.model.Project;
import com.gangdestrois.smartimmo.domain.project.port.ProjectSpi;
import com.gangdestrois.smartimmo.domain.prospect.model.Prospect;

import java.util.List;
import java.util.Optional;

import static com.gangdestrois.smartimmo.domain.event.enums.EventType.PROJECT_DUE_DATE_APPROACHING;

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
        var potentialProjectsToNotify = potentialProjectSpi.findPotentialProjectToNotify();
        var potentialProjectNotificationStrategy = new PotentialProjectNotificationStrategy(this.notificationSpi, eventManager);
        potentialProjectNotificationStrategy.makeNotification(potentialProjectsToNotify, PROJECT_DUE_DATE_APPROACHING);
        return potentialProjectNotificationStrategy.getNotifications(PROJECT_DUE_DATE_APPROACHING);
    }

    @Override
    public void subscription(EventListener eventListener) {
        eventManager.subscribe(PROJECT_DUE_DATE_APPROACHING, eventListener);
    }

    @Override
    public void unsubscription(EventListener eventListener) {
        eventManager.unSubscribe(PROJECT_DUE_DATE_APPROACHING, eventListener);
    }

    @Override
    public Optional<Prospect> findProspectByPotentialProjectId(Long potentialProjectId) {
        return projectSpi.findProjectByPotentialProjectId(potentialProjectId).map(Project::prospect);
    }
}
