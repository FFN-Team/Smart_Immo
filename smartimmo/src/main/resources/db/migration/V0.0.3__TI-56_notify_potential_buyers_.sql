-- Ajouter la colonne id_prospect à notification avec une contrainte de clé étrangère
ALTER TABLE notification
    ADD COLUMN fk_prospect INTEGER,
    ADD CONSTRAINT fk_contrainte FOREIGN KEY (fk_prospect)
        REFERENCES prospect (id_prospect);

CREATE TABLE IF NOT EXISTS home
(
    id_home       SERIAL PRIMARY KEY,
    maritalStatus VARCHAR(255)
);

ALTER TABLE prospect
    ADD COLUMN fk_home INTEGER,
    ADD CONSTRAINT fk FOREIGN KEY (fk_home)
        REFERENCES home (id_home);

CREATE TABLE IF NOT EXISTS owner
(
    id_owner    SERIAL PRIMARY KEY,
    active      BOOLEAN,
    fk_prospect INTEGER,
    FOREIGN KEY (fk_prospect) REFERENCES prospect (id_prospect)
);

CREATE TABLE IF NOT EXISTS child
(
    id_child SERIAL PRIMARY KEY,
    age      INTEGER
);

CREATE TABLE IF NOT EXISTS property_owner
(
    id_property_owner SERIAL PRIMARY KEY,
    acquisition_date  DATE,
    fk_owner          INTEGER,
    fk_property       INTEGER,
    FOREIGN KEY (fk_owner) REFERENCES owner (id_owner),
    FOREIGN KEY (fk_property) REFERENCES property (id_property)
);



