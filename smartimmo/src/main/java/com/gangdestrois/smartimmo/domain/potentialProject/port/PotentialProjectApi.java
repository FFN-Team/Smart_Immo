package com.gangdestrois.smartimmo.domain.potentialProject.port;

import com.gangdestrois.smartimmo.domain.event.Event;
import com.gangdestrois.smartimmo.domain.event.EventListener;
import com.gangdestrois.smartimmo.domain.potentialProject.model.PotentialProject;

import java.util.Set;

public interface PotentialProjectApi {
    Set<Event<PotentialProject>> notifyPotentialProjects();

    void subscription(EventListener eventListener);
}
