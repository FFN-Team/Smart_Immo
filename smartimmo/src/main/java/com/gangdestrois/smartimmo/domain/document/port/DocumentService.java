package com.gangdestrois.smartimmo.domain.document.port;

import com.gangdestrois.smartimmo.domain.document.File;
import com.gangdestrois.smartimmo.domain.document.Folder;

public interface DocumentService {
    File generatePublicUrl(String fileId);
    Folder createFolder(String name);
    String uploadFileIntoFolder(String stringFilePath, String fileName, String fileType, String folderId);
}