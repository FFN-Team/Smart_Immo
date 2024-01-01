package com.gangdestrois.smartimmo.infrastructure.rest.dto;

import io.soabase.recordbuilder.core.RecordBuilder;

@RecordBuilder
// que représentent label et value ?
public record ProspectDataResponse(String label, long value) {
}