package com.gangdestrois.smartimmo.domain.document;

import java.util.Optional;

public abstract class DocumentImplementation implements Document {
    private final String name;
    private Folder parent;

    DocumentImplementation(String name){
        this.name=name;
    }

    DocumentImplementation(String name, Folder parent) {
        this.name = name;
        this.parent = parent;
    }

    public String getName() {
        return this.name;
    }

    public Optional<Folder> getParent(){
        return Optional.ofNullable(this.parent);
    }
}
