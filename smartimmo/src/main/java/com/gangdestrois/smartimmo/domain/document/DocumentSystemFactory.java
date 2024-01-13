package com.gangdestrois.smartimmo.domain.document;

import java.util.Optional;

public class DocumentSystemFactory {
    public Optional<Document> createFile(String name, Folder parent){
        var file = new File(name, parent);
        return Optional.of(file);
    }
    public Optional<Document> createFolder(String name, Folder parent){
        var folder = new Folder(name, parent);
        return Optional.of(folder);
    }
}
