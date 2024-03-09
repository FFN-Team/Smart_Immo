package com.gangdestrois.smartimmo.infrastructure.rest.controller;

import com.gangdestrois.smartimmo.domain.document.DocumentType;
import com.gangdestrois.smartimmo.domain.document.Folder;
import com.gangdestrois.smartimmo.domain.document.OwnerType;
import com.gangdestrois.smartimmo.domain.document.port.DocumentApi;
import com.gangdestrois.smartimmo.infrastructure.rest.dto.DocumentResponse;
import com.gangdestrois.smartimmo.infrastructure.rest.dto.FileByDocumentTypeResponse;
import com.gangdestrois.smartimmo.infrastructure.rest.dto.FileByOwnerResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
@RequestMapping("/api/v1/documents")
public class DocumentController {
    private final DocumentApi documentApi;

    public DocumentController(DocumentApi documentApi) {
        this.documentApi = documentApi;
    }

    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "Upload file into Google Drive.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Upload file successful into Google Drive."),
                    @ApiResponse(responseCode = "404", description = "The given owner doesn't exists."),
                    @ApiResponse(responseCode = "400", description = "More than one parent folder."),
                    @ApiResponse(responseCode = "400", description = "Unable to create folder because no name is specified."),
                    @ApiResponse(responseCode = "400", description = "Unable to create folder because a folder with same name already exists."),
                    @ApiResponse(responseCode = "500", description = "Error during converting file."),
            })
    @PostMapping(value = "/upload", consumes = MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<DocumentResponse> saveDocument(
            @RequestPart("fileContent") byte[] fileContent,
            @RequestParam("fileName") String fileName,
            @RequestParam("documentType") DocumentType documentType,
            @RequestParam("ownerId") Long ownerId,
            @RequestParam("fileType") String fileType) {
        var file = documentApi.uploadFile(fileContent, fileName, fileType, documentType, ownerId);
        var documentResponse = new DocumentResponse(file.getName(), file.getDocumentId(),
                file.getWebContentLink(), file.getWebLink());
        return ResponseEntity.ok(documentResponse);
    }

    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "Create folder into Google Drive.",
            responses = {
                    @ApiResponse(responseCode = "400", description = "Unable to create folder because no name is specified."),
                    @ApiResponse(responseCode = "400", description = "Unable to create folder because a folder with same name already exists."),
            })
    @PostMapping("/create-folder")
    public ResponseEntity<Folder> createFolder(@RequestBody String name) {
        return ResponseEntity.ok(documentApi.createFolder(name, null));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("{ownerType}/{ownerId}")
    public ResponseEntity<List<FileByDocumentTypeResponse>> getFiles(@PathVariable("ownerType") OwnerType ownerType,
                                                                     @PathVariable("ownerId") Long ownerId) {
        return ResponseEntity.ok(FileByOwnerResponse.fromModel(documentApi.getFile(ownerType, ownerId)));
    }

}
