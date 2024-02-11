package com.gangdestrois.smartimmo.domain.document;

import com.gangdestrois.smartimmo.domain.document.port.DocumentApi;
import com.gangdestrois.smartimmo.domain.document.port.DocumentService;
import com.gangdestrois.smartimmo.domain.document.port.DocumentSpi;
import com.gangdestrois.smartimmo.domain.prospect.port.ProspectSpi;
import com.gangdestrois.smartimmo.infrastructure.rest.error.InternalServerErrorException;
import com.gangdestrois.smartimmo.infrastructure.rest.error.explicitException.ProspectNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

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
    public File uploadFile(byte[] file, String fileName, DocumentType documentType, Long ownerId) {
        var owner = prospectSpi.findById(ownerId).orElseThrow(() ->
                new ProspectNotFoundException(String.format("Prospect with id %d doesn't exists.", ownerId)));
        var parentFolders = documentSpi.getFolderByName(documentType.getName());
        Folder parentFolder;
        if (parentFolders.size() > 1)
            throw new InternalServerErrorException("to do");
        if (parentFolders.size() == 0 && nonNull(documentType.getName())) {
            parentFolder = createFolder(documentType.getName(), null);
        } else parentFolder = parentFolders.getFirst();
        var fileToUpload = convertBytesToFile(file, fileName);
        var fileId = documentService.uploadFileIntoFolder(fileToUpload, fileName, documentType.getFileType(),
                parentFolder.getDocumentId());
        var fileUploaded = documentService.generatePublicUrl(fileId);
        fileUploaded.setOwner(owner);
        parentFolder.addChild(fileUploaded);
        documentSpi.saveFile(fileUploaded, parentFolder);
        return fileUploaded;
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

    public java.io.File convertBytesToFile(byte[] fileToConvert, String fileName) {
        java.io.File file = new java.io.File(fileName);

        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(fileToConvert);
        } catch (IOException e) {
            throw new RuntimeException("Error during converting byte[] into file.");
        }

        return file;
    }

    public java.io.File convertMultiPartToFile(MultipartFile multipartFile) {
        java.io.File convertedFile = new java.io.File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        try {
            multipartFile.transferTo(convertedFile);
        } catch (IOException e) {
            throw new RuntimeException("Error during converting MultiPartFile into file.");
        }
        return convertedFile;
    }
}
