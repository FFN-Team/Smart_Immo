CREATE TABLE IF NOT EXISTS street
(
    id_street           SERIAL PRIMARY KEY,
    street_name         VARCHAR(255),
    street_surface_area DOUBLE PRECISION
);

CREATE TABLE IF NOT EXISTS city
(
    id_city           SERIAL PRIMARY KEY,
    city_name         VARCHAR(255),
    city_surface_area DOUBLE PRECISION
);

CREATE TABLE IF NOT EXISTS address
(
    id_address    SERIAL PRIMARY KEY,
    street_number INT,
    fk_street     INT,
    fk_city       INT,
    FOREIGN KEY (fk_street) REFERENCES street (id_street),
    FOREIGN KEY (fk_city) REFERENCES city (id_city)
);


DO
$$
    BEGIN
        IF NOT EXISTS (SELECT 1
                       FROM information_schema.columns
                       WHERE table_name = 'property' AND column_name = 'fk_address') THEN
            ALTER TABLE property
                ADD COLUMN fk_address INT,
                ADD CONSTRAINT fk_address_property FOREIGN KEY (fk_address) REFERENCES address (id_address);
        END IF;
    END
$$;


INSERT INTO street (street_name, street_surface_area)
VALUES ('Rue Étienne Marcel', 14040.0),
       ('Rue Albert Camus', 1162.0),
       ('Rue Falconet', 252.0),
       ('Rue du Niger', 2400.0),
       ('Rue d''Amboise', 771.4),
       ('Rue Oberkampf', 15990.0);

INSERT INTO city (city_name, city_surface_area)
VALUES ('Paris', 105400000.0);

INSERT INTO address (street_number, fk_street, fk_city)
VALUES (1, 1, 1),
       (4, 2, 1),
       (2, 3, 1),
       (9, 4, 1),
       (5, 5, 1),
       (15, 6, 1);

UPDATE property
SET fk_address = id_property
WHERE fk_address IS NULL
   OR fk_address <> id_property;
