package com.gangdestrois.smartimmo.infrastructure.rest.dto;

import com.gangdestrois.smartimmo.domain.event.enums.EventType;

public record EmailRequest(Long prospectId, EventType eventType) {
}
