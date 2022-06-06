-- USERS
INSERT INTO public.users (id, username, password, role)
VALUES (1, 'Ivan', '111', 'USER');
INSERT INTO public.users (id, username, password, role)
VALUES (2, 'Andrew', '123', 'ADMIN');
INSERT INTO public.users (id, username, password, role)
VALUES (3, 'Nick', 'qweqwe', 'SUPER_ADMIN');
-- CINEMAS
INSERT INTO public.cinema (id, name, description)
VALUES (1, 'Galaxy', 'usual cinema with popcorn and blackjack');
INSERT INTO public.cinema (id, name, description)
VALUES (2, 'Helios', 'none');
-- TICKETS
INSERT INTO public.ticket (id, user_id, event_film_id, ticket_number)
VALUES (1, 1, 1, 5);
INSERT INTO public.ticket (id, user_id, event_film_id, ticket_number)
VALUES (2, 2, 2, 6);
INSERT INTO public.ticket (id, user_id, event_film_id, ticket_number)
VALUES (3, 3, 3, 7);
-- HALLS
INSERT INTO public.hall (id, name, description, cinema_id, amount_of_seats)
VALUES (1, 'first galaxy hall', 'This hale have 40 seats', 1, 40);
INSERT INTO public.hall (id, name, description, cinema_id, amount_of_seats)
VALUES (2, 'second galaxy hall', 'This hale have 35 seats', 1, 35);
INSERT INTO public.hall (id, name, description, cinema_id, amount_of_seats)
VALUES (3, 'first helios hall', 'This hale have 50 seats', 2, 50);
-- FILMS
INSERT INTO public.film (id, name, description, ticket_cost, duration)
VALUES (1, 'Morbius', 'Film about vampires', 20, 134);
INSERT INTO public.film (id, name, description, ticket_cost, duration)
VALUES (2, 'Viking', 'Film about vikings', 15, 124);
INSERT INTO public.film (id, name, description, ticket_cost, duration)
VALUES (3, 'Stranger', 'Film about stranger', 25, 144);
-- EVENTFILM
INSERT INTO public.event_film (id, date, hall_id, film_id, free_seats)
VALUES (1, '2022-06-25 13:24:00.000000', 1, 3, 40);
INSERT INTO public.event_film (id, date, hall_id, film_id, free_seats)
VALUES (2, '2022-06-25 13:24:00.000000', 2, 1, 35);
INSERT INTO public.event_film (id, date, hall_id, film_id, free_seats)
VALUES (3, '2022-06-25 13:24:00.000000', 3, 2, 50);


