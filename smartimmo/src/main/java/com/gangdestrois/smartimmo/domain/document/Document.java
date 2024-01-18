package com.gangdestrois.smartimmo.domain.document;

import java.util.Optional;

public interface Document {
    String getName();
    Optional<Folder> getParent();
    Boolean isComposite();
    void accept(DocumentVisitor documentVisitor);
}
