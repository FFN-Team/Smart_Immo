package com.gangdestrois.smartimmo.infrastructure.jpa;

import com.gangdestrois.smartimmo.domain.document.model.DocumentType;
import com.gangdestrois.smartimmo.domain.document.port.DocumentTypeSpi;
import com.gangdestrois.smartimmo.infrastructure.jpa.entity.DocumentTypeEntity;
import com.gangdestrois.smartimmo.infrastructure.jpa.repository.DocumentTypeRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DocumentTypeDataAdapter implements DocumentTypeSpi {
    DocumentTypeRepository documentTypeRepository;

    @Override
    public Optional<DocumentType> findDocumentTypeFromCode(String documentTypeCode) {
        return documentTypeRepository.findByName(documentTypeCode).map(DocumentTypeEntity::toModel);
    }
}
