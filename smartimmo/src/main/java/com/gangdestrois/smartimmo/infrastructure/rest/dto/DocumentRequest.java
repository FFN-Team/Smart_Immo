package com.gangdestrois.smartimmo.infrastructure.rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gangdestrois.smartimmo.domain.document.DocumentType;
import io.soabase.recordbuilder.core.RecordBuilder;

@JsonIgnoreProperties
@RecordBuilder
public record DocumentRequest(String filePath, String fileName, DocumentType documentType, Long ownerId) {
}
