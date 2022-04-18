package com.bronx;

import com.bronx.entity.*;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import java.time.LocalDateTime;

public class HibernateRunner {

    public static void main(String[] args) {

        LocalDateTime date = LocalDateTime.of( 2022, 6, 25, 13, 24);

        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");


        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            User user = User.builder()
                    .username("Alex")
                    .password("1111")
                    .levelAccess(LevelAccess.USER)
                    .build();

            Cinema cinema = Cinema.builder()
                    .name("ProjectCinema")
                    .description("Cinema with one hall")
                    .build();

            Hall hall = Hall.builder()
                    .name("First")
                    .description("fine hall")
                    .amountOfSeats(40)
                    .cinema(cinema)
                    .build();

            Film film = Film.builder()
                    .name("Morbius")
                    .description("About vampire")
                    .ticketCost(14.5F)
                    .duration(145)
                    .build();

            EventFilm eventFilm = EventFilm.builder()
                    .hall(hall)
                    .film(film)
                    .date(date)
                    .freeSeats(40)
                    .build();

            Ticket ticket = Ticket.builder()
                    .user(user)
                    .eventFilm(eventFilm)
                    .ticketNumber(hall.getAmountOfSeats() - eventFilm.getFreeSeats())//КОСТЫЛЬ!
                    .build();

            session.save(user);
//            session.save(cinema);
//            session.save(hall);
//            session.save(film);
//            session.save(eventFilm);
//            session.save(ticket);

            session.getTransaction().commit();
        }


    }
}

