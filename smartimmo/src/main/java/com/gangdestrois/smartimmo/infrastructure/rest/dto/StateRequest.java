package com.gangdestrois.smartimmo.infrastructure.rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gangdestrois.smartimmo.domain.event.Status;
import io.soabase.recordbuilder.core.RecordBuilder;
import jakarta.validation.constraints.NotNull;

@JsonIgnoreProperties()
@RecordBuilder
public record StateRequest(@NotNull Status status) {
}
