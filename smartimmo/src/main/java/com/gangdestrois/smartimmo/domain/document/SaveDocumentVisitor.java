package com.gangdestrois.smartimmo.domain.document;

public class SaveDocumentVisitor implements DocumentVisitor {
    @Override
    public void visit(File file) {
        System.out.println(file.getName());
    }

    @Override
    public void visit(Folder folder) {
        System.out.println(folder.getName());
    }
}
