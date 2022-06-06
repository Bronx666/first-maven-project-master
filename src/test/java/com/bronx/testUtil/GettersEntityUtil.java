package com.bronx.testUtil;

import com.bronx.entity.Cinema;
import com.bronx.entity.EventFilm;
import com.bronx.entity.Film;
import com.bronx.entity.Hall;
import com.bronx.entity.Role;
import com.bronx.entity.Ticket;
import com.bronx.entity.User;
import org.hibernate.SessionFactory;

import java.time.LocalDateTime;

public class GettersEntityUtil {

    public static User user = GettersEntityUtil.getUser();
    public static Film film = GettersEntityUtil.getFilm();
    public static Cinema cinema = GettersEntityUtil.getCinema();
    public static Hall hall = GettersEntityUtil.getHall(cinema);
    public static EventFilm eventFilm = GettersEntityUtil.getEventFilm(hall, film);
    public static Ticket ticket = GettersEntityUtil.getTicket(user, eventFilm);

    public static SessionFactory sessionFactory;

    static LocalDateTime date = LocalDateTime.of(2022, 6, 25, 13, 24);

    public static User getUser() {
        return User.builder()
                .username("Alex")
                .password("1111")
                .role(Role.USER)
                .build();
    }

    public static Film getFilm() {
        return Film.builder()
                .name("name")
                .description("description")
                .ticketCost(24)
                .duration(123)
                .build();
    }

    public static Cinema getCinema() {
        return Cinema.builder()
                .name("nameCinema")
                .description("description")
                .build();

    }

    public static Ticket getTicket(User user, EventFilm eventFilm) {

        eventFilm.setFreeSeats(eventFilm.getFreeSeats() - 1);
        return Ticket.builder()
                .user(user)
                .eventFilm(eventFilm)
                .ticketNumber(eventFilm.getFreeSeats())
                .build();
    }

    public static Hall getHall(Cinema cinema) {
        return Hall.builder()
                .name("nameHall")
                .description("descriptionHall")
                .amountOfSeats(40)
                .cinema(cinema)
                .build();
    }

    public static EventFilm getEventFilm(Hall hall, Film film) {
        return EventFilm.builder()
                .hall(hall)
                .film(film)
                .date(date)
                .freeSeats(hall.getAmountOfSeats())
                .build();
    }
}
