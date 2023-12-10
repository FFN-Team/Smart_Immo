package com.gangdestrois.smartimmo.infrastructure.rest.dto;

import com.gangdestrois.smartimmo.domain.event.Event;

public record PotentialBuyerEventResponse(String state,
                                             String message,
                                             String priority) {
    public static PotentialBuyerEventResponse fromModel(Event potentialBuyerEvent) {
        return new PotentialBuyerEventResponse(
                potentialBuyerEvent.state().name(),
                potentialBuyerEvent.message(),
                potentialBuyerEvent.priority().name());
    }
}
