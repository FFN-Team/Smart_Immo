package com.gangdestrois.smartimmo.infrastructure.rest.dto;

import io.soabase.recordbuilder.core.RecordBuilder;

@RecordBuilder
public record ProspectDataResponse(String label, long value) {
}