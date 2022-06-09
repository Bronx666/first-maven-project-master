--liquibase formatred sql

--changeset bronx:1
CREATE TABLE users
(
    id       BIGSERIAL PRIMARY KEY,
    username VARCHAR(128),
    password VARCHAR(128),
    role     VARCHAR(128)
);

--changeset bronx:2
CREATE TABLE cinema
(
    id          BIGSERIAL PRIMARY KEY,
    name        VARCHAR(128),
    description VARCHAR(128)
);

--changeset bronx:3
CREATE TABLE ticket
(
    id            BIGSERIAL PRIMARY KEY,
    user_id       BIGINT,
    event_film_id BIGINT,
    ticket_number INT
);

--changeset bronx:4
CREATE TABLE film
(
    id          BIGSERIAL PRIMARY KEY,
    name        VARCHAR(128),
    description VARCHAR(128),
    ticket_cost FLOAT,
    duration    INT
);

--changeset bronx:5
CREATE TABLE hall
(
    id              BIGSERIAL PRIMARY KEY,
    name            VARCHAR(128),
    description     VARCHAR(128),
    cinema_id       BIGINT,
    amount_of_seats INT
);

--changeset bronx:6
CREATE TABLE event_film
(
    id         BIGSERIAL PRIMARY KEY,
    date       TIMESTAMP,
    hall_id    BIGINT,
    film_id    BIGINT,
    free_seats INT
);
