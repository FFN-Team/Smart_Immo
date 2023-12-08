package com.gangdestrois.smartimmo.infrastructure.rest.dto;

import com.gangdestrois.smartimmo.domain.event.ProjectNotification;

public record PotentialProjectEventResponse(String state,
                                            String message,
                                            String priority,
                                            Integer potentialProjectId) {
    public static PotentialProjectEventResponse fromModel(ProjectNotification potentialProjectEvent) {
        return new PotentialProjectEventResponse(potentialProjectEvent.state().name(),
                potentialProjectEvent.message(),
                potentialProjectEvent.priority().name(),
                potentialProjectEvent.potentialProject().id());
    }
}
