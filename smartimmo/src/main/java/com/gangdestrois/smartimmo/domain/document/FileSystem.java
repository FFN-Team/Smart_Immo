package com.gangdestrois.smartimmo.domain.document;


import com.gangdestrois.smartimmo.domain.prospect.model.Prospect;

public class FileSystem implements DocumentSystemFactory {
    private static final FileSystem INSTANCE = new FileSystem();

    private FileSystem() {
    }

    public static FileSystem getInstance() {
        return INSTANCE;
    }

    public Component createComponent(String name, Prospect owner) {
        return new File(name, owner);
    }
}
