package com.gangdestrois.smartimmo.domain.potentialProject.port;

import com.gangdestrois.smartimmo.domain.event.Event;
import com.gangdestrois.smartimmo.domain.event.EventListener;

import java.util.Set;

public interface PotentialProjectApi {
    Set<Event> notifyPotentialProjects();

    void subscription(EventListener eventListener);
}
