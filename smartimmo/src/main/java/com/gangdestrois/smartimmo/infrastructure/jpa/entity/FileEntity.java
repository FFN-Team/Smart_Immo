package com.gangdestrois.smartimmo.infrastructure.jpa.entity;

import com.gangdestrois.smartimmo.domain.document.model.File;
import jakarta.persistence.*;

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
    private ProspectEntity prospect;

    public FileEntity(String documentId, String name, String webContentLink, String webLink, FolderEntity parent, ProspectEntity owner) {
        this.documentId = documentId;
        this.name = name;
        this.webContentLink = webContentLink;
        this.webLink = webLink;
        this.folder = parent;
        this.prospect = owner;
    }

    public FileEntity(String documentId, String name, String webContentLink, String webLink, ProspectEntity owner) {
        this.documentId = documentId;
        this.name = name;
        this.webContentLink = webContentLink;
        this.webLink = webLink;
        this.prospect = owner;
    }

    public FileEntity() {
    }

    public Long getId() {
        return id;
    }

    public static FileEntity fromModel(File file, ProspectEntity prospectEntity, FolderEntity parentEntity) {
        if (isNull(parentEntity)) return new FileEntity(file.getDocumentId(), file.getName(),
                file.getWebContentLink(), file.getWebLink(), prospectEntity);
        return new FileEntity(file.getDocumentId(), file.getName(), file.getWebContentLink(), file.getWebLink(),
                parentEntity,
                prospectEntity);
    }

    public static File toModel(FileEntity fileEntity) {
        return (File) new File.FileBuilder()
                .id(fileEntity.getId())
                .documentId(fileEntity.documentId)
                .name(fileEntity.name)
                .webContentLink(fileEntity.webContentLink)
                .webLink(fileEntity.webLink)
                .build();
    }
}
