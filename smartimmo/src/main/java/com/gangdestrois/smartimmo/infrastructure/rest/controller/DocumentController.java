package com.gangdestrois.smartimmo.infrastructure.rest.controller;

import com.gangdestrois.smartimmo.domain.document.Folder;
import com.gangdestrois.smartimmo.domain.document.port.DocumentApi;
import com.gangdestrois.smartimmo.infrastructure.rest.dto.DocumentRequest;
import com.gangdestrois.smartimmo.infrastructure.rest.dto.DocumentResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/documents")
public class DocumentController {
    private final DocumentApi documentApi;

    public DocumentController(DocumentApi documentApi) {
        this.documentApi = documentApi;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/upload")
    public ResponseEntity<DocumentResponse> saveDocument(@RequestBody DocumentRequest documentRequest) {
        var file = documentApi.uploadFile(documentRequest.filePath(), documentRequest.fileName(),
                documentRequest.documentType(), documentRequest.ownerId());
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
