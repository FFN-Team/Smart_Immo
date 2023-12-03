CREATE TABLE IF NOT EXISTS bien (
    bien_id SERIAL PRIMARY KEY,
    nom_bien VARCHAR(255),
    description TEXT,
    nb_piece INT,
    surface_habitable DOUBLE PRECISION
);

CREATE TABLE IF NOT EXISTS acquereur (
    acquereur_id SERIAL PRIMARY KEY,
    statut VARCHAR(255),
    date_debut_recherche DATE,
    date_fin_recherche DATE
);

CREATE TABLE critere_bien (
    id_critere_bien SERIAL PRIMARY KEY,
    ref_acquereur INT,
    nombre_piece INT,
    surface_minimum DOUBLE PRECISION,

    FOREIGN KEY (ref_acquereur) REFERENCES acquereur(acquereur_id)
);






INSERT INTO bien (nom_bien, description, nb_piece, surface_habitable)
VALUES
('Maison de ville', 'Belle maison près du centre-ville', 4, 150.5),
('Appartement moderne', 'Vue panoramique sur la ville', 2, 80.0),
('Villa avec piscine', 'Spacieuse villa avec piscine privée', 6, 300.75),
('Maison de ville', 'Belle maison près du centre-ville', 4, 150.5),
('Appartement moderne', 'Vue panoramique sur la ville', 2, 80.0),
('Villa avec piscine', 'Spacieuse villa avec piscine privée', 6, 300.75);


INSERT INTO acquereur (statut, date_debut_recherche, date_fin_recherche)
VALUES
    ('EN_RECHERCHE', '2023-01-01', '2023-12-31'),
    ('ACHETEUR_CONFIRME', '2022-11-01', '2023-06-30'),
    ('PREMIERE_ACQUISITION', '2023-02-15', '2023-11-15');


INSERT INTO critere_bien (ref_acquereur,nombre_piece, surface_minimum)
VALUES
    (1, 3, 100.0),
    (2, 4, 150.5),
    (3, 2, 80.0);