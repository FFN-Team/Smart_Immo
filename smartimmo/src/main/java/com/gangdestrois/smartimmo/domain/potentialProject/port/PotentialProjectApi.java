package com.gangdestrois.smartimmo.domain.potentialProject.port;

import com.gangdestrois.smartimmo.domain.event.EventListener;
import com.gangdestrois.smartimmo.domain.event.Notify;
import com.gangdestrois.smartimmo.domain.event.model.Event;
import com.gangdestrois.smartimmo.domain.prospect.model.Prospect;

import java.util.List;
import java.util.Optional;

public interface PotentialProjectApi {
    List<Event<? extends Notify>> notifyPotentialProjects();

    void subscription(EventListener eventListener);

    Optional<Prospect> findProspectByPotentialProjectId(Long potentialProjectId);
}
