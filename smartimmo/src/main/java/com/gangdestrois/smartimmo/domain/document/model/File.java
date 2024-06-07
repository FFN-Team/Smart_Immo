package com.gangdestrois.smartimmo.domain.document.model;

import com.gangdestrois.smartimmo.domain.prospect.model.Prospect;

import java.time.LocalDate;
import java.util.Optional;

public class File extends DocumentImplementation {
    private Prospect owner;
    private DocumentType documentType;
    private final LocalDate created;

    protected File(FileBuilder builder) {
        super(builder);
        this.owner = builder.owner;
        this.documentType = builder.documentType;
        this.created = builder.created;
    }

    public Optional<Prospect> getOwner() {
        return Optional.ofNullable(owner);
    }

    public void setOwner(Prospect owner) {
        this.owner = owner;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }

    public static class FileBuilder extends DocumentImplementation.DocumentImplementationBuilder {
        private Prospect owner;
        private DocumentType documentType;
        private LocalDate created;

        public FileBuilder owner(Prospect owner) {
            this.owner = owner;
            return self();
        }

        public FileBuilder documentType(DocumentType documentType) {
            this.documentType = documentType;
            return self();
        }

        public FileBuilder created(LocalDate created) {
            this.created = created;
            return self();
        }

        @Override
        public File build() {
            return new File(this);
        }

        @Override
        protected FileBuilder self() {
            return this;
        }
    }
}
