CREATE TABLE IF NOT EXISTS project
(
    project_id SERIAL,
    PRIMARY KEY (project_id)
);

CREATE TABLE IF NOT EXISTS potential_project
(
    potential_project_id SERIAL,
    due_date             DATE,
    project_id           INTEGER,
    priority             VARCHAR(255),
    PRIMARY KEY (potential_project_id),
    FOREIGN KEY (project_id) REFERENCES project (project_id)
);

CREATE TABLE IF NOT EXISTS notification
(
    notification_id      SERIAL,
    state                VARCHAR(255),
    message              VARCHAR(255),
    priority             VARCHAR(255),
    potential_project_id INTEGER,
    PRIMARY KEY (notification_id),
    FOREIGN KEY (potential_project_id) REFERENCES potential_project (potential_project_id)
);

CREATE TABLE IF NOT EXISTS event_type_notification
(
    event_type_notification_id SERIAL,
    event_type                 VARCHAR(255),
    notification_id            INTEGER,
    PRIMARY KEY (event_type_notification_id),
    FOREIGN KEY (notification_id) REFERENCES notification (notification_id)
);

CREATE TABLE IF NOT EXISTS subscription
(
    subscription_id SERIAL,
    event_type      VARCHAR(255),
    event_listener  VARCHAR(255),
    PRIMARY KEY (subscription_id)
);

