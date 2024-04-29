ALTER TABLE project
    ADD COLUMN fk_prospect INT,
    ADD CONSTRAINT fk_project_prospect FOREIGN KEY (fk_prospect) REFERENCES prospect (id_prospect);

ALTER TABLE notification
    ADD COLUMN type VARCHAR(255);

-- Insertion du prospect
INSERT INTO project (fk_prospect)
VALUES ((SELECT id_prospect FROM prospect WHERE last_name = 'Durand' AND first_name = 'Marie' LIMIT 1));

-- Insertion du projet potentiel
INSERT INTO potential_project (due_date, fk_project, priority, notification_date)
VALUES ('2025-02-02', currval('project_id_project_seq'), 'HIGH', NOW());

-- Insertion du statut matrimonial
INSERT INTO home (marital_status)
VALUES ('COHABITATION');

-- Mise à jour du prospect pour ajouter la clé étrangère vers le domicile
UPDATE prospect
SET fk_home = currval('home_id_home_seq')
WHERE last_name = 'Dupont'
  AND first_name = 'Jean';

-- Insertion du propriétaire
INSERT INTO owner (active, fk_prospect)
VALUES (TRUE, (SELECT id_prospect FROM prospect WHERE last_name = 'Dupont' AND first_name = 'Jean' LIMIT 1));

-- Insertion du propriétaire de la propriété
INSERT INTO property_owner (acquisition_date, main, fk_owner)
VALUES ('2015-06-06', TRUE, currval('owner_id_owner_seq'));
