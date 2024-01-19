package com.gangdestrois.smartimmo.domain.document;

import com.gangdestrois.smartimmo.domain.tool.Model;

public abstract class DocumentImplementation implements Document, Model {
    private Long id;
    private final String documentId;
    private final String name;
    private final String webContentLink;
    private final String webLink;

    DocumentImplementation(Long id, String documentId, String name, String webContentLink, String webLink) {
        this.id = id;
        this.documentId = documentId;
        this.name = name;
        this.webContentLink = webContentLink;
        this.webLink = webLink;
    }

    DocumentImplementation(String documentId, String name, String webContentLink, String webLink) {
        this.documentId = documentId;
        this.name = name;
        this.webContentLink = webContentLink;
        this.webLink = webLink;
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
}
