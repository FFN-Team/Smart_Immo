CREATE TABLE IF NOT EXISTS Visit (
   visit_id SERIAL PRIMARY KEY,
   fk_buyer INT,
   fk_property INT,
   visit_date DATE,
   visit_type VARCHAR(255),
   comments TEXT,
   FOREIGN KEY (fk_buyer) REFERENCES buyer (id_buyer),
   FOREIGN KEY (fk_property) REFERENCES property (id_property)
);