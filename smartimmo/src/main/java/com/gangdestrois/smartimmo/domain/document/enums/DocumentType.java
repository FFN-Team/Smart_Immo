package com.gangdestrois.smartimmo.domain.document.enums;

import com.gangdestrois.smartimmo.domain.actor.enums.Actor;

import java.util.List;

import static com.gangdestrois.smartimmo.domain.actor.enums.Actor.*;
import static com.gangdestrois.smartimmo.domain.document.enums.OwnerType.PROPERTY;
import static com.gangdestrois.smartimmo.domain.document.enums.OwnerType.PROSPECT;

public enum DocumentType {
    TITLE_DEED("Titre de propriété", List.of(OWNER, NOTARY), PROPERTY),
    CADASTRAL_MAP("Plan cadastral", List.of(OWNER, NOTARY), PROPERTY),
    PROPERTY_TAX_NOTICE("Avis de taxe foncière", List.of(OWNER), PROPERTY),
    PROSPECT_IDENTITY("Justificatif d'identité", List.of(OWNER), PROSPECT),
    CIVIL_STATUS("Etat civil", List.of(OWNER), PROSPECT),
    ARTICLES_OF_ASSOCIATION("Statuts de société", List.of(OWNER), PROSPECT),
    GENERAL_ASSEMBLY("Assemblée générale", List.of(OWNER), PROPERTY),
    LEASE("Bail", List.of(OWNER), PROPERTY),
    PARASITE_STATUS("Etat parasitaire", List.of(DIAGNOSTICIAN), PROPERTY),
    LEAD_DIAGNOSTIC("Constat de risque d'exposition au plomb", List.of(DIAGNOSTICIAN), PROPERTY),
    ASBESTOS_TECHNICAL_FILE("Dossier technique amiante", List.of(DIAGNOSTICIAN), PROPERTY),
    PRESALES_ASBESTOS_DIAGNOSIS("Diagnostic amiante avant-vente", List.of(DIAGNOSTICIAN), PROPERTY),
    ENERGY_PERFORMANCE_DIAGNOSIS("Diagnostic de performance énergétique", List.of(DIAGNOSTICIAN), PROPERTY),
    INDOOR_ELECTRICAL_INSTALLATION("Etat de l'installation intérieure électrique", List.of(DIAGNOSTICIAN), PROPERTY),
    INDOOR_GAS_INSTALLATION("Etat de l'installation intérieure gaz", List.of(DIAGNOSTICIAN), PROPERTY),
    STATE_OF_NATURAL_MINING_AND_TECHNOLOGICAL_RISKS("Etat des Risques Naturels, Miniers et Technologiques", List.of(DIAGNOSTICIAN), PROPERTY),
    SANITATION_DIAGNOSIS("Diagnostic assainissement", List.of(DIAGNOSTICIAN), PROPERTY),
    BUILDINGS_PERMITS("Permis de construire", List.of(OWNER, URBANISM), PROPERTY),
    DECLARATION_OF_COMPLETION_OF_WORKS("Déclaration d'Achèvement de Travaux", List.of(OWNER, URBANISM), PROPERTY),
    CERTIFICATE_OF_CONFORMITY("Certificat de conformité", List.of(OWNER, URBANISM), PROPERTY),
    DAMAGE_CERTIFICATES("Attestations dommage-ouvrage", List.of(OWNER, BUILDER), PROPERTY),
    TEN_YEAR_WARRANTY("Garantie décennale", List.of(OWNER, BUILDER), PROPERTY),
    MINUTES_OF_GENERAL_ASSEMBLY("Procès-verbaux d'Assemblée Générale", List.of(OWNER, SYNDICATE), PROPERTY),
    CERTIFICATE_OF_SURFACE_AREA("Attestation de superficie", List.of(OWNER, SYNDICATE), PROPERTY),
    CONDOMINIUM_BY_LAWS("Condominium by-laws", List.of(OWNER, SYNDICATE), PROPERTY),
    DESCRIPTIVE_STATEMENT_OF_DIVISION("Etat Descriptif de Division", List.of(OWNER, SYNDICATE), PROPERTY),
    LOAD_READINGS("Relevés de charges", List.of(OWNER, SYNDICATE), PROPERTY),
    DATED_PRECONDITION("Pré-état daté", List.of(OWNER, SYNDICATE), PROPERTY),
    BOUNDARY_DOCUMENT("Document de bornage", List.of(OWNER, URBANISM, MORTGAGE), PROPERTY),
    URBAN_PLANNING_CERTIFICATE("Certificat d'urbanisme", List.of(OWNER, URBANISM, MORTGAGE), PROPERTY),
    QUOTATION_OF_WORK("Devis travaux", List.of(OWNER), PROPERTY),
    NOTICE_OF_TAX_HABITATION("Avis de Taxe habitation", List.of(OWNER), PROPERTY),
    WATER_CONSUMPTION_BILLS("Factures de Consommation Eau", List.of(OWNER), PROPERTY),
    EDF_CONSUMPTION_BILLS("Factures de Consommation EDF", List.of(OWNER), PROPERTY),
    GAS_CONSUMPTION_BILLS("Factures de Consommation GAZ", List.of(OWNER), PROPERTY),
    FUEL_OR_PROPANE_CONSUMPTION_BILLS("Factures de Consommation Fioul ou Propane", List.of(OWNER), PROPERTY),
    FLOOR_PLAN_OF_APARTMENT("Plan de l'appartement", List.of(OWNER), PROPERTY),
    FLOOR_PLAN_OF_HOUSE("Plan de l'appartement", List.of(OWNER), PROPERTY),
    GROUND_PLAN("Plan du terrain", List.of(OWNER), PROPERTY),
    VISIT_PHOTOS("Visite photo", List.of(OWNER), PROPERTY),
    ;
    private final String description;
    private final List<Actor> holders;
    private final OwnerType ownerType;

    DocumentType(String description, List<Actor> holders, OwnerType ownerType) {
        this.description = description;
        this.holders = holders;
        this.ownerType = ownerType;
    }

    public String description() {
        return this.description;
    }

    public List<Actor> holders() {
        return this.holders;
    }

    public OwnerType ownerType() {
        return this.ownerType;
    }
}
