package com.gangdestrois.smartimmo.domain.document;

import com.gangdestrois.smartimmo.domain.tool.Composite;

import java.util.List;

public class Folder extends DocumentImplementation implements Composite<Document> {
    private List<Document> documents;

    Folder(String name){
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

    public Integer getSize() {
        return documents.stream().map(Document::getSize).reduce(0, Integer::sum);
    }

    public Boolean isComposite() {
        return true;
    }

    public String toString() {
        return null;
    }
}
