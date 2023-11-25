package com.gangdestrois.smartimmo.infrastructure.rest.dto;

import com.gangdestrois.smartimmo.domain.notification.Event;

public record PotentialProjectEventResponse(String state,
                                            String message,
                                            String priority) {
    public static PotentialProjectEventResponse fromModel(Event potentialProjectEvent) {
        return new PotentialProjectEventResponse(potentialProjectEvent.state().name(),
                potentialProjectEvent.message(),
                potentialProjectEvent.priority().name());
    }
}
