CREATE TABLE IF NOT EXISTS prospect (
    id_prospect SERIAL PRIMARY KEY,
    origine_contact VARCHAR(255),
    civilite VARCHAR(255),
    nom VARCHAR(255),
    prenom VARCHAR(255),
    date_naissance DATE,
    profession VARCHAR(255),
    tel_mobile BIGINT,
    mail VARCHAR(255),
    authorise_contact_reseaux BOOLEAN
);

CREATE TABLE IF NOT EXISTS bien (
    bien_id SERIAL PRIMARY KEY,
    nom_bien VARCHAR(255),
    description TEXT,
    nb_piece INT,
    surface_habitable DOUBLE PRECISION
);

CREATE TABLE IF NOT EXISTS acquereur (
    acquereur_id SERIAL PRIMARY KEY,
    ref_prospect INT,
    statut VARCHAR(255),
    date_debut_recherche DATE,
    date_fin_recherche DATE,

    FOREIGN KEY (ref_prospect) REFERENCES prospect(id_prospect)
);

CREATE TABLE critere_bien (
    id_critere_bien SERIAL PRIMARY KEY,
    ref_acquereur INT,
    nombre_piece INT,
    surface_minimum DOUBLE PRECISION,

    FOREIGN KEY (ref_acquereur) REFERENCES acquereur(acquereur_id)
);






-- Inserts pour la table prospect
INSERT INTO prospect (origine_contact, civilite, nom, prenom, date_naissance, profession, tel_mobile, mail, authorise_contact_reseaux)
VALUES
('Internet', 'M.', 'Dupont', 'Jean', '1990-01-15', 'Ingénieur', 123456789, 'jean.dupont@email.com', true),
('Référence', 'Mme', 'Martin', 'Sophie', '1985-07-20', 'Médecin', 987654321, 'sophie.martin@email.com', false),
('Bouche à oreille', 'M.', 'Lefevre', 'Philippe', '1982-05-10', 'Commercial', 555111222, 'philippe.lefevre@email.com', true),
('Site Web', 'Mlle', 'Durand', 'Marie', '1995-12-03', 'Étudiante', 777888999, 'marie.durand@email.com', false);

-- Inserts pour la table bien
INSERT INTO bien (nom_bien, description, nb_piece, surface_habitable)
VALUES
('Appartement 1', 'Bel appartement avec vue sur la mer', 3, 120.5),
('Maison 1', 'Spacieuse maison avec jardin', 4, 200.0),
('Studio 1', 'Petit studio bien situé en centre-ville', 1, 40.0),
('Maison 2', 'Maison avec piscine et jardin spacieux', 5, 300.0);


-- Inserts pour la table acquereur
INSERT INTO acquereur (ref_prospect, statut, date_debut_recherche, date_fin_recherche)
VALUES
(1, 'En recherche', '2023-01-01', '2023-06-30'),
(2, 'En attente', '2023-02-15', '2023-06-30'),
(3, 'En attente', '2023-03-20', '2023-06-30'),
(4, 'En recherche', '2023-01-10', '2023-05-15');

-- Inserts pour la table critere_bien
INSERT INTO critere_bien (ref_acquereur, nombre_piece, surface_minimum)
VALUES
(1, 3, 100.0),
(2, 4, 150.0),
(3, 2, 50.0),
(4, 3, 120.0);