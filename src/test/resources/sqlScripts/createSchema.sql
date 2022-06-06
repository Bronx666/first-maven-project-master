CREATE TABLE users
(
    id       BIGSERIAL PRIMARY KEY,
    username VARCHAR(128),
    password VARCHAR(128),
    role     VARCHAR(128)
);

CREATE TABLE cinema
(
    id          BIGSERIAL PRIMARY KEY,
    name        VARCHAR(128),
    description VARCHAR(128)
);

CREATE TABLE ticket
(
    id            BIGSERIAL PRIMARY KEY,
    user_id       BIGINT,
    event_film_id BIGINT,
    ticket_number INT
);

CREATE TABLE film
(
    id          BIGSERIAL PRIMARY KEY,
    name        VARCHAR(128),
    description VARCHAR(128),
    ticket_cost FLOAT,
    duration    INT
);

CREATE TABLE hall
(
    id              BIGSERIAL PRIMARY KEY,
    name            VARCHAR(128),
    description     VARCHAR(128),
    cinema_id       BIGINT,
    amount_of_seats INT
);

CREATE TABLE event_film
(
    id         BIGSERIAL PRIMARY KEY,
    date       TIMESTAMP,
    hall_id    BIGINT,
    film_id    BIGINT,
    free_seats INT
);
