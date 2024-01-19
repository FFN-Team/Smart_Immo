package com.gangdestrois.smartimmo.domain.document;

public interface Document {
    String getName();

    Boolean isComposite();

    void accept(DocumentVisitor documentVisitor);
}
