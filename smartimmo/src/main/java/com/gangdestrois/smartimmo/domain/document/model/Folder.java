package com.gangdestrois.smartimmo.domain.document.model;

import com.gangdestrois.smartimmo.domain.tool.Composite;

import java.util.ArrayList;
import java.util.List;

public class Folder extends DocumentImplementation implements Composite<Document> {
    private final List<Document> documents;

    public Folder(String fileId, String name, String webContentLink, String webLink) {
        super(fileId, name, webContentLink, webLink);
        this.documents = new ArrayList<>();
    }

    public Folder(Long id, String fileId, String name, String webContentLink, String webLink, List<Document> documents) {
        super(id, fileId, name, webContentLink, webLink);
        this.documents = new ArrayList<>(documents);
    }

    @Override
    public List<Document> getChildren() {
        return this.documents;
    }

    @Override
    public void addChild(Document c) {
        this.documents.add(c);
    }

    @Override
    public Boolean removeChild(Document t) {
        return this.documents.remove(t);
    }

    @Override
    public Boolean removeChildren(List<Document> t) {
        return this.documents.removeAll(t);
    }
}
