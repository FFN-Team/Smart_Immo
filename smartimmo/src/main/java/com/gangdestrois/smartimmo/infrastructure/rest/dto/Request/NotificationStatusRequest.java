package com.gangdestrois.smartimmo.infrastructure.rest.dto.Request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gangdestrois.smartimmo.domain.event.enums.NotificationStatus;
import io.soabase.recordbuilder.core.RecordBuilder;
import jakarta.validation.constraints.NotNull;

@JsonIgnoreProperties()
@RecordBuilder
public record NotificationStatusRequest(@NotNull NotificationStatus status) {
}
