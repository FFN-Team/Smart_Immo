package com.gangdestrois.smartimmo.infrastructure.rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.soabase.recordbuilder.core.RecordBuilder;

import java.util.List;

@JsonIgnoreProperties
@RecordBuilder
public record FilesResponse(List<FileByDocumentTypeResponse> files) {
}
