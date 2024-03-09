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
    fk_prospect      INT,
    fk_property      INT,
    created          DATE,
    FOREIGN KEY (fk_parent) REFERENCES folder (id_folder),
    FOREIGN KEY (fk_prospect) REFERENCES prospect (id_prospect),
    FOREIGN KEY (fk_property) REFERENCES property (id_property)
);

INSERT INTO folder (id_folder, name)
values (0, 'My Drive');
