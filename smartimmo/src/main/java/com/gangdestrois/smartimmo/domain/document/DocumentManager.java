package com.gangdestrois.smartimmo.domain.document;

import com.gangdestrois.smartimmo.domain.document.enums.DocumentHolderType;
import com.gangdestrois.smartimmo.domain.document.model.DocumentType;
import com.gangdestrois.smartimmo.domain.document.model.File;
import com.gangdestrois.smartimmo.domain.document.model.Folder;
import com.gangdestrois.smartimmo.domain.document.port.DocumentApi;
import com.gangdestrois.smartimmo.domain.document.port.DocumentService;
import com.gangdestrois.smartimmo.domain.document.port.DocumentSpi;
import com.gangdestrois.smartimmo.domain.document.port.DocumentTypeSpi;
import com.gangdestrois.smartimmo.domain.property.model.Property;
import com.gangdestrois.smartimmo.domain.prospect.model.Prospect;
import com.gangdestrois.smartimmo.infrastructure.rest.error.BadRequestException;
import com.gangdestrois.smartimmo.infrastructure.rest.error.InternalServerErrorException;
import com.google.common.base.Strings;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.gangdestrois.smartimmo.infrastructure.rest.error.ExceptionEnum.*;
import static java.time.LocalDate.now;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class DocumentManager implements DocumentApi {
    private final DocumentSpi documentSpi;
    private final DocumentService documentService;
    private final DocumentTypeSpi documentTypeSpi;

    public DocumentManager(DocumentService documentService, DocumentSpi documentSpi, DocumentTypeSpi documentTypeSpi) {
        this.documentService = documentService;
        this.documentSpi = documentSpi;
        this.documentTypeSpi = documentTypeSpi;
    }

    @Override
    public File uploadFile(byte[] file, String fileName, String fileType, String documentTypeCode, Long ownerId) {
        verifyUploadFile(fileName);
        var documentType = documentTypeSpi.findDocumentTypeFromCode(documentTypeCode).orElseThrow(
                () -> new BadRequestException(DOCUMENT_ERROR, String.format("Document of type %s does not exists.", documentTypeCode)));
        var parentFolder = getParentFolder(documentType.description());
        var fileToUpload = convertBytesToFile(file, fileName);
        return saveFile(fileName, fileType, parentFolder, fileToUpload, documentType, ownerId);
    }

    public Folder getParentFolder(String description) {
        if (Strings.isNullOrEmpty(description))
            throw new BadRequestException(DOCUMENT_ERROR, "Description of folder is empty.");
        var parentFolder = documentSpi.getFolderByName(description);
        if (parentFolder.size() > 1)
            throw new BadRequestException(DOCUMENT_ERROR, "More than one parent folder.");
        return parentFolder.isEmpty() ? createFolder(description, null) : parentFolder.getFirst();
    }

    private File saveFile(String fileName, String fileType, Folder parentFolder, java.io.File fileToUpload,
                          DocumentType documentType, Long ownerId) {
        var fileId = documentService.uploadFileIntoFolder(fileToUpload, fileName, fileType, parentFolder.getDocumentId());
        var fileUploaded = documentService.generatePublicUrl(fileId);
        var holder = documentType.holderType().getHolder(ownerId);
        switch (documentType.holderType()) {
            case PROSPECT -> fileUploaded.setProspect((Prospect) holder);
            case PROPERTY -> fileUploaded.setProperty((Property) holder);
        }
        fileUploaded.setDocumentType(documentType);
        parentFolder.addChild(fileUploaded);
        documentSpi.saveFile(now(), fileUploaded, parentFolder);
        return fileUploaded;
    }

    @Override
    public Folder createFolder(String folderName, Folder parent) {
        verifyFolderName(folderName);
        var folder = documentService.createFolder(folderName);
        documentSpi.saveFolder(folder, parent);
        return folder;
    }

    @Override
    public Map<String, List<File>> getFile(DocumentHolderType documentHolderType, Long documentHolderId) {
        return documentHolderType.getDocuments(documentHolderId).stream()
                .collect(Collectors.groupingBy(file -> file.getDocumentType().name()));
    }

    public java.io.File convertBytesToFile(byte[] fileToConvert, String fileName) {
        java.io.File file = new java.io.File(fileName);
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(fileToConvert);
            return file;
        } catch (IOException e) {
            throw new InternalServerErrorException(CONVERT_DOCUMENT_ERROR, e.getMessage());
        }
    }

    private void verifyUploadFile(String fileName) {
        if (Strings.isNullOrEmpty(fileName)) {
            throw new BadRequestException(DOCUMENT_NAME_NOT_SPECIFIED, "fileName should not be blank.");
        }
    }

    private void verifyFolderName(String folderName) {
        if (isNull(folderName))
            throw new BadRequestException(DOCUMENT_NAME_NOT_SPECIFIED, "folderName should not be blank.");
        var sameNameDocuments = documentSpi.getFolderByName(folderName);
        if (nonNull(sameNameDocuments) && !sameNameDocuments.isEmpty())
            throw new BadRequestException(DOCUMENT_WITH_SAME_NAME_ALREADY_EXISTS,
                    "Unable to create folder because a folder with same name already exists.");
    }
}
