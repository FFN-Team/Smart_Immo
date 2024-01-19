package com.gangdestrois.smartimmo.domain.document;

public class DocumentSystemFactory {
/*    public static Optional<Document> createFile(String name, Folder parent){
        var file = new File(name, parent);
        return Optional.of(file);
    }
    public Optional<Document> createFolder(String name, Folder parent){
        if(isNull(parent)) parent = getRoot();
        var folder = new Folder(name, parent);
        return Optional.of(folder);
    }*/

    private Folder getRoot() {
        return null;
    }
}
