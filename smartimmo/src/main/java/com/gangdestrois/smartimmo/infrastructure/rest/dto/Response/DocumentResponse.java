package com.gangdestrois.smartimmo.infrastructure.rest.dto.Response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gangdestrois.smartimmo.domain.document.model.File;
import io.soabase.recordbuilder.core.RecordBuilder;

@JsonIgnoreProperties
@RecordBuilder
public record DocumentResponse(String name, String documentId, String webContentLink, String webLink) {
    public static DocumentResponse fromModel(File file) {
        return new DocumentResponse(file.getName(), file.getDocumentId(), file.getWebContentLink(), file.getWebLink());
    }
}
