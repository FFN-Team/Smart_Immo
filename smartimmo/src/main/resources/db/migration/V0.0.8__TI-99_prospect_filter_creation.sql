 CREATE TABLE IF NOT EXISTS prospect_filter (
     id_prospect_filter SERIAL PRIMARY KEY,
     prospect_filter_name VARCHAR(255),
     contact_origin VARCHAR(255),
     title VARCHAR(255),
     age_comparator VARCHAR(255),
     age INT,
     profession VARCHAR(255),
     authorize_contact_on_social_media BOOLEAN
);
