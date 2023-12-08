package com.gangdestrois.smartimmo.domain.potentialProject.port;

import com.gangdestrois.smartimmo.domain.event.EventListener;
import com.gangdestrois.smartimmo.domain.event.ProjectNotification;

import java.util.Set;

public interface PotentialProjectApi {
    Set<ProjectNotification> notifyPotentialProjects();

    void subscription(EventListener eventListener);
}
