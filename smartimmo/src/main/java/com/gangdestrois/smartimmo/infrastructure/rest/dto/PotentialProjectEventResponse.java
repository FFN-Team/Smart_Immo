package com.gangdestrois.smartimmo.infrastructure.rest.dto;

import com.gangdestrois.smartimmo.domain.notification.Event;

public record PotentialProjectEventResponse() {
    public static PotentialProjectEventResponse fromModel(Event potentialProjectEvent) {
        return new PotentialProjectEventResponse();
    }
}
