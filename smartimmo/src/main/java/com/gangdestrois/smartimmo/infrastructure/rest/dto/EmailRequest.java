package com.gangdestrois.smartimmo.infrastructure.rest.dto;

import com.gangdestrois.smartimmo.domain.event.EventType;

public record EmailRequest(Long prospectId, EventType eventType) {
}
