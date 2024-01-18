package com.gangdestrois.smartimmo.domain.document;

import com.gangdestrois.smartimmo.domain.tool.Composite;

import java.util.List;

public class Folder extends DocumentImplementation implements Composite<Document> {
    private List<Document> documents;

    public Folder(String name){
        super(name);
    }

    Folder(String name, Folder folder) {
        super(name, folder);
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

    public Boolean isComposite() {
        return true;
    }

    @Override
    public void accept(DocumentVisitor documentVisitor) {
        documentVisitor.visit(this);
        documents.forEach(c -> c.accept(documentVisitor));
    }
}
