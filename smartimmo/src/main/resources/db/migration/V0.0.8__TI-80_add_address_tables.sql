ALTER TABLE project
    ADD COLUMN fk_prospect INT,
    ADD CONSTRAINT fk_project_prospect FOREIGN KEY (fk_prospect) REFERENCES prospect (id_prospect);

ALTER TABLE notification
    ADD COLUMN type VARCHAR(255);
