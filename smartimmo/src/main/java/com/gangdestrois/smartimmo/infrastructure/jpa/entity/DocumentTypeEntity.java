package com.gangdestrois.smartimmo.infrastructure.jpa.entity;

import com.gangdestrois.smartimmo.domain.document.enums.DocumentHolderType;
import com.gangdestrois.smartimmo.domain.document.model.DocumentType;
import jakarta.persistence.*;

import java.util.List;

import static java.util.Objects.nonNull;

@Entity
@Table(name = "document_type")
public class DocumentTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_document_type")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "document_holder_type")
    private String documentHolderType;

    @OneToMany(mappedBy = "documentType")
    private List<FileEntity> files;

    // TODO : rajouter holders dans le docker + rajouter une table de jonction
/*    @OneToMany(mappedBy = "document_type")
    private List<DocumentHolderEntity> holders;*/

    public DocumentTypeEntity(String name, String description, String documentHolderType) {
        this.name = name;
        this.description = description;
        this.documentHolderType = documentHolderType;
    }

    public DocumentTypeEntity() {

    }

    public static DocumentType toModel(DocumentTypeEntity documentType) {
        return new DocumentType(documentType.name, documentType.description,/*
                documentType.holders.stream()
                        .map(holder -> nonNull(holder.name()) ? Actor.valueOf(holder.name()) : null)
                        .toList(),*/
                nonNull(documentType.documentHolderType) ?
                        DocumentHolderType.valueOf(documentType.documentHolderType) : null);
    }

    public static DocumentTypeEntity fromModel(DocumentType documentType) {
        return new DocumentTypeEntity(documentType.name(), documentType.description(), documentType.ownerType().name());
    }
}