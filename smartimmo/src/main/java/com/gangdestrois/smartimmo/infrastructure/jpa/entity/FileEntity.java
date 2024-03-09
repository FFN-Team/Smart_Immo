package com.gangdestrois.smartimmo.infrastructure.jpa.entity;

import com.gangdestrois.smartimmo.domain.document.DocumentType;
import com.gangdestrois.smartimmo.domain.document.File;
import jakarta.persistence.*;

import java.time.LocalDate;

import static java.util.Objects.isNull;

@Entity
@Table(name = "file")
public class FileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_file")
    private Long id;

    @Column(name = "document_id")
    private String documentId;

    @Column(name = "name")
    private String name;

    @Column(name = "web_content_link")
    private String webContentLink;

    @Column(name = "web_link")
    private String webLink;

    @ManyToOne(targetEntity = FolderEntity.class)
    @JoinColumn(name = "fk_parent", referencedColumnName = "id_folder")
    private FolderEntity folder;

    @ManyToOne(targetEntity = ProspectEntity.class)
    @JoinColumn(name = "fk_owner", referencedColumnName = "id_prospect")
    private ProspectEntity owner;

    @Enumerated(EnumType.STRING)
    @Column(name = "document_type")
    private DocumentType documentType;

    @Column(name = "created")
    private LocalDate created;

    public FileEntity(String documentId, String name, String webContentLink, String webLink, FolderEntity parent, ProspectEntity owner,
                      DocumentType documentType, LocalDate created) {
        this.documentId = documentId;
        this.name = name;
        this.webContentLink = webContentLink;
        this.webLink = webLink;
        this.folder = parent;
        this.owner = owner;
        this.documentType = documentType;
        this.created = created;
    }

    public FileEntity(String documentId, String name, String webContentLink, String webLink, ProspectEntity owner,
                      LocalDate created) {
        this.documentId = documentId;
        this.name = name;
        this.webContentLink = webContentLink;
        this.webLink = webLink;
        this.owner = owner;
        this.created = created;
    }

    public FileEntity() {
    }

    public Long getId() {
        return id;
    }

    public static FileEntity fromModel(File file, ProspectEntity prospectEntity, FolderEntity parentEntity,
                                       LocalDate created) {
        if (isNull(parentEntity)) return new FileEntity(file.getDocumentId(), file.getName(),
                file.getWebContentLink(), file.getWebLink(), prospectEntity, created);
        return new FileEntity(file.getDocumentId(), file.getName(), file.getWebContentLink(), file.getWebLink(),
                parentEntity, prospectEntity, file.getDocumentType(), created);
    }

    public static File toModel(FileEntity fileEntity) {
        return new File(fileEntity.getId(), fileEntity.documentId, fileEntity.name, fileEntity.webContentLink,
                fileEntity.webLink, fileEntity.documentType, fileEntity.created);
    }
}
