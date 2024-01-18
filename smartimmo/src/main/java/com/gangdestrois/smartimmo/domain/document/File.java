package com.gangdestrois.smartimmo.domain.document;

import com.gangdestrois.smartimmo.domain.prospect.model.Prospect;

public class File extends DocumentImplementation {
    private Prospect owner;

    public File(String name, Folder parent) {
        super(name, parent);
    }

    public Boolean isComposite() {
        return false;
    }

    @Override
    public void accept(DocumentVisitor documentVisitor) {
        documentVisitor.visit(this);
    }

}
