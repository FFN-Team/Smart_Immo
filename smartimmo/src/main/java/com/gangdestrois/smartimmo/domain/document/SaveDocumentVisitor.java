package com.gangdestrois.smartimmo.domain.document;

import com.gangdestrois.smartimmo.domain.document.port.DocumentService;

public class SaveDocumentVisitor implements DocumentVisitor {
    DocumentService documentService;

    @Override
    public void visit(File file) {
    }

    @Override
    public void visit(Folder folder) {
        System.out.println(folder.getName());
    }
}
