package com.gangdestrois.smartimmo.infrastructure.jpa.entity;

import com.gangdestrois.smartimmo.domain.document.Document;
import com.gangdestrois.smartimmo.domain.document.Folder;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "folder")
public class FolderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_folder")
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

    @OneToMany(mappedBy = "folder")
    private List<FolderEntity> folderChildren;

    @OneToMany(mappedBy = "folder")
    private List<FileEntity> fileChildren;

    public FolderEntity(String documentId, String name, String webContentLink, String webLink, FolderEntity parent) {
        this.documentId = documentId;
        this.name = name;
        this.webContentLink = webContentLink;
        this.webLink = webLink;
        this.folder = parent;
    }

    public FolderEntity(String documentId, String name, String webContentLink, String webLink) {
        this.documentId = documentId;
        this.name = name;
        this.webContentLink = webContentLink;
        this.webLink = webLink;
    }

    public FolderEntity() {
    }

    public Long getId() {
        return id;
    }

    public Optional<FolderEntity> getParent() {
        return Optional.ofNullable(folder);
    }

    public static FolderEntity fromModel(Folder folder, FolderEntity parent) {
        return new FolderEntity(folder.getDocumentId(), folder.getName(), folder.getWebContentLink(), folder.getWebLink(),
                parent);
    }

    public static Folder toModel(FolderEntity folderEntity) {
        List<Document> documents = new ArrayList<>();
        folderEntity.fileChildren.stream().map(FileEntity::toModel).forEach(documents::add);
        folderEntity.folderChildren.stream().map(FolderEntity::toModel).forEach(documents::add);
        return new Folder(folderEntity.id, folderEntity.documentId, folderEntity.name, folderEntity.getWebContentLink(),
                folderEntity.getWebLink(), documents);
    }

    public String getDocumentId() {
        return documentId;
    }

    public String getName() {
        return name;
    }

    public String getWebContentLink() {
        return webContentLink;
    }

    public String getWebLink() {
        return webLink;
    }
}
