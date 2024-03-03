package com.gangdestrois.smartimmo.infrastructure.rest.dto;

import com.gangdestrois.smartimmo.domain.event.model.Event;
import com.gangdestrois.smartimmo.domain.prospect.model.Prospect;

public record PotentialBuyerEventResponse(Long id,
                                          String state,
                                          String message,
                                          String priority,
                                          ProspectResponse prospect) {
    public static PotentialBuyerEventResponse fromModel(Event<Prospect> potentialBuyerEvent) {
        return new PotentialBuyerEventResponse(
                potentialBuyerEvent.getId(),
                potentialBuyerEvent.status().name(),
                potentialBuyerEvent.message(),
                potentialBuyerEvent.priority().name(),
                ProspectResponse.fromModel(potentialBuyerEvent.getElement()));
    }
}
