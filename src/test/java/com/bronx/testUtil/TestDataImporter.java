package com.bronx.testUtil;

import com.bronx.entity.Cinema;
import com.bronx.entity.EventFilm;
import com.bronx.entity.Film;
import com.bronx.entity.Hall;
import com.bronx.entity.Role;
import com.bronx.entity.Ticket;
import com.bronx.entity.User;
import lombok.Cleanup;
import lombok.experimental.UtilityClass;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.TestInstance;

import java.time.LocalDateTime;

@UtilityClass
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestDataImporter {

     LocalDateTime date = LocalDateTime.of(2022, 6, 25, 13, 24);

    public void importData(SessionFactory sessionFactory) {

        @Cleanup Session session = sessionFactory.openSession();

        User ivan = saveUser(session, "Ivan", "111", Role.USER);
        User andrew = saveUser(session, "Andrew", "123", Role.ADMIN);
        User nick = saveUser(session, "Nick", "qweqwe", Role.SUPER_ADMIN);

        Cinema galaxy = saveCinema(session, "Galaxy", "usual cinema with popcorn and blackjack");
        Cinema helios = saveCinema(session, "Helios", "none");

        //id = 1
        Film morbius = saveFilm(session, "Morbius", "Film about vampires", 20, 134);
        Film viking = saveFilm(session, "Viking", "Film about vikings", 15, 124);
        //3
        Film stranger = saveFilm(session,"Stranger", "Film about stranger", 25, 144);

        //id = 1
        Hall galaxyFirstHall = saveHall(session,
                "first galaxy hall",
                "This hale have 40 seats",
                40,
                galaxy);
        Hall galaxySecondHall = saveHall(session,
                "second galaxy hall",
                "This hale have 35 seats",
                35,
                galaxy);
        Hall heliosFirstHall = saveHall(session,
                "first helios hall",
                "This hale have 50 seats",
                50,
                helios);


        EventFilm strangerInGalaxyOnFirstHall = saveEventFilm(session, galaxyFirstHall, stranger, date);
        EventFilm morbiusInGalaxyOnSecondHall = saveEventFilm(session, galaxySecondHall, morbius, date);
        EventFilm vikingInHeliosOnFirstHall = saveEventFilm(session, heliosFirstHall, viking, date);

        Ticket ivanTicket1 = saveTicket(session, ivan, strangerInGalaxyOnFirstHall);
        Ticket andrewTicket1 = saveTicket(session, andrew, morbiusInGalaxyOnSecondHall);
        Ticket nickTicket1 = saveTicket(session, nick, vikingInHeliosOnFirstHall);

    }

    public  User saveUser(Session session,
                                String username,
                                String password,
                                Role role) {
        User user = User.builder()
                .username(username)
                .password(password)
                .role(role)
                .build();
        session.save(user);
        return user;
    }

    public Film saveFilm(Session session,
                                String name,
                                String description,
                                float ticketCost,
                                int duration) {

        Film film = Film.builder()
                .name(name)
                .description(description)
                .ticketCost(ticketCost)
                .duration(duration)
                .build();
        session.save(film);
        return film;
    }

    public  Cinema saveCinema(Session session,
                                    String name,
                                    String description) {
        Cinema cinema = Cinema.builder()
                .name(name)
                .description(description)
                .build();
        session.save(cinema);
        return cinema;
    }

    public  Ticket saveTicket(Session session,
                                    User user,
                                    EventFilm eventFilm) {

//        eventFilm.setFreeSeats(eventFilm.getFreeSeats() - 1);
//        session.update(eventFilm);
//        session.flush();

        Ticket ticket = Ticket.builder()
                .user(user)
                .eventFilm(eventFilm)
                .ticketNumber(eventFilm.getFreeSeats())
                .build();

        session.save(ticket);

        return ticket;
    }

    public  Hall saveHall(Session session,
                                String name,
                                String description,
                                int amountOfSeats,
                                Cinema cinema) {
        Hall hall = Hall.builder()
                .name(name)
                .description(description)
                .amountOfSeats(amountOfSeats)
                .cinema(cinema)
                .build();

        session.save(hall);

        return hall;
    }

    public EventFilm saveEventFilm(Session session,
                                          Hall hall,
                                          Film film,
                                          LocalDateTime date) {
        EventFilm eventFilm = EventFilm.builder()
                .hall(hall)
                .film(film)
                .date(date)
                .freeSeats(hall.getAmountOfSeats())
                .build();

        session.save(eventFilm);
        return eventFilm;
    }
}

