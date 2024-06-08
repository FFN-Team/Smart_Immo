package com.gangdestrois.smartimmo.infrastructure.jpa;

import com.gangdestrois.smartimmo.domain.document.model.File;
import com.gangdestrois.smartimmo.domain.document.model.Folder;
import com.gangdestrois.smartimmo.domain.document.port.DocumentSpi;
import com.gangdestrois.smartimmo.domain.property.model.Property;
import com.gangdestrois.smartimmo.domain.prospect.model.Prospect;
import com.gangdestrois.smartimmo.infrastructure.jpa.entity.FileEntity;
import com.gangdestrois.smartimmo.infrastructure.jpa.entity.FolderEntity;
import com.gangdestrois.smartimmo.infrastructure.jpa.entity.PropertyEntity;
import com.gangdestrois.smartimmo.infrastructure.jpa.entity.ProspectEntity;
import com.gangdestrois.smartimmo.infrastructure.jpa.repository.DocumentTypeRepository;
import com.gangdestrois.smartimmo.infrastructure.jpa.repository.FileRepository;
import com.gangdestrois.smartimmo.infrastructure.jpa.repository.FolderRepository;
import com.gangdestrois.smartimmo.infrastructure.jpa.repository.ProspectRepository;
import com.gangdestrois.smartimmo.infrastructure.rest.error.BadRequestException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.gangdestrois.smartimmo.infrastructure.rest.error.ExceptionEnum.DOCUMENT_ERROR;
import static java.util.Objects.nonNull;

@Component
public class DocumentDataAdapter implements DocumentSpi {
    private final FileRepository fileRepository;
    private final FolderRepository folderRepository;
    private final ProspectRepository prospectRepository;
    private final DocumentTypeRepository documentTypeRepository;

    @Autowired
    public DocumentDataAdapter(FileRepository fileRepository, FolderRepository folderRepository,
                               ProspectRepository prospectRepository, DocumentTypeRepository documentTypeRepository) {
        this.fileRepository = fileRepository;
        this.folderRepository = folderRepository;
        this.prospectRepository = prospectRepository;
        this.documentTypeRepository = documentTypeRepository;
    }

    @Override
    @Transactional
    public File saveFile(LocalDate created, File file, Folder folder) {
        Optional<FolderEntity> parentEntity = folderRepository.findById(folder.id());
        Optional<ProspectEntity> prospectEntity = Optional.empty();
        if (file.getProspect().isPresent()) prospectEntity = prospectRepository.findById(file.getProspect().get().id());
        var documentTypeEntity = documentTypeRepository.findByName(file.getDocumentType().name())
                .orElseThrow(() -> new BadRequestException(DOCUMENT_ERROR,
                        String.format("Document of type %s does not exists.", file.getDocumentType().name())));
        var fileEntity = FileEntity.fromModel(file,
                prospectEntity.orElse(null),
                parentEntity.orElseGet(() -> folderRepository.findAllByName("My Drive").getFirst()),
                documentTypeEntity,
                created);
        var fileSaved = fileRepository.save(fileEntity);
        file.setId(fileSaved.getId());
        return file;
    }

    @Override
    public List<File> getFileByDocumentHolder(Property documentHolder) {
        return fileRepository.findAllByDocumentHolder(PropertyEntity.fromModelToEntity(documentHolder))
                .stream().map(FileEntity::toModel)
                .toList();
    }

    @Override
    public List<File> getFileByDocumentHolder(Prospect documentHolder) {
        return fileRepository.findAllByDocumentHolder(ProspectEntity.fromModelToEntity(documentHolder))
                .stream().map(FileEntity::toModel)
                .toList();
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
