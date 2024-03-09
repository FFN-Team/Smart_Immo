package com.gangdestrois.smartimmo.domain.document.port;

import com.gangdestrois.smartimmo.domain.document.File;
import com.gangdestrois.smartimmo.domain.document.Folder;
import com.gangdestrois.smartimmo.domain.utils.Model;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DocumentSpi {
    File saveFile(LocalDate created, File file, Folder folder);

    List<File> getFileByOwner(Model owner);

    Folder saveFolder(Folder folder, Folder parent);

    Folder getFolderByDocumentId(String documentId);

    List<Folder> getFolderByName(String name);

    Optional<Folder> getFolderById(Long id);
}
