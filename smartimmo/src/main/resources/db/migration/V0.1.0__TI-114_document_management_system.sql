CREATE TABLE IF NOT EXISTS folder
(
    id_folder        SERIAL PRIMARY KEY,
    document_id      VARCHAR(255),
    name             VARCHAR(255),
    web_content_link VARCHAR(255),
    web_link         VARCHAR(255),
    fk_parent        INT,
    FOREIGN KEY (fk_parent) REFERENCES folder (id_folder)
);

CREATE TABLE IF NOT EXISTS file
(
    id_file          SERIAL PRIMARY KEY,
    document_id      VARCHAR(255),
    name             VARCHAR(255),
    web_content_link VARCHAR(255),
    web_link         VARCHAR(255),
    fk_parent        INT,
    fk_owner         INT,
    FOREIGN KEY (fk_parent) REFERENCES folder (id_folder),
    FOREIGN KEY (fk_owner) REFERENCES prospect (id_prospect)
);

INSERT INTO folder (id_folder, name)
values (0, 'My Drive');
