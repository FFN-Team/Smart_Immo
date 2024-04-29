package com.gangdestrois.smartimmo.infrastructure.rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gangdestrois.smartimmo.domain.document.model.DocumentType;
import io.soabase.recordbuilder.core.RecordBuilder;
import org.springframework.web.multipart.MultipartFile;

@JsonIgnoreProperties
@RecordBuilder
public record DocumentRequest(MultipartFile file, String fileName, DocumentType documentType, Long ownerId) {
}
