CREATE TABLE IF NOT EXISTS prospect
(
    id_prospect                       SERIAL PRIMARY KEY,
    contact_origin                    VARCHAR(255),
    title                             VARCHAR(255),
    last_name                         VARCHAR(255),
    first_name                        VARCHAR(255),
    date_of_birth                     DATE,
    profession                        VARCHAR(255),
    mobile                            BIGINT,
    mail                              VARCHAR(255),
    authorize_contact_on_social_media BOOLEAN,
    fk_home                           INT
);

CREATE TABLE IF NOT EXISTS property
(
    id_property   SERIAL PRIMARY KEY,
    property_name VARCHAR(255),
    description   TEXT,
    room_number   INT,
    livable_area  DOUBLE PRECISION
);

CREATE TABLE IF NOT EXISTS buyer
(
    id_buyer          SERIAL PRIMARY KEY,
    fk_prospect       INT,
    status            VARCHAR(255),
    search_start_date DATE,
    search_end_date   DATE,
    FOREIGN KEY (fk_prospect) REFERENCES prospect (id_prospect)
);

CREATE TABLE property_criteria
(
    id_property_criteria SERIAL PRIMARY KEY,
    fk_buyer             INT,
    room_number          INT,
    minimum_surface      DOUBLE PRECISION,
    FOREIGN KEY (fk_buyer) REFERENCES buyer (id_buyer)
);

-- Inserts pour la table prospect
INSERT INTO prospect (contact_origin, title, last_name, first_name, date_of_birth, profession, mobile, mail,
                      authorize_contact_on_social_media)
VALUES ('Internet', 'M.', 'Dupont', 'Jean', '1990-01-15', 'Ingénieur', 123456789, 'jean.dupont@email.com', true),
       ('Référence', 'Mme', 'Martin', 'Sophie', '1985-07-20', 'Médecin', 987654321, 'sophie.martin@email.com', false),
       ('Bouche à oreille', 'M.', 'Lefevre', 'Philippe', '1982-05-10', 'Commercial', 555111222,
        'philippe.lefevre@email.com', true),
       ('Site Web', 'Mlle', 'Durand', 'Marie', '1995-12-03', 'Étudiante', 777888999, 'marie.durand@email.com', false);

-- Inserts pour la table property
INSERT INTO property (property_name, description, room_number, livable_area)
VALUES ('Appartement 1', 'Bel appartement avec vue sur la mer', 3, 120.5),
       ('Maison 1', 'Spacieuse maison avec jardin', 4, 200.0),
       ('Studio 1', 'Petit studio property situé en centre-ville', 1, 40.0),
       ('Maison 2', 'Maison avec piscine et jardin spacieux', 5, 300.0);


-- Inserts pour la table buyer
INSERT INTO buyer (fk_prospect, status, search_start_date, search_end_date)
VALUES (1, 'En recherche', '2023-01-01', '2023-06-30'),
       (2, 'En attente', '2023-02-15', '2023-06-30'),
       (3, 'En attente', '2023-03-20', '2023-06-30'),
       (4, 'En recherche', '2023-01-10', '2023-05-15');

-- Inserts pour la table critere_bien
INSERT INTO property_criteria (fk_buyer, room_number, minimum_surface)
VALUES (1, 3, 100.0),
       (2, 4, 150.0),
       (3, 2, 50.0),
       (4, 3, 120.0);