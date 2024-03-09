package com.gangdestrois.smartimmo.domain.document;

import com.gangdestrois.smartimmo.domain.document.port.DocumentApi;
import com.gangdestrois.smartimmo.domain.document.port.DocumentService;
import com.gangdestrois.smartimmo.domain.document.port.DocumentSpi;
import com.gangdestrois.smartimmo.domain.prospect.model.Prospect;
import com.gangdestrois.smartimmo.domain.prospect.port.ProspectSpi;
import com.gangdestrois.smartimmo.infrastructure.rest.error.BadRequestException;
import com.gangdestrois.smartimmo.infrastructure.rest.error.ExceptionEnum;
import com.gangdestrois.smartimmo.infrastructure.rest.error.InternalServerErrorException;
import com.gangdestrois.smartimmo.infrastructure.rest.error.NotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.gangdestrois.smartimmo.infrastructure.rest.error.ExceptionEnum.CONVERT_DOCUMENT_ERROR;
import static com.gangdestrois.smartimmo.infrastructure.rest.error.ExceptionEnum.DOCUMENT_WITH_SAME_NAME_ALREADY_EXISTS;
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
    public File uploadFile(byte[] file, String fileName, String fileType, DocumentType documentType, Long ownerId) {
        var owner = prospectSpi.findById(ownerId).orElseThrow(() ->
                new NotFoundException(ExceptionEnum.PROSPECT_NOT_FOUND,
                        String.format("Prospect with id %d doesn't exists.", ownerId)));
        Folder parentFolder = getParentFolder(documentType);
        var fileToUpload = convertBytesToFile(file, fileName);
        return saveFile(fileName, fileType, owner, parentFolder, fileToUpload, documentType);
    }

    public Folder getParentFolder(DocumentType documentType) {
        var parentFolders = documentSpi.getFolderByName(documentType.description());
        Folder parentFolder;
        if (parentFolders.size() > 1)
            throw new BadRequestException(ExceptionEnum.DOCUMENT_ERROR, "More than one parent folder.");
        if (parentFolders.size() == 0 && nonNull(documentType.description())) {
            parentFolder = createFolder(documentType.description(), null);
        } else parentFolder = parentFolders.getFirst();
        return parentFolder;
    }

    private File saveFile(String fileName, String fileType, Prospect owner, Folder parentFolder, java.io.File fileToUpload,
                          DocumentType documentType) {
        var fileId = documentService.uploadFileIntoFolder(fileToUpload, fileName, fileType, parentFolder.getDocumentId());
        var fileUploaded = documentService.generatePublicUrl(fileId);
        fileUploaded.setOwner(owner);
        fileUploaded.setDocumentType(documentType);
        parentFolder.addChild(fileUploaded);
        documentSpi.saveFile(LocalDate.now(), fileUploaded, parentFolder);
        return fileUploaded;
    }

    @Override
    public Folder createFolder(String folderName, Folder parent) {
        if (isNull(folderName)) throw new BadRequestException(ExceptionEnum.DOCUMENT_NAME_NOT_SPECIFIED,
                "Unable to create folder because no name is specified.");
        var sameNameDocuments = documentSpi.getFolderByName(folderName);
        if (nonNull(sameNameDocuments) && sameNameDocuments.size() > 0)
            throw new BadRequestException(DOCUMENT_WITH_SAME_NAME_ALREADY_EXISTS,
                    "Unable to create folder because a folder with same name already exists.");
        var folder = documentService.createFolder(folderName);
        documentSpi.saveFolder(folder, parent);
        return folder;
    }

    @Override
    public Map<DocumentType, List<File>> getFile(Long ownerId) {
        var owner = prospectSpi.findById(ownerId).orElseThrow(() ->
                new NotFoundException(ExceptionEnum.PROSPECT_NOT_FOUND,
                        String.format("Prospect with id %d doesn't exists.", ownerId)));
        return documentSpi.getFileByOwner(owner).stream()
                .collect(Collectors.groupingBy(File::getDocumentType));

    }

    public java.io.File convertBytesToFile(byte[] fileToConvert, String fileName) {
        java.io.File file = new java.io.File(fileName);
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(fileToConvert);
        } catch (IOException e) {
            throw new InternalServerErrorException(CONVERT_DOCUMENT_ERROR, e.getMessage());
        }
        return file;
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
