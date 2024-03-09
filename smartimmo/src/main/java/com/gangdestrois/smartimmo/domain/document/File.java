package com.gangdestrois.smartimmo.domain.document;

import com.gangdestrois.smartimmo.domain.prospect.model.Prospect;

import java.time.LocalDate;
import java.util.Optional;

public class File extends DocumentImplementation {
    private Prospect owner;
    private DocumentType documentType;
    private final LocalDate created;

    public File(Long id, String fileId, String name, String webContentLink, String webLink, DocumentType documentType,
                LocalDate created) {
        super(id, fileId, name, webContentLink, webLink);
        this.documentType = documentType;
        this.created = created;
    }

    public File(String fileId, String name, String webContentLink, String webLink, LocalDate created) {
        super(fileId, name, webContentLink, webLink);
        this.created = created;
    }

    public Optional<Prospect> getOwner() {
        return Optional.ofNullable(owner);
    }

    public void setOwner(Prospect owner) {
        this.owner = owner;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }
}
