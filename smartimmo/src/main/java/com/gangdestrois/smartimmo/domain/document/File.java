package com.gangdestrois.smartimmo.domain.document;

import com.gangdestrois.smartimmo.domain.prospect.model.Prospect;

import java.util.Optional;

public class File extends DocumentImplementation {
    private Prospect owner;

    //ce serait bien de passer par une factory car il faut gérer lorsqu'il n'y a pas de parent
    public File(Long id, String fileId, String name, String webContentLink, String webLink) {
        super(id, fileId, name, webContentLink, webLink);
    }

    public File(String fileId, String name, String webContentLink, String webLink) {
        super(fileId, name, webContentLink, webLink);
    }

    public Boolean isComposite() {
        return false;
    }

    @Override
    public void accept(DocumentVisitor documentVisitor) {
        documentVisitor.visit(this);
    }

    public Optional<Prospect> getOwner() {
        return Optional.ofNullable(owner);
    }

    public void setOwner(Prospect owner) {
        this.owner = owner;
    }
}
