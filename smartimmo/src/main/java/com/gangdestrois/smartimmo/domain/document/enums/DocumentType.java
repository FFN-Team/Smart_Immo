package com.gangdestrois.smartimmo.domain.document.enums;

public enum DocumentType {
    VISITE_PHOTO("image/jpg", "Visite photo");
    private final String fileType;
    private final String name;

    DocumentType(String fileType, String name) {
        this.fileType = fileType;
        this.name = name;
    }

    public String getFileType() {
        return fileType;
    }

    public String getName() {
        return this.name;
    }
}
