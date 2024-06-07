package com.gangdestrois.smartimmo.infrastructure.rest.dto.Response;

import com.gangdestrois.smartimmo.domain.event.model.Event;
import com.gangdestrois.smartimmo.domain.potentialProject.model.PotentialProject;

import static java.util.Objects.isNull;

public record PotentialProjectEventResponse(Long id,
                                            String state,
                                            String message,
                                            String priority,
                                            PotentialProjectResponse potentialProject) {
    public static PotentialProjectEventResponse fromModel(Event<PotentialProject> potentialProjectEvent) {
        if (isNull(potentialProjectEvent)) return null;
        return new PotentialProjectEventResponse(
                potentialProjectEvent.getId(),
                potentialProjectEvent.status().name(),
                potentialProjectEvent.message(),
                potentialProjectEvent.priority().name(),
                PotentialProjectResponse.fromModel(potentialProjectEvent.getElement()));
    }
}
