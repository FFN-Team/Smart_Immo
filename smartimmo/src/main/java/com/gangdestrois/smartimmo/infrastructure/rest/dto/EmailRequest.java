package com.gangdestrois.smartimmo.infrastructure.rest.dto;

import com.gangdestrois.smartimmo.domain.prospect.model.Prospect;

public record EmailRequest(Prospect prospect, String eventType) {
}
