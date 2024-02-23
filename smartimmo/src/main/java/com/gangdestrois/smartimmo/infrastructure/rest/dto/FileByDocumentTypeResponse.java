package com.gangdestrois.smartimmo.infrastructure.rest.dto;

import com.gangdestrois.smartimmo.domain.document.DocumentType;

import java.util.List;

public record FileByDocumentTypeResponse(DocumentType documentType, List<DocumentResponse> files) {
}
