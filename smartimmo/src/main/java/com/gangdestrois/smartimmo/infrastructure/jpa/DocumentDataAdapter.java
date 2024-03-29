package com.gangdestrois.smartimmo.infrastructure.jpa;

import com.gangdestrois.smartimmo.domain.document.model.File;
import com.gangdestrois.smartimmo.domain.document.model.Folder;
import com.gangdestrois.smartimmo.domain.document.port.DocumentSpi;
import com.gangdestrois.smartimmo.infrastructure.jpa.entity.FileEntity;
import com.gangdestrois.smartimmo.infrastructure.jpa.entity.FolderEntity;
import com.gangdestrois.smartimmo.infrastructure.jpa.entity.ProspectEntity;
import com.gangdestrois.smartimmo.infrastructure.jpa.repository.FileRepository;
import com.gangdestrois.smartimmo.infrastructure.jpa.repository.FolderRepository;
import com.gangdestrois.smartimmo.infrastructure.jpa.repository.ProspectRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.nonNull;

@Component
public class DocumentDataAdapter implements DocumentSpi {
    private final FileRepository fileRepository;
    private final FolderRepository folderRepository;
    private final ProspectRepository prospectRepository;

    @Autowired
    public DocumentDataAdapter(FileRepository fileRepository, FolderRepository folderRepository, ProspectRepository prospectRepository) {
        this.fileRepository = fileRepository;
        this.folderRepository = folderRepository;
        this.prospectRepository = prospectRepository;
    }

    @Override
    @Transactional
    public File saveFile(File file, Folder folder) {
        Optional<FolderEntity> parentEntity = folderRepository.findById(folder.id());
        Optional<ProspectEntity> prospectEntity = Optional.empty();
        if (file.getOwner().isPresent()) prospectEntity = prospectRepository.findById(file.getOwner().get().id());
        var fileEntity = FileEntity.fromModel(file,
                prospectEntity.orElse(null),
                parentEntity.orElseGet(() -> folderRepository.findAllByName("My Drive").getFirst()));
        var fileSaved = fileRepository.save(fileEntity);
        file.setId(fileSaved.getId());
        return file;
    }

    @Override
    @Transactional
    public Folder saveFolder(Folder folder, Folder parent) {
        Optional<FolderEntity> parentEntity = nonNull(parent) ? folderRepository.findById(parent.id()) :
                Optional.ofNullable(folderRepository.findAllByName("My Drive").getFirst());
        var folderEntitySaved = folderRepository.save(FolderEntity.fromModel(folder, parentEntity.orElse(null)));
        folder.setId(folderEntitySaved.getId());
        return folder;
    }

    @Override
    public Folder getFolderByDocumentId(String documentId) {
        var folderEntity = folderRepository.findAllByDocumentId(documentId).getFirst();
        return FolderEntity.toModel(folderEntity);
    }

    @Override
    public List<Folder> getFolderByName(String folderName) {
        return folderRepository.findAllByName(folderName).stream().map(FolderEntity::toModel).toList();
    }

    @Override
    public Optional<Folder> getFolderById(Long id) {
        var folderEntities = folderRepository.findById(id);
        return folderEntities.map(FolderEntity::toModel);
    }
}
