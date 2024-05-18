-- Ajouter la colonne id_prospect à notification avec une contrainte de clé étrangère
DO
$$
    BEGIN
        IF NOT EXISTS (SELECT 1
                       FROM information_schema.columns
                       WHERE table_name = 'notification'
                         AND column_name = 'fk_prospect') THEN
            ALTER TABLE notification
                ADD COLUMN fk_prospect INTEGER,
                ADD CONSTRAINT fk_contrainte FOREIGN KEY (fk_prospect)
                    REFERENCES prospect (id_prospect);
        END IF;
    END
$$;

CREATE TABLE IF NOT EXISTS home
(
    id_home        SERIAL PRIMARY KEY,
    marital_status VARCHAR(255)
);

DO
$$
    BEGIN
        IF NOT EXISTS (SELECT 1
                       FROM information_schema.columns
                       WHERE table_name = 'potential_project'
                         AND column_name = 'notification_date') THEN
            ALTER TABLE potential_project
                ADD COLUMN notification_date DATE;
        END IF;
    END
$$;

DO $$
    BEGIN
        -- Check if the foreign key constraint named 'fk' already exists on the table 'prospect'
        IF NOT EXISTS (
            SELECT 1
            FROM information_schema.table_constraints
            WHERE table_name = 'prospect'
              AND constraint_type = 'FOREIGN KEY'
              AND constraint_name = 'fk'
        ) THEN
            ALTER TABLE prospect
                ADD CONSTRAINT fk FOREIGN KEY (fk_home)
                    REFERENCES home (id_home);
        END IF;
    END
$$;



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
    age      INTEGER,
    fk_home  INTEGER,
    FOREIGN KEY (fk_home) REFERENCES home (id_home)
);

CREATE TABLE IF NOT EXISTS property_owner
(
    id_property_owner SERIAL PRIMARY KEY,
    acquisition_date  DATE,
    main              BOOLEAN,
    fk_owner          INTEGER,
    fk_property       INTEGER,
    FOREIGN KEY (fk_owner) REFERENCES owner (id_owner),
    FOREIGN KEY (fk_property) REFERENCES property (id_property)
);



