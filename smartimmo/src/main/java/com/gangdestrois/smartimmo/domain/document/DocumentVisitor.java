package com.gangdestrois.smartimmo.domain.document;

public interface DocumentVisitor {
    void visit(File file);
    void visit(Folder folder);
}
