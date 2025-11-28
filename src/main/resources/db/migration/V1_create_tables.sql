-- ================
-- USERS
-- ================
CREATE TABLE users (
                       id BIGSERIAL PRIMARY KEY,
                       first_name VARCHAR(255),
                       last_name VARCHAR(255),
                       email VARCHAR(255) UNIQUE NOT NULL,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ================
-- ROLES
-- ================
CREATE TABLE roles (
                       id BIGSERIAL PRIMARY KEY,
                       name VARCHAR(255) UNIQUE NOT NULL
);

-- ================
-- USER_ROLES (Many-to-Many)
-- ================
CREATE TABLE user_roles (
                            user_id BIGINT NOT NULL,
                            role_id BIGINT NOT NULL,
                            PRIMARY KEY (user_id, role_id),
                            FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
                            FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE
);

-- ================
-- OAUTH ACCOUNTS
-- ================
CREATE TABLE oauth_accounts (
                                id BIGSERIAL PRIMARY KEY,
                                user_id BIGINT NOT NULL,
                                provider VARCHAR(255) NOT NULL,
                                provider_user_id VARCHAR(255) NOT NULL,

                                FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- ================
-- AIRCRAFT
-- ================
CREATE TABLE aircraft (
                          id BIGSERIAL PRIMARY KEY,
                          model VARCHAR(255) NOT NULL,
                          capacity INT NOT NULL
);

-- ================
-- AIRPORT
-- ================
CREATE TABLE airport (
                         id BIGSERIAL PRIMARY KEY,
                         code VARCHAR(10) UNIQUE NOT NULL,
                         name VARCHAR(255) NOT NULL,
                         city VARCHAR(255),
                         country VARCHAR(255),
                         latitude NUMERIC(10,6),
                         longitude NUMERIC(10,6)
);

-- ================
-- FLIGHTS
-- ================
CREATE TABLE flights (
                         id BIGSERIAL PRIMARY KEY,
                         flight_number VARCHAR(50) NOT NULL,

                         aircraft_id BIGINT NOT NULL,
                         origin_airport_id BIGINT NOT NULL,
                         destination_airport_id BIGINT NOT NULL,

                         departure_time TIMESTAMP NOT NULL,
                         arrival_time TIMESTAMP NOT NULL,
                         status VARCHAR(50) NOT NULL,

                         FOREIGN KEY (aircraft_id) REFERENCES aircraft(id),
                         FOREIGN KEY (origin_airport_id) REFERENCES airport(id),
                         FOREIGN KEY (destination_airport_id) REFERENCES airport(id)
);

-- ================
-- TICKET
-- ================
CREATE TABLE ticket (
                        id BIGSERIAL PRIMARY KEY,
                        flight_id BIGINT NOT NULL,
                        seat_number VARCHAR(10) NOT NULL,
                        price DECIMAL(10,2) NOT NULL,

                        FOREIGN KEY (flight_id) REFERENCES flights(id) ON DELETE CASCADE
);

-- ================
-- BOOKINGS
-- ================
CREATE TABLE bookings (
                          id BIGSERIAL PRIMARY KEY,
                          user_id BIGINT NOT NULL,
                          ticket_id BIGINT NOT NULL,
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          status VARCHAR(50) NOT NULL,

                          FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
                          FOREIGN KEY (ticket_id) REFERENCES ticket(id) ON DELETE CASCADE
);

-- ================
-- BOOKED_TICKET (Many-to-Many between booking & ticket)
-- ================
CREATE TABLE booked_ticket (
                               id BIGSERIAL PRIMARY KEY,
                               booking_id BIGINT NOT NULL,
                               ticket_id BIGINT NOT NULL,

                               FOREIGN KEY (booking_id) REFERENCES bookings(id) ON DELETE CASCADE,
                               FOREIGN KEY (ticket_id) REFERENCES ticket(id) ON DELETE CASCADE
);

-- ====================
-- INDEXES FOR SPEED
-- ====================
CREATE INDEX idx_flights_aircraft ON flights(aircraft_id);
CREATE INDEX idx_flights_origin ON flights(origin_airport_id);
CREATE INDEX idx_flights_dest ON flights(destination_airport_id);

CREATE INDEX idx_ticket_flight ON ticket(flight_id);

CREATE INDEX idx_booking_user ON bookings(user_id);
CREATE INDEX idx_booking_ticket ON bookings(ticket_id);

CREATE INDEX idx_bookedticket_booking ON booked_ticket(booking_id);
CREATE INDEX idx_bookedticket_ticket ON booked_ticket(ticket_id);
