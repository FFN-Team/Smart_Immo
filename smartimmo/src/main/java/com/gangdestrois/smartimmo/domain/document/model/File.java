package com.gangdestrois.smartimmo.domain.document.model;

import com.gangdestrois.smartimmo.domain.property.model.Property;
import com.gangdestrois.smartimmo.domain.prospect.model.Prospect;

import java.time.LocalDate;
import java.util.Optional;

public class File extends DocumentImplementation {
    private Prospect prospect;
    private Property property;
    private DocumentType documentType;
    private final LocalDate created;

    protected File(FileBuilder builder) {
        super(builder);
        this.prospect = builder.propspect;
        this.property = builder.property;
        this.documentType = builder.documentType;
        this.created = builder.created;
    }

    public Optional<Prospect> getProspect() {
        return Optional.ofNullable(prospect);
    }

    public void setProspect(Prospect prospect) {
        this.prospect = prospect;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }

    public static class FileBuilder extends DocumentImplementation.DocumentImplementationBuilder {
        private Prospect propspect;
        private Property property;
        private DocumentType documentType;
        private LocalDate created;

        public FileBuilder prospect(Prospect owner) {
            this.propspect = owner;
            return self();
        }

        public FileBuilder property(Property owner) {
            this.property = owner;
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
