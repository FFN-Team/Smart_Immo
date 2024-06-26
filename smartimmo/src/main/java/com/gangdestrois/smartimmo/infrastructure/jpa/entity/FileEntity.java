package com.gangdestrois.smartimmo.infrastructure.jpa.entity;

import com.gangdestrois.smartimmo.domain.document.model.File;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

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
    @JoinColumn(name = "fk_prospect", referencedColumnName = "id_prospect")
    private ProspectEntity prospect;

    @ManyToOne(targetEntity = PropertyEntity.class)
    @JoinColumn(name = "fk_property", referencedColumnName = "id_property")
    private PropertyEntity property;

    @JoinColumn(name = "fk_document_type", referencedColumnName = "id_document_type")
    @ManyToOne(targetEntity = DocumentTypeEntity.class)
    private DocumentTypeEntity documentType;

    @Column(name = "created")
    private LocalDate created;

    public FileEntity(String documentId, String name, String webContentLink, String webLink, FolderEntity parent, ProspectEntity prospect,
                      DocumentTypeEntity documentType, LocalDate created) {
        this.documentId = documentId;
        this.name = name;
        this.webContentLink = webContentLink;
        this.webLink = webLink;
        this.folder = parent;
        this.prospect = prospect;
        this.documentType = documentType;
        this.created = created;
    }

    public FileEntity(String documentId, String name, String webContentLink, String webLink, ProspectEntity prospect,
                      LocalDate created) {
        this.documentId = documentId;
        this.name = name;
        this.webContentLink = webContentLink;
        this.webLink = webLink;
        this.prospect = prospect;
        this.created = created;
    }

    public FileEntity() {
    }

    public Long getId() {
        return id;
    }

    public static FileEntity fromModel(File file, ProspectEntity prospectEntity, FolderEntity parentEntity,
                                       DocumentTypeEntity documentTypeEntity, LocalDate created) {
        if (isNull(parentEntity)) return new FileEntity(file.getDocumentId(), file.getName(),
                file.getWebContentLink(), file.getWebLink(), prospectEntity, created);
        return new FileEntity(file.getDocumentId(), file.getName(), file.getWebContentLink(), file.getWebLink(),
                parentEntity, prospectEntity, documentTypeEntity, created);
    }

    public static File toModel(FileEntity fileEntity) {
        return (File) new File.FileBuilder()
                .documentType(DocumentTypeEntity.toModel(fileEntity.documentType))
                .id(fileEntity.getId())
                .documentId(fileEntity.documentId)
                .name(fileEntity.name)
                .webContentLink(fileEntity.webContentLink)
                .webLink(fileEntity.webLink)
                .build();
    }
}