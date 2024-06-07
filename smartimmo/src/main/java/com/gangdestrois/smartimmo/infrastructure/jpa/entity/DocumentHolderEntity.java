package com.gangdestrois.smartimmo.infrastructure.jpa.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "document_holder")
public class DocumentHolderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_document_holder")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "document_holder_type")
    private String documentHolderType;

    @ManyToOne(targetEntity = DocumentTypeEntity.class)
    @JoinColumn(name = "fk_document_type", referencedColumnName = "id_document_type")
    private DocumentTypeEntity documentType;

    public String name() {
        return name;
    }
}