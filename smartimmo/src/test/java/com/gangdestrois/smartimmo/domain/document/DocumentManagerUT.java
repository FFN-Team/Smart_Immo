package com.gangdestrois.smartimmo.domain.document;

import com.gangdestrois.smartimmo.DataForUT;
import com.gangdestrois.smartimmo.domain.document.port.DocumentService;
import com.gangdestrois.smartimmo.domain.document.port.DocumentSpi;
import com.gangdestrois.smartimmo.domain.prospect.port.ProspectSpi;
import com.gangdestrois.smartimmo.infrastructure.jpa.DocumentDataAdapter;
import com.gangdestrois.smartimmo.infrastructure.jpa.ProspectDataAdapter;
import com.gangdestrois.smartimmo.infrastructure.rest.error.BadRequestException;
import com.gangdestrois.smartimmo.infrastructure.rest.error.ExceptionEnum;
import com.gangdestrois.smartimmo.infrastructure.rest.error.NotFoundException;
import com.gangdestrois.smartimmo.infrastructure.service.GoogleDriveApi;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class DocumentManagerUT {

    private DocumentSpi documentSpi;
    private ProspectSpi prospectSpi;
    private DocumentService documentService;
    private DocumentManager documentManager;
    private final DataForUT dataForUT = new DataForUT();

    @Before
    public void setUp() {
        documentSpi = mock(DocumentDataAdapter.class);
        prospectSpi = mock(ProspectDataAdapter.class);
        documentService = mock(GoogleDriveApi.class);
        documentManager = new DocumentManager(documentService, documentSpi, prospectSpi);
    }

    @Test
    public void uploadFile_should_throw_not_found_exception_when_prospect_not_found() {
        // Getters
        Long ownerId = 1L;
        byte[] file = dataForUT.file;
        var fileName = "test";
        var documentType = DocumentType.VISITE_PHOTO;
        when(prospectSpi.findById(ownerId)).thenReturn(Optional.empty());

        // When and then
        var result = assertThrows(NotFoundException.class, () -> documentManager.uploadFile(file, fileName, documentType,
                ownerId));
        assertEquals(ExceptionEnum.PROSPECT_NOT_FOUND, result.getError());
    }

    @Test
    public void getParentFolder_should_throw_internal_server_error_exception_when_document_to_upload_have_more_than_one_parent() {
        // Getters
        var documentType = DocumentType.VISITE_PHOTO;
        List<Folder> list = new ArrayList<>();
        var folder = dataForUT.folder1;
        list.add(folder);
        list.add(folder);
        when(documentSpi.getFolderByName(documentType.getName())).thenReturn(list);

        // When and then
        var result = assertThrows(BadRequestException.class, () -> documentManager.getParentFolder(documentType));
        assertEquals(ExceptionEnum.DOCUMENT_ERROR, result.getError());
    }

    @Test
    public void getParentFolder_should_return_a_new_folder_when_document_type_is_conform() {
        // Getters
        var documentType = DocumentType.VISITE_PHOTO;
        var folderName = documentType.getName();
        var parentFolder = dataForUT.folder1;
        List<Folder> parentFolders = new ArrayList<>();
        parentFolders.add(parentFolder);
        when(documentSpi.getFolderByName(folderName)).thenReturn(parentFolders);

        // When
        var result = documentManager.getParentFolder(documentType);

        // Then
        assertEquals(parentFolder, result);
    }

    @Test
    public void createFolder_should_throw_bad_request_exception_when_the_folder_name_is_null() {
        // Getters
        var folder = dataForUT.folder1;

        // When and then
        var result = assertThrows(BadRequestException.class, () -> documentManager.createFolder(null, folder));
        assertEquals(ExceptionEnum.DOCUMENT_NAME_NOT_SPECIFIED, result.getError());
    }

    @Test
    public void createFolder_should_throw_bad_request_exception_when_a_folder_with_same_name_already_exists() {
        // Getters
        var parentFolder = dataForUT.folder1;
        var folderName = parentFolder.getName();
        List<Folder> folders = new ArrayList<>();
        folders.add(parentFolder);
        when(documentSpi.getFolderByName(folderName)).thenReturn(folders);

        // When and then
        var result = assertThrows(BadRequestException.class, () -> documentManager.createFolder(folderName, parentFolder));
        assertEquals(ExceptionEnum.DOCUMENT_WITH_SAME_NAME_ALREADY_EXISTS, result.getError());
    }

    @Test
    public void createFolder_should_create_folder_when_folder_to_save_is_conform() {
        // Getters
        var parentFolder = dataForUT.folder1;
        var folderName = parentFolder.getName();
        List<Folder> folders = new ArrayList<>();
        when(documentSpi.getFolderByName(folderName)).thenReturn(folders);

        // When
        documentManager.createFolder(folderName, parentFolder);

        // Then
        verify(documentService).createFolder(folderName);
    }

    @Test
    public void createFolder_should_save_folder_when_folder_to_save_is_conform() {
        // Getters
        var parentFolder = dataForUT.folder1;
        var folder = dataForUT.folder1;
        var folderName = parentFolder.getName();
        List<Folder> folders = new ArrayList<>();
        when(documentSpi.getFolderByName(folderName)).thenReturn(folders);
        when(documentService.createFolder(folderName)).thenReturn(folder);

        // When
        var result = documentManager.createFolder(folderName, parentFolder);

        // Then
        verify(documentSpi).saveFolder(folder, parentFolder);
        assertEquals(folder, result);
    }
}
