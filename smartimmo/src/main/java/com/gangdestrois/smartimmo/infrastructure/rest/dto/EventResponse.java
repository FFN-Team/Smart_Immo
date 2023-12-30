package com.gangdestrois.smartimmo.infrastructure.rest.dto;

import com.gangdestrois.smartimmo.domain.event.Event;
import com.gangdestrois.smartimmo.domain.potentialProject.model.PotentialProject;
import com.gangdestrois.smartimmo.domain.prospect.model.Prospect;
import io.soabase.recordbuilder.core.RecordBuilder;

@RecordBuilder
public record EventResponse(Long id,
                            String state,
                            String message,
                            String priority,
                            Object subResponse) {
    public static EventResponse fromModel(Event event) {
        return new EventResponse(
                event.getId(),
                event.status().name(),
                event.message(),
                event.priority().name(),
                (event.getElement() instanceof Prospect) ?
                        ProspectResponse.fromModel((Prospect)event.getElement()):
                        PotentialProjectResponse.fromModel((PotentialProject)event.getElement())
        );
    }
}
