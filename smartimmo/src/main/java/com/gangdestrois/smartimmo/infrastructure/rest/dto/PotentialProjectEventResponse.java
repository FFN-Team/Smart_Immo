package com.gangdestrois.smartimmo.infrastructure.rest.dto;

import com.gangdestrois.smartimmo.domain.event.Event;
import com.gangdestrois.smartimmo.domain.potentialProject.model.PotentialProject;

public record PotentialProjectEventResponse(String state,
                                            String message,
                                            String priority,
                                            PotentialProjectResponse potentialProject) {
    public static PotentialProjectEventResponse fromModel(Event<PotentialProject> potentialProjectEvent) {
        return new PotentialProjectEventResponse(potentialProjectEvent.state().name(),
                potentialProjectEvent.message(),
                potentialProjectEvent.priority().name(),
                PotentialProjectResponse.fromModel(potentialProjectEvent.getElement()));
    }
}
