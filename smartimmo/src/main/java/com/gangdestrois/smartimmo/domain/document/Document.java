package com.gangdestrois.smartimmo.domain.document;

import java.util.Optional;

public interface Document {
    String getName();
    Integer getSize();
    Optional<Folder> getParent();
    Boolean isComposite();
    void accept(DocumentVisitor documentVisitor);
}
