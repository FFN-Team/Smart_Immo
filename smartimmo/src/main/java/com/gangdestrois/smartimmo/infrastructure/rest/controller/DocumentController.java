package com.gangdestrois.smartimmo.infrastructure.rest.controller;

import com.gangdestrois.smartimmo.domain.document.DocumentType;
import com.gangdestrois.smartimmo.domain.document.Folder;
import com.gangdestrois.smartimmo.domain.document.port.DocumentApi;
import com.gangdestrois.smartimmo.infrastructure.rest.dto.DocumentResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
@RequestMapping("/api/v1/documents")
public class DocumentController {
    private final DocumentApi documentApi;

    public DocumentController(DocumentApi documentApi) {
        this.documentApi = documentApi;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/upload", consumes = MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<DocumentResponse> saveDocument(
            @RequestPart("fileContent") byte[] fileContent,
            @RequestParam("fileName") String fileName,
            @RequestParam("documentType") DocumentType documentType,
            @RequestParam("ownerId") Long ownerId,
            @RequestParam("fileType") String fileType) {
        var file = documentApi.uploadFile(fileContent, fileName, fileType,  documentType, ownerId);
        var documentResponse = new DocumentResponse(file.getName(), file.getDocumentId(),
                file.getWebContentLink(), file.getWebLink());
        return ResponseEntity.ok(documentResponse);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/create-folder")
    public ResponseEntity<Folder> createFolder(@RequestBody String name) {
        return ResponseEntity.ok(documentApi.createFolder(name, null));
    }
}
