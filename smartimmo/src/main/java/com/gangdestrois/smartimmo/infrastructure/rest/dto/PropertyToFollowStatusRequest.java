package com.gangdestrois.smartimmo.infrastructure.rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gangdestrois.smartimmo.domain.portfolio.propertiesToFollow.enums.PropertyToFollowStatus;
import io.soabase.recordbuilder.core.RecordBuilder;
import jakarta.validation.constraints.NotNull;

@JsonIgnoreProperties()
@RecordBuilder
public record PropertyToFollowStatusRequest(@NotNull PropertyToFollowStatus propertyToFollowStatus) {
}
