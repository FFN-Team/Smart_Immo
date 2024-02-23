package com.gangdestrois.smartimmo.domain.document;

public enum DocumentType {
    VISITE_PHOTO("Visite photo"),
    PROSPECT_IDENTITY("Justificatif d'identité"),
    CIVIL_STATUS("Etat civil");
    private final String name;

    DocumentType(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
