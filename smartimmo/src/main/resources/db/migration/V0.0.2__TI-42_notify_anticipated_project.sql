CREATE TABLE IF NOT EXISTS project
(
    id_project SERIAL,
    PRIMARY KEY (id_project)
);

CREATE TABLE IF NOT EXISTS potential_project
(
    id_potential_project SERIAL,
    due_date             DATE,
    fk_project           INTEGER,
    priority             VARCHAR(255),
    PRIMARY KEY (id_potential_project),
    FOREIGN KEY (fk_project) REFERENCES project (id_project)
);

CREATE TABLE IF NOT EXISTS notification
(
    id_notification      SERIAL,
    status               VARCHAR(255),
    message              VARCHAR(255),
    priority             VARCHAR(255),
    fk_potential_project INTEGER,
    PRIMARY KEY (id_notification),
    FOREIGN KEY (fk_potential_project) REFERENCES potential_project (id_potential_project)
);

CREATE TABLE IF NOT EXISTS event_type_notification
(
    id_event_type_notification SERIAL,
    event_type                 VARCHAR(255),
    fk_notification            INTEGER,
    PRIMARY KEY (id_event_type_notification),
    FOREIGN KEY (fk_notification) REFERENCES notification (id_notification)
);

CREATE TABLE IF NOT EXISTS subscription
(
    id_subscription SERIAL,
    event_type      VARCHAR(255),
    event_listener  VARCHAR(255),
    PRIMARY KEY (id_subscription)
);

