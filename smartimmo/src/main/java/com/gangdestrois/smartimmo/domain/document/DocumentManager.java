package com.gangdestrois.smartimmo.domain.document;

import com.gangdestrois.smartimmo.domain.document.port.DocumentApi;
import com.gangdestrois.smartimmo.domain.document.port.DocumentService;
import com.gangdestrois.smartimmo.domain.document.port.DocumentSpi;
import com.gangdestrois.smartimmo.domain.prospect.port.ProspectSpi;
import com.gangdestrois.smartimmo.infrastructure.rest.error.explicitException.ProspectNotFoundException;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class DocumentManager implements DocumentApi {
    private final DocumentSpi documentSpi;
    private final ProspectSpi prospectSpi;
    private final DocumentService documentService;

    public DocumentManager(DocumentService documentService, DocumentSpi documentSpi, ProspectSpi prospectSpi) {
        this.documentService = documentService;
        this.documentSpi = documentSpi;
        this.prospectSpi = prospectSpi;

    }

    @Override
    public File uploadFile(String stringFilePath, String fileName, DocumentType documentType, Long ownerId) {
        var owner = prospectSpi.findById(ownerId).orElseThrow(() ->
                new ProspectNotFoundException(String.format("Prospect with id %d doesn't exists.", ownerId)));
        var parentFolders = documentSpi.getFolderByName(documentType.getName());
        Folder parentFolder;
        if (parentFolders.size() > 1)
            throw new RuntimeException("to do");
        if (parentFolders.size() == 0 && nonNull(documentType.getName())) {
            parentFolder = createFolder(documentType.getName(), null);
        } else parentFolder = parentFolders.getFirst();
        var fileId = documentService.uploadFileIntoFolder(stringFilePath, fileName, documentType.getFileType(),
                parentFolder.getDocumentId());
        var file = documentService.generatePublicUrl(fileId);
        file.setOwner(owner);
        parentFolder.addChild(file);
        documentSpi.saveFile(file, parentFolder);
        return file;
    }

    @Override
    public Folder createFolder(String folderName, Folder parent) {
        if (isNull(folderName)) throw new RuntimeException();
        var sameNameDocuments = documentSpi.getFolderByName(folderName);
        if (nonNull(sameNameDocuments) && sameNameDocuments.size() > 0)
            throw new RuntimeException("to do");
        var folder = documentService.createFolder(folderName);
        documentSpi.saveFolder(folder, parent);
        return folder;
    }
}
