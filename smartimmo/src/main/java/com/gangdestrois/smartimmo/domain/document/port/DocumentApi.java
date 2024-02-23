package com.gangdestrois.smartimmo.domain.document.port;

import com.gangdestrois.smartimmo.domain.document.DocumentType;
import com.gangdestrois.smartimmo.domain.document.File;
import com.gangdestrois.smartimmo.domain.document.Folder;

public interface DocumentApi {
    File uploadFile(byte[] file, String fileName, String fileType, DocumentType documentType, Long ownerId);

    Folder createFolder(String folderName, Folder parent);
}
