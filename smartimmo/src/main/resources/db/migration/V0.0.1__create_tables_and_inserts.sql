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


INSERT INTO prospect (contact_origin, title, last_name, first_name, date_of_birth, profession, mobile, mail, authorize_contact_on_social_media)
VALUES
    ('EMAIL', 'MR', 'Martin', 'Sophie', '1985-07-20', 'ENGINEER', 987654321, 'sophie.martin@email.com', false),
    ('EMAIL', 'MRS', 'Dupont', 'Jean', '1990-01-15', 'STUDENT', 123456789, 'jean.dupont@email.com', true),
    ('EMAIL', 'DR', 'Smith', 'John', '1988-03-25', 'ENGINEER', 111222333, 'john.smith@email.com', true),
    ('EMAIL', 'MR', 'Johnson', 'Emily', '1982-08-10', 'TEACHER', 444555666, 'emily.johnson@email.com', false),
    ('EMAIL', 'DR', 'Jones', 'David', '1992-07-18', 'DOCTOR', 222333444, 'david.jones@email.com', true),
    ('EMAIL', 'MRS', 'Taylor', 'Sophie', '1984-06-28', 'TEACHER', 444555666, 'sophie.taylor@email.com', true),
    ('EMAIL', 'MR', 'Miller', 'Olivia', '1996-09-12', 'DOCTOR', 111222333, 'olivia.miller@email.com', false),
    ('EMAIL', 'MISS', 'Baker', 'Emma', '1981-02-19', 'STUDENT', 888999000, 'emma.baker@email.com', true),

    ('PHONE', 'MR', 'Dupont', 'Jean', '1990-01-15', 'ENGINEER', 123456789, 'jean.dupont@email.com', true),
    ('PHONE', 'MRS', 'Martin', 'Sophie', '1985-07-20', 'TEACHER', 987654321, 'sophie.martin@email.com', false),
    ('PHONE', 'DR', 'Smith', 'John', '1988-03-25', 'STUDENT', 111222333, 'john.smith@email.com', true),
    ('PHONE', 'MRS', 'Johnson', 'Emily', '1982-08-10', 'DOCTOR', 444555666, 'emily.johnson@email.com', false),
    ('PHONE', 'MISS', 'Jones', 'David', '1992-07-18', 'DOCTOR', 222333444, 'david.jones@email.com', true),
    ('PHONE', 'MR', 'Taylor', 'Sophie', '1984-06-28', 'TEACHER', 444555666, 'sophie.taylor@email.com', true),
    ('PHONE', 'MRS', 'Miller', 'Olivia', '1996-09-12', 'ENGINEER', 111222333, 'olivia.miller@email.com', false),
    ('PHONE', 'MISS', 'Baker', 'Emma', '1981-02-19', 'STUDENT', 888999000, 'emma.baker@email.com', true),
    ('SOCIAL_MEDIA', 'MISS', 'Lefevre', 'Philippe', '1982-05-10', 'DOCTOR', 555111222, 'philippe.lefevre@email.com', true),
    ('SOCIAL_MEDIA', 'DR', 'Smith', 'John', '1988-03-25', 'STUDENT', 111222333, 'john.smith@email.com', true),
    ('SOCIAL_MEDIA', 'MRS', 'Brown', 'Michael', '1990-05-15', 'TEACHER', 777888999, 'michael.brown@email.com', true),
    ('SOCIAL_MEDIA', 'MR', 'Clark', 'Ryan', '1987-01-21', 'ENGINEER', 888999000, 'ryan.clark@email.com', true),
    ('SOCIAL_MEDIA', 'MISS', 'Anderson', 'Liam', '1995-10-02', 'ENGINEER', 555666777, 'liam.anderson@email.com', false),
    ('SOCIAL_MEDIA', 'DR', 'Hall', 'Eva', '1993-03-08', 'STUDENT', 222333444, 'eva.hall@email.com', true),
    ('WEB_SITE', 'DR', 'Durand', 'Marie', '1995-12-03', 'ENGINEER', 777888999, 'marie.durand@email.com', false),
    ('WEB_SITE', 'MRS', 'Durand', 'Marie', '1995-12-03', 'STUDENT', 777888999, 'marie.durand@email.com', false),
    ('WORD_OF_MOUTH', 'MR', 'Durand', 'Marie', '1995-12-03', 'TEACHER', 777888999, 'marie.durand@email.com', false),
    ('WORD_OF_MOUTH', 'DR', 'Durand', 'Marie', '1995-12-03', 'DOCTOR', 777888999, 'marie.durand@email.com', false);



-- Inserts pour la table property
INSERT INTO property (property_name, description, room_number, livable_area)
VALUES ('Appartement 1', 'Bel appartement avec vue sur la mer', 3, 120.5),
       ('Maison 1', 'Spacieuse maison avec jardin', 4, 200.0),
       ('Studio 1', 'Petit studio property situé en centre-ville', 1, 40.0),
       ('Maison 2', 'Maison avec piscine et jardin spacieux', 5, 300.0);


-- Inserts pour la table buyer
INSERT INTO buyer (fk_prospect, status, search_start_date, search_end_date)
VALUES (1, 'SEARCHING', '2023-01-01', '2023-06-30'),
       (2, 'WAITING', '2023-02-15', '2023-06-30'),
       (3, 'WAITING', '2023-03-20', '2023-06-30'),
       (4, 'SEARCHING', '2023-01-10', '2023-05-15');

-- Inserts pour la table critere_bien
INSERT INTO property_criteria (fk_buyer, room_number, minimum_surface)
VALUES (1, 3, 100.0),
       (2, 4, 150.0),
       (3, 2, 50.0),
       (4, 3, 120.0);