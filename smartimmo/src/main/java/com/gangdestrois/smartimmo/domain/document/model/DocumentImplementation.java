package com.gangdestrois.smartimmo.domain.document.model;

import com.gangdestrois.smartimmo.domain.utils.Model;

public abstract class DocumentImplementation implements Document, Model {
    private Long id;
    private final String documentId;
    private final String name;
    private final String webContentLink;
    private final String webLink;

    DocumentImplementation(DocumentImplementationBuilder builder) {
        this.id = builder.id;
        this.documentId = builder.documentId;
        this.name = builder.name;
        this.webContentLink = builder.webContentLink;
        this.webLink = builder.webLink;
    }

    public String getName() {
        return this.name;
    }

    public String getDocumentId() {
        return documentId;
    }

    public String getWebContentLink() {
        return webContentLink;
    }

    public String getWebLink() {
        return webLink;
    }

    public Long id() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public static abstract class DocumentImplementationBuilder {
        private Long id;
        private String documentId;
        private String name;
        private String webContentLink;
        private String webLink;

        public DocumentImplementationBuilder() {
        }

        public DocumentImplementationBuilder id(Long id) {
            this.id = id;
            return self();
        }

        public DocumentImplementationBuilder documentId(String documentId) {
            this.documentId = documentId;
            return self();
        }

        public DocumentImplementationBuilder name(String name) {
            this.name = name;
            return self();
        }

        public DocumentImplementationBuilder webContentLink(String webContentLink) {
            this.webContentLink = webContentLink;
            return self();
        }

        public DocumentImplementationBuilder webLink(String webLink) {
            this.webLink = webLink;
            return self();
        }

        public abstract DocumentImplementation build();

        protected abstract DocumentImplementationBuilder self();
    }
}
