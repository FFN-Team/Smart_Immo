package com.gangdestrois.smartimmo.infrastructure.rest.controller;

import com.gangdestrois.smartimmo.domain.document.enums.DocumentHolderType;
import com.gangdestrois.smartimmo.domain.document.model.Folder;
import com.gangdestrois.smartimmo.domain.document.port.DocumentApi;
import com.gangdestrois.smartimmo.infrastructure.rest.dto.Response.DocumentResponse;
import com.gangdestrois.smartimmo.infrastructure.rest.dto.FilesByHolderResponse;
import com.gangdestrois.smartimmo.infrastructure.rest.error.BadRequestException;
import com.gangdestrois.smartimmo.infrastructure.rest.error.ExceptionEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static java.util.Objects.isNull;
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
            @RequestParam("documentTypeCode") String documentTypeCode,
            @RequestParam("ownerId") Long ownerId,
            @RequestParam("fileType") String fileType) {
        var file = documentApi.uploadFile(fileContent, fileName, fileType, documentTypeCode, ownerId);
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
        if (isNull(name)) throw new BadRequestException(ExceptionEnum.DOCUMENT_NAME_NOT_SPECIFIED,
                "Unable to create folder because no name is specified.");
        return ResponseEntity.ok(documentApi.createFolder(name, null));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{documentHolderType}/{documentHolderId}")
    @Operation(description = """
            Get files of a holder by a holder reference and the document holder type (the document can belongs
            to a prospect, a property, ...)""")
    public ResponseEntity<FilesByHolderResponse> getFiles(
            @PathVariable("documentHolderType") DocumentHolderType documentHolderType,
            @PathVariable("documentHolderId") Long documentHolderId) {
        return ResponseEntity.ok(FilesByHolderResponse.toDto(documentApi.getFile(documentHolderType, documentHolderId)));
    }

}
