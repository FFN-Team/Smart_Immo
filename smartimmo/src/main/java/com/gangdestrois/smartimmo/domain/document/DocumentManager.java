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
import com.gangdestrois.smartimmo.domain.prospect.port.ProspectSpi;
import com.gangdestrois.smartimmo.infrastructure.rest.error.BadRequestException;
import com.gangdestrois.smartimmo.infrastructure.rest.error.InternalServerErrorException;
import com.google.common.base.Strings;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.gangdestrois.smartimmo.infrastructure.rest.error.ExceptionEnum.CONVERT_DOCUMENT_ERROR;
import static com.gangdestrois.smartimmo.infrastructure.rest.error.ExceptionEnum.DOCUMENT_ERROR;
import static com.gangdestrois.smartimmo.infrastructure.rest.error.ExceptionEnum.DOCUMENT_NAME_NOT_SPECIFIED;
import static com.gangdestrois.smartimmo.infrastructure.rest.error.ExceptionEnum.DOCUMENT_WITH_SAME_NAME_ALREADY_EXISTS;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

// TODO : je pense qu'on ne devrait pas avoir d'exception de l'infrastructure dans le domaine,
//  se renseigner sur la façon de renvoyer des exceptions dans le domaine
public class DocumentManager implements DocumentApi {
    private final DocumentSpi documentSpi;
    private final ProspectSpi prospectSpi;
    private final DocumentService documentService;
    private final DocumentTypeSpi documentTypeSpi;

    public DocumentManager(DocumentService documentService, DocumentSpi documentSpi, ProspectSpi prospectSpi,
                           DocumentTypeSpi documentTypeSpi) {
        this.documentService = documentService;
        this.documentSpi = documentSpi;
        this.prospectSpi = prospectSpi;
        this.documentTypeSpi = documentTypeSpi;
    }

    @Override
    public File uploadFile(byte[] file, String fileName, String fileType, String documentTypeCode, Long ownerId) {
        var documentType = documentTypeSpi.findDocumentTypeFromCode(documentTypeCode)
                .orElseThrow(() -> new BadRequestException(DOCUMENT_ERROR,
                        String.format("Document of type %s does not exists.", documentTypeCode)));
        var parentFolder = getParentFolder(documentType.description());
        var fileToUpload = convertBytesToFile(file, fileName);
        return saveFile(fileName, fileType, parentFolder, fileToUpload, documentType, ownerId);
    }

    public Folder getParentFolder(String description) {
        if (Strings.isNullOrEmpty(description))
            throw new BadRequestException(DOCUMENT_ERROR, "Description of folder is empty.");
        var parentFolders = documentSpi.getFolderByName(description);
        if (parentFolders.size() > 1) throw new BadRequestException(DOCUMENT_ERROR, "More than one parent folder.");
        return parentFolders.isEmpty() ? createFolder(description, null) : parentFolders.getFirst();
    }

    private File saveFile(String fileName, String fileType, Folder parentFolder, java.io.File fileToUpload,
                          DocumentType documentType, Long ownerId) {
        var fileId = documentService.uploadFileIntoFolder(fileToUpload, fileName, fileType, parentFolder.getDocumentId());
        var fileUploaded = documentService.generatePublicUrl(fileId);
        switch (documentType.ownerType()) {
            case PROSPECT -> fileUploaded.setProspect((Prospect) documentType.ownerType().getOwner(ownerId));
            case PROPERTY -> fileUploaded.setProperty((Property) documentType.ownerType().getOwner(ownerId));
        }
        fileUploaded.setDocumentType(documentType);
        parentFolder.addChild(fileUploaded);
        documentSpi.saveFile(LocalDate.now(), fileUploaded, parentFolder);
        return fileUploaded;
    }

    @Override
    public Folder createFolder(String folderName, Folder parent) {
        verifyFolderName(folderName);
        var folder = documentService.createFolder(folderName);
        documentSpi.saveFolder(folder, parent);
        return folder;
    }

    private void verifyFolderName(String folderName) {
        if (isNull(folderName))
            throw new BadRequestException(DOCUMENT_NAME_NOT_SPECIFIED, "Document name should be present.");
        var sameNameDocuments = documentSpi.getFolderByName(folderName);
        if (nonNull(sameNameDocuments) && !sameNameDocuments.isEmpty())
            throw new BadRequestException(DOCUMENT_WITH_SAME_NAME_ALREADY_EXISTS,
                    "Unable to create folder because a folder with same name already exists.");
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

    public java.io.File convertMultiPartToFile(MultipartFile multipartFile) {
        java.io.File convertedFile = new java.io.File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        try {
            multipartFile.transferTo(convertedFile);
        } catch (IOException e) {
            throw new InternalServerErrorException(CONVERT_DOCUMENT_ERROR, e.getMessage());
        }
        return convertedFile;
    }
}
