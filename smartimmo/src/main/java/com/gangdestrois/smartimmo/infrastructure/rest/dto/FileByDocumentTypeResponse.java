package com.gangdestrois.smartimmo.infrastructure.rest.dto;

import com.gangdestrois.smartimmo.domain.document.model.File;
import com.gangdestrois.smartimmo.infrastructure.rest.dto.Response.DocumentResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public record FileByDocumentTypeResponse(String documentTypeCode, List<DocumentResponse> documentTypeFiles) {
    public static List<FileByDocumentTypeResponse> fromModel(Map<String, List<File>> filesByDocumentType) {
        List<FileByDocumentTypeResponse> documentTypes = new ArrayList<>();

        filesByDocumentType.forEach((key, value) -> {
            FileByDocumentTypeResponse fileByDocumentTypeResponse = new FileByDocumentTypeResponse(key,
                    value.stream().map(DocumentResponse::fromModel).toList());
            documentTypes.add(fileByDocumentTypeResponse);
        });

        return documentTypes;
    }
}
