ALTER TABLE users
    ADD CONSTRAINT uq_users_email UNIQUE(email);

ALTER TABLE roles
    ADD CONSTRAINT uq_roles_name UNIQUE(name);

ALTER TABLE airport
    ADD CONSTRAINT uq_airport_code UNIQUE(code);

CREATE INDEX idx_flights_origin ON flights(origin_airport_id);
CREATE INDEX idx_flights_destination ON flights(destination_airport_id);
