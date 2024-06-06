package com.gangdestrois.smartimmo.infrastructure.rest.dto;

import com.gangdestrois.smartimmo.domain.document.model.DocumentType;
import com.gangdestrois.smartimmo.domain.document.model.File;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public record FileByDocumentTypeResponse(String documentTypeCode, List<DocumentResponse> documentTypeFiles) {
    static List<FileByDocumentTypeResponse> documentTypes = new ArrayList<>();

    public static List<FileByDocumentTypeResponse> fromModel(Map<DocumentType, List<File>> filesByDocumentType) {
        filesByDocumentType.forEach((key, value) -> {
            FileByDocumentTypeResponse fileByDocumentTypeResponse = new FileByDocumentTypeResponse(key.name(),
                    value.stream().map(DocumentResponse::fromModel).toList());
            documentTypes.add(fileByDocumentTypeResponse);
        });
        return documentTypes;
    }
}
