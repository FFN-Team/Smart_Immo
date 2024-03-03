package com.gangdestrois.smartimmo.domain.document.model;

import com.gangdestrois.smartimmo.domain.prospect.model.Prospect;

import java.util.Optional;

public class File extends DocumentImplementation {
    private Prospect owner;

    public File(Long id, String fileId, String name, String webContentLink, String webLink) {
        super(id, fileId, name, webContentLink, webLink);
    }

    public File(String fileId, String name, String webContentLink, String webLink) {
        super(fileId, name, webContentLink, webLink);
    }

    public Optional<Prospect> getOwner() {
        return Optional.ofNullable(owner);
    }

    public void setOwner(Prospect owner) {
        this.owner = owner;
    }
}
