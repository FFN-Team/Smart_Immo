CREATE TABLE IF NOT EXISTS document_type
(
    id_document_type     SERIAL PRIMARY KEY,
    name                 VARCHAR(255),
    description          VARCHAR(255),
    document_holder_type VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS document_holder
(
    id_document_holder SERIAL PRIMARY KEY,
    fk_document_type   INT,
    holder             VARCHAR(255),
    FOREIGN KEY (fk_document_type) REFERENCES document_type (id_document_type)
);

DO
$$
    BEGIN
        IF NOT EXISTS (SELECT 1
                       FROM information_schema.columns
                       WHERE table_name = 'file'
                         AND column_name = 'fk_document_type') THEN
            ALTER TABLE file
                ADD COLUMN fk_document_type INT,
                ADD CONSTRAINT fk_file_document_type FOREIGN KEY (fk_document_type) REFERENCES document_type (id_document_type);
        END IF;
    END
$$;

INSERT INTO document_type (name, description, document_holder_type)
values ('TITLE_DEED', 'Titre de propriété', 'PROPERTY'),
       ('CADASTRAL_MAP', 'Plan cadastral', 'PROPERTY'),
       ('PROPERTY_TAX_NOTICE', 'Avis de taxe foncière', 'PROPERTY'),
       ('PROSPECT_IDENTITY', 'Justificatif d''identité', 'PROSPECT'),
       ('CIVIL_STATUS', 'Etat civil', 'PROSPECT'),
       ('ARTICLES_OF_ASSOCIATION', 'Statuts de société', 'PROSPECT'),
       ('GENERAL_ASSEMBLY', 'Assemblée générale', 'PROPERTY'),
       ('LEASE', 'Bail', 'PROPERTY'),
       ('PARASITE_STATUS', 'Etat parasitaire', 'PROPERTY'),
       ('LEAD_DIAGNOSTIC', 'Constat de risque d''exposition au plomb', 'PROPERTY'),
       ('ASBESTOS_TECHNICAL_FILE', 'Dossier technique amiante', 'PROPERTY'),
       ('PRESALES_ASBESTOS_DIAGNOSIS', 'Diagnostic amiante avant-vente', 'PROPERTY'),
       ('ENERGY_PERFORMANCE_DIAGNOSIS', 'Diagnostic de performance énergétique', 'PROPERTY'),
       ('INDOOR_ELECTRICAL_INSTALLATION', 'Etat de l''installation intérieure électrique', 'PROPERTY'),
       ('INDOOR_GAS_INSTALLATION', 'Etat de l''installation intérieure gaz', 'PROPERTY'),
       ('STATE_OF_NATURAL_MINING_AND_TECHNOLOGICAL_RISKS', 'Etat des Risques Naturels, Miniers et Technologiques',
        'PROPERTY'),
       ('SANITATION_DIAGNOSIS', 'Diagnostic assainissement', 'PROPERTY'),
       ('BUILDINGS_PERMITS', 'Permis de construire', 'PROPERTY'),
       ('DECLARATION_OF_COMPLETION_OF_WORKS', 'Déclaration d''Achèvement de Travaux', 'PROPERTY'),
       ('CERTIFICATE_OF_CONFORMITY', 'Certificat de conformité', 'PROPERTY'),
       ('DAMAGE_CERTIFICATES', 'Attestations dommage-ouvrage', 'PROPERTY'),
       ('TEN_YEAR_WARRANTY', 'Garantie décennale', 'PROPERTY'),
       ('MINUTES_OF_GENERAL_ASSEMBLY', 'Procès-verbaux d''Assemblée Générale', 'PROPERTY'),
       ('CERTIFICATE_OF_SURFACE_AREA', 'Attestation de superficie', 'PROPERTY'),
       ('CONDOMINIUM_BY_LAWS', 'Réglement de copropriété', 'PROPERTY'),
       ('DESCRIPTIVE_STATEMENT_OF_DIVISION', 'Etat Descriptif de Division', 'PROPERTY'),
       ('LOAD_READINGS', 'Relevés de charges', 'PROPERTY'),
       ('DATED_PRECONDITION', 'Pré-état daté', 'PROPERTY'),
       ('BOUNDARY_DOCUMENT', 'Document de bornage', 'PROPERTY'),
       ('URBAN_PLANNING_CERTIFICATE', 'Certificat d''urbanisme', 'PROPERTY'),
       ('QUOTATION_OF_WORK', 'Devis travaux', 'PROPERTY'),
       ('NOTICE_OF_TAX_HABITATION', 'Avis de Taxe habitation', 'PROPERTY'),
       ('WATER_CONSUMPTION_BILLS', 'Factures de Consommation Eau', 'PROPERTY'),
       ('EDF_CONSUMPTION_BILLS', 'Factures de Consommation EDF', 'PROPERTY'),
       ('GAS_CONSUMPTION_BILLS', 'Factures de Consommation GAZ', 'PROPERTY'),
       ('FUEL_OR_PROPANE_CONSUMPTION_BILLS', 'Factures de Consommation Fioul ou Propane', 'PROPERTY'),
       ('FLOOR_PLAN_OF_APARTMENT', 'Plan de l''appartement', 'PROPERTY'),
       ('FLOOR_PLAN_OF_HOUSE', 'Plan de l''appartement', 'PROPERTY'),
       ('GROUND_PLAN', 'Plan du terrain', 'PROPERTY'),
       ('VISIT_PHOTOS', 'Visite photo', 'PROPERTY');

INSERT INTO document_holder(fk_document_type, holder)
values ((select id_document_type from document_type where name = 'TITLE_DEED' LIMIT 1), 'OWNER'),
       ((select id_document_type from document_type where name = 'TITLE_DEED' LIMIT 1), 'NOTARY'),
       ((select id_document_type from document_type where name = 'CADASTRAL_MAP' LIMIT 1), 'OWNER'),
       ((select id_document_type from document_type where name = 'CADASTRAL_MAP' LIMIT 1), 'NOTARY'),
       ((select id_document_type from document_type where name = 'PROPERTY_TAX_NOTICE' LIMIT 1), 'OWNER'),
       ((select id_document_type from document_type where name = 'PROSPECT_IDENTITY' LIMIT 1), 'OWNER'),
       ((select id_document_type from document_type where name = 'CIVIL_STATUS' LIMIT 1), 'OWNER'),
       ((select id_document_type from document_type where name = 'ARTICLES_OF_ASSOCIATION' LIMIT 1), 'OWNER'),
       ((select id_document_type from document_type where name = 'GENERAL_ASSEMBLY' LIMIT 1), 'OWNER'),
       ((select id_document_type from document_type where name = 'LEASE' LIMIT 1), 'OWNER'),
       ((select id_document_type from document_type where name = 'PARASITE_STATUS' LIMIT 1), 'DIAGNOSTICIAN'),
       ((select id_document_type from document_type where name = 'LEAD_DIAGNOSTIC' LIMIT 1), 'DIAGNOSTICIAN'),
       ((select id_document_type from document_type where name = 'ASBESTOS_TECHNICAL_FILE' LIMIT 1), 'DIAGNOSTICIAN'),
       ((select id_document_type from document_type where name = 'PRESALES_ASBESTOS_DIAGNOSIS' LIMIT 1),
        'DIAGNOSTICIAN'),
       ((select id_document_type from document_type where name = 'ENERGY_PERFORMANCE_DIAGNOSIS' LIMIT 1),
        'DIAGNOSTICIAN'),
       ((select id_document_type from document_type where name = 'INDOOR_ELECTRICAL_INSTALLATION' LIMIT 1),
        'DIAGNOSTICIAN'),
       ((select id_document_type from document_type where name = 'INDOOR_GAS_INSTALLATION' LIMIT 1), 'DIAGNOSTICIAN'),
       ((select id_document_type
         from document_type
         where name = 'STATE_OF_NATURAL_MINING_AND_TECHNOLOGICAL_RISKS'
         LIMIT 1), 'DIAGNOSTICIAN'),
       ((select id_document_type from document_type where name = 'SANITATION_DIAGNOSIS' LIMIT 1), 'DIAGNOSTICIAN'),
       ((select id_document_type from document_type where name = 'BUILDINGS_PERMITS' LIMIT 1), 'OWNER'),
       ((select id_document_type from document_type where name = 'BUILDINGS_PERMITS' LIMIT 1), 'URBANISM'),
       ((select id_document_type from document_type where name = 'DECLARATION_OF_COMPLETION_OF_WORKS' LIMIT 1),
        'OWNER'),
       ((select id_document_type from document_type where name = 'DECLARATION_OF_COMPLETION_OF_WORKS' LIMIT 1),
        'URBANISM'),
       ((select id_document_type from document_type where name = 'CERTIFICATE_OF_CONFORMITY' LIMIT 1), 'OWNER'),
       ((select id_document_type from document_type where name = 'CERTIFICATE_OF_CONFORMITY' LIMIT 1), 'URBANISM'),
       ((select id_document_type from document_type where name = 'DAMAGE_CERTIFICATES' LIMIT 1), 'OWNER'),
       ((select id_document_type from document_type where name = 'DAMAGE_CERTIFICATES' LIMIT 1), 'BUILDER'),
       ((select id_document_type from document_type where name = 'TEN_YEAR_WARRANTY' LIMIT 1), 'OWNER'),
       ((select id_document_type from document_type where name = 'TEN_YEAR_WARRANTY' LIMIT 1), 'BUILDER'),
       ((select id_document_type from document_type where name = 'MINUTES_OF_GENERAL_ASSEMBLY' LIMIT 1), 'OWNER'),
       ((select id_document_type from document_type where name = 'MINUTES_OF_GENERAL_ASSEMBLY' LIMIT 1), 'SYNDICATE'),
       ((select id_document_type from document_type where name = 'CERTIFICATE_OF_SURFACE_AREA' LIMIT 1), 'OWNER'),
       ((select id_document_type from document_type where name = 'CERTIFICATE_OF_SURFACE_AREA' LIMIT 1), 'SYNDICATE'),
       ((select id_document_type from document_type where name = 'CONDOMINIUM_BY_LAWS' LIMIT 1), 'OWNER'),
       ((select id_document_type from document_type where name = 'CONDOMINIUM_BY_LAWS' LIMIT 1), 'SYNDICATE'),
       ((select id_document_type from document_type where name = 'DESCRIPTIVE_STATEMENT_OF_DIVISION' LIMIT 1), 'OWNER'),
       ((select id_document_type from document_type where name = 'DESCRIPTIVE_STATEMENT_OF_DIVISION' LIMIT 1),
        'SYNDICATE'),
       ((select id_document_type from document_type where name = 'LOAD_READINGS' LIMIT 1), 'OWNER'),
       ((select id_document_type from document_type where name = 'LOAD_READINGS' LIMIT 1), 'SYNDICATE'),
       ((select id_document_type from document_type where name = 'DATED_PRECONDITION' LIMIT 1), 'OWNER'),
       ((select id_document_type from document_type where name = 'DATED_PRECONDITION' LIMIT 1), 'SYNDICATE'),
       ((select id_document_type from document_type where name = 'BOUNDARY_DOCUMENT' LIMIT 1), 'OWNER'),
       ((select id_document_type from document_type where name = 'BOUNDARY_DOCUMENT' LIMIT 1), 'URBANISM'),
       ((select id_document_type from document_type where name = 'BOUNDARY_DOCUMENT' LIMIT 1), 'MORTGAGE'),
       ((select id_document_type from document_type where name = 'URBAN_PLANNING_CERTIFICATE' LIMIT 1), 'OWNER'),
       ((select id_document_type from document_type where name = 'URBAN_PLANNING_CERTIFICATE' LIMIT 1), 'URBANISM'),
       ((select id_document_type from document_type where name = 'URBAN_PLANNING_CERTIFICATE' LIMIT 1), 'MORTGAGE'),
       ((select id_document_type from document_type where name = 'QUOTATION_OF_WORK' LIMIT 1), 'OWNER'),
       ((select id_document_type from document_type where name = 'NOTICE_OF_TAX_HABITATION' LIMIT 1), 'OWNER'),
       ((select id_document_type from document_type where name = 'WATER_CONSUMPTION_BILLS' LIMIT 1), 'OWNER'),
       ((select id_document_type from document_type where name = 'EDF_CONSUMPTION_BILLS' LIMIT 1), 'OWNER'),
       ((select id_document_type from document_type where name = 'GAS_CONSUMPTION_BILLS' LIMIT 1), 'OWNER'),
       ((select id_document_type from document_type where name = 'FUEL_OR_PROPANE_CONSUMPTION_BILLS' LIMIT 1), 'OWNER'),
       ((select id_document_type from document_type where name = 'FLOOR_PLAN_OF_APARTMENT' LIMIT 1), 'OWNER'),
       ((select id_document_type from document_type where name = 'FLOOR_PLAN_OF_HOUSE' LIMIT 1), 'OWNER'),
       ((select id_document_type from document_type where name = 'GROUND_PLAN' LIMIT 1), 'OWNER'),
       ((select id_document_type from document_type where name = 'VISIT_PHOTOS' LIMIT 1), 'OWNER');