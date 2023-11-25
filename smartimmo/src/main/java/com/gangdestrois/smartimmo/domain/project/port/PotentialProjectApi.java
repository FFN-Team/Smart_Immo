package com.gangdestrois.smartimmo.domain.project.port;

import com.gangdestrois.smartimmo.domain.notification.Event;
import com.gangdestrois.smartimmo.domain.notification.EventListener;

import java.util.Set;

public interface PotentialProjectApi {
    Set<Event> notifyPotentialProjects(EventListener eventListener);
}
