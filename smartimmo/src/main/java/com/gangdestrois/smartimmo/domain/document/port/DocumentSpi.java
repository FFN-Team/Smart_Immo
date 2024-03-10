package com.gangdestrois.smartimmo.domain.document.port;

import com.gangdestrois.smartimmo.domain.document.model.File;
import com.gangdestrois.smartimmo.domain.document.model.Folder;
import com.gangdestrois.smartimmo.domain.property.model.Property;
import com.gangdestrois.smartimmo.domain.prospect.model.Prospect;
import com.gangdestrois.smartimmo.domain.utils.Model;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DocumentSpi {
    File saveFile(LocalDate created, File file, Folder folder);

    List<File> getFileByOwner(Property owner);
    List<File> getFileByOwner(Prospect prospect);

    Folder saveFolder(Folder folder, Folder parent);

    Folder getFolderByDocumentId(String documentId);

    List<Folder> getFolderByName(String name);

    Optional<Folder> getFolderById(Long id);
}
