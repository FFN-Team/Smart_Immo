package com.gangdestrois.smartimmo.infrastructure.rest.dto.Request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.soabase.recordbuilder.core.RecordBuilder;

@JsonIgnoreProperties()
@RecordBuilder
public record ExistingProspectFilterRequest(String prospectFilterName) {
}
