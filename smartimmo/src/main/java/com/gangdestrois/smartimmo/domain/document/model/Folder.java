package com.gangdestrois.smartimmo.domain.document.model;

import com.gangdestrois.smartimmo.domain.utils.Composite;

import java.util.List;

public class Folder extends DocumentImplementation implements Composite<Document> {
    private final List<Document> documents;

    protected Folder(FolderBuilder builder) {
        super(builder);
        this.documents = builder.documents;
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

    public static class FolderBuilder extends DocumentImplementation.DocumentImplementationBuilder {
        private List<Document> documents;

        public FolderBuilder documents(List<Document> documents) {
            this.documents = documents;
            return self();
        }

        @Override
        public Folder build() {
            return new Folder(this);
        }

        @Override
        protected FolderBuilder self() {
            return this;
        }
    }
}
