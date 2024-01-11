package com.gangdestrois.smartimmo.domain.document;


import com.gangdestrois.smartimmo.domain.prospect.model.Prospect;

public class FolderSystem implements DocumentSystemFactory {
    private static final FolderSystem INSTANCE = new FolderSystem();

    private FolderSystem() {
    }

    public static FolderSystem getInstance() {
        return INSTANCE;
    }

    public Component createComponent(String name, Prospect owner) {
        return new Folder(name, owner);
    }
}
