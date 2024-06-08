package com.gangdestrois.smartimmo.domain.document.port;

import com.gangdestrois.smartimmo.domain.document.model.DocumentType;

import java.util.Optional;

public interface DocumentTypeSpi {
    Optional<DocumentType> findDocumentTypeFromCode(String documentTypeCode);
}
