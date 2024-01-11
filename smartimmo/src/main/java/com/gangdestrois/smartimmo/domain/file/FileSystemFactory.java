package com.gangdestrois.smartimmo.domain.file;


import com.gangdestrois.smartimmo.domain.prospect.model.Prospect;

public class FileSystemFactory {
    private static final FileSystemFactory INSTANCE = new FileSystemFactory();

    private FileSystemFactory() {
    }

    public static FileSystemFactory getInstance() {
        return INSTANCE;
    }

    public Component createComponent(ComponentType type, String name, Prospect owner) {
        return switch (type) {
            case FILE -> new File(name, owner);
            case FOLDER -> new Folder(name, owner);
        };
    }
}
