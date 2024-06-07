CREATE TABLE IF NOT EXISTS property_to_follow
(
    id_property_to_follow SERIAL PRIMARY KEY,
    fk_buyer              INTEGER,
    fk_property           INTEGER,
    status                VARCHAR(255),
    FOREIGN KEY (fk_buyer) REFERENCES buyer (id_buyer),
    FOREIGN KEY (fk_property) REFERENCES property (id_property)
);