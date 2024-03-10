package com.gangdestrois.smartimmo.domain.document.port;

import com.gangdestrois.smartimmo.domain.document.enums.DocumentType;
import com.gangdestrois.smartimmo.domain.document.model.File;
import com.gangdestrois.smartimmo.domain.document.model.Folder;

public interface DocumentApi {
    File uploadFile(byte[] file, String fileName, DocumentType documentType, Long ownerId);

    Folder createFolder(String folderName, Folder parent);
}
