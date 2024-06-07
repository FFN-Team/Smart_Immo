package com.gangdestrois.smartimmo.infrastructure.rest.dto.Response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.soabase.recordbuilder.core.RecordBuilder;

@JsonIgnoreProperties
@RecordBuilder
public record DocumentResponse(String name, String documentId, String webContentLink, String webLink) {
}
