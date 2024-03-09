package com.gangdestrois.smartimmo.domain.document.port;

import com.gangdestrois.smartimmo.domain.document.DocumentType;
import com.gangdestrois.smartimmo.domain.document.File;
import com.gangdestrois.smartimmo.domain.document.Folder;
import com.gangdestrois.smartimmo.domain.document.OwnerType;

import java.util.List;
import java.util.Map;

public interface DocumentApi {
    File uploadFile(byte[] file, String fileName, String fileType, DocumentType documentType, Long ownerId);

    Folder createFolder(String folderName, Folder parent);

    Map<DocumentType, List<File>> getFile(OwnerType ownerType, Long ownerId);
}
