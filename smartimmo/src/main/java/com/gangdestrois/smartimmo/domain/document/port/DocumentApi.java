package com.gangdestrois.smartimmo.domain.document.port;

import com.gangdestrois.smartimmo.domain.document.enums.DocumentHolderType;
import com.gangdestrois.smartimmo.domain.document.model.File;
import com.gangdestrois.smartimmo.domain.document.model.Folder;

import java.util.List;
import java.util.Map;

public interface DocumentApi {
    File uploadFile(byte[] file, String fileName, String fileType, String documentTypeCode, Long ownerId);

    Folder createFolder(String folderName, Folder parent);

    Map<String, List<File>> getFile(DocumentHolderType documentHolderType, Long documentHolderId);
}
