package com.gangdestrois.smartimmo.infrastructure.rest.dto;

import com.gangdestrois.smartimmo.domain.document.model.File;

import java.util.List;
import java.util.Map;

public record FilesByHolderResponse(List<FileByDocumentTypeResponse> files) {
    public static FilesByHolderResponse toDto(Map<String, List<File>> filesByDocumentType) {
        return new FilesByHolderResponse(FileByDocumentTypeResponse.fromModel(filesByDocumentType));
    }
}
