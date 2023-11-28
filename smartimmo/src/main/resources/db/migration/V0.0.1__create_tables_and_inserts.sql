CREATE TABLE IF NOT EXISTS bien
(
    bien_id     SERIAL,
    nom_bien    VARCHAR(255),
    description VARCHAR(255),
    PRIMARY KEY (bien_id)
);

CREATE TABLE IF NOT EXISTS proprietaire
(
    proprietaire_id  SERIAL,
    nom_proprietaire VARCHAR(255),
    bien_id          INT,
    PRIMARY KEY (proprietaire_id),
    FOREIGN KEY (bien_id) REFERENCES bien (bien_id)
);

INSERT INTO bien (nom_bien, description)
VALUES ('Maison', 'Belle maison spacieuse avec jardin'),
       ('Appartement', 'Appartement moderne en centre-ville'),
       ('Chalet', 'Chalet rustique avec vue sur la montagne');

INSERT INTO proprietaire (nom_proprietaire, bien_id)
VALUES ('John Doe', 1),
       ('Jane Smith', 2),
       ('Alice Johnson', 3);