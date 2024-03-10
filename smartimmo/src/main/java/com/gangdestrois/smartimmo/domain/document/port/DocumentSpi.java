package com.gangdestrois.smartimmo.domain.document.port;

import com.gangdestrois.smartimmo.domain.document.model.File;
import com.gangdestrois.smartimmo.domain.document.model.Folder;

import java.util.List;
import java.util.Optional;

public interface DocumentSpi {
    File saveFile(File file, Folder folder);

    Folder saveFolder(Folder folder, Folder parent);

    Folder getFolderByDocumentId(String documentId);

    List<Folder> getFolderByName(String name);

    Optional<Folder> getFolderById(Long id);
}
