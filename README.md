CREATE TABLE users
(
    id          BIGSERIAL PRIMARY KEY,
    userName    VARCHAR(128),
    password    VARCHAR(128),
    levelAccess VARCHAR(128)
);

CREATE TABLE cinema
(
id          BIGSERIAL PRIMARY KEY,
name        VARCHAR(128),
description VARCHAR(128)
);

CREATE TABLE ticket
(
id           BIGSERIAL PRIMARY KEY,
user_id      INT,
eventFilm_id INT,
ticketNumber INT
);

CREATE TABLE film
(
id          BIGSERIAL PRIMARY KEY,
name        VARCHAR(128),
description VARCHAR(128),
ticketCost  FLOAT,
duration    INT
);

CREATE TABLE hall
(
id            BIGSERIAL PRIMARY KEY,
name          VARCHAR(128),
description   VARCHAR(128),
cinema_id     INT,
amountOfSeats INT
);

CREATE TABLE eventFilm
(
id        BIGSERIAL PRIMARY KEY,
date      TIMESTAMP,
hall_id   INT,
film_id   INT,
freeSeats INT
);


DROP TABLE cinema;
DROP TABLE eventFilm;
DROP TABLE ticket;
DROP TABLE hall;
DROP TABLE film;
DROP TABLE users;

DROP TABLE
    cinema,
    eventFilm,
    ticket,
    hall,
    film,
    users;
