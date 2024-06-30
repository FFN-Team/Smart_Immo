package com.gangdestrois.smartimmo.domain.document.model;

import com.gangdestrois.smartimmo.domain.actor.enums.Actor;
import com.gangdestrois.smartimmo.domain.document.enums.DocumentHolderType;

import java.util.List;

public class DocumentType {
    private final String name;
    private final String description;
    private List<Actor> holders;
    private final DocumentHolderType documentHolderType;

    public DocumentType(String name, String description, List<Actor> holders, DocumentHolderType documentHolderType) {
        this.name = name;
        this.description = description;
        this.holders = holders;
        this.documentHolderType = documentHolderType;
    }

    public DocumentType(String name, String description, DocumentHolderType documentHolderType) {
        this.name = name;
        this.description = description;
        this.documentHolderType = documentHolderType;
    }

    public String name() {
        return this.name;
    }

    public String description() {
        return this.description;
    }

    public List<Actor> holders() {
        return this.holders;
    }

    public DocumentHolderType holderType() {
        return this.documentHolderType;
    }
}
