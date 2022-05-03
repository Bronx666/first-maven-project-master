package com.bronx.entityTest;

import com.bronx.entity.Cinema;
import com.bronx.entity.EventFilm;
import com.bronx.entity.Film;
import com.bronx.entity.Hall;
import com.bronx.entity.Ticket;
import com.bronx.entity.User;
import com.bronx.testUtil.GettersEntityUtil;
import com.bronx.util.HibernateUtil;
import lombok.Cleanup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class FilmTest {

    static User user = GettersEntityUtil.getUser();
    static Film film = GettersEntityUtil.getFilm();
    static Cinema cinema = GettersEntityUtil.getCinema();
    static Hall hall = GettersEntityUtil.getHall(cinema);
    static EventFilm eventFilm = GettersEntityUtil.getEventFilm(hall, film);
    static Ticket ticket = GettersEntityUtil.getTicket(user, eventFilm);

    @Test
    void checkReturnFilm() {
        @Cleanup SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(GettersEntityUtil.getFilm());

        var film = session.get(Film.class, 1L);
        assertNotNull(film);

        session.getTransaction().commit();
    }

    @Test
    void checkCreateFilm() {
        @Cleanup SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(GettersEntityUtil.getFilm());
        assertNotNull(session.get(Film.class, 1L));
        session.getTransaction().commit();
    }

    @Test
    void checkDeleteFilm() {
        @Cleanup SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(GettersEntityUtil.getFilm());
        var film = session.get(Film.class, 1L);
        session.delete(film);

        session.flush();

        assertNull(session.get(Film.class, 1L));

        session.getTransaction().commit();
    }

    @Test
    void updateFilmName() {
        @Cleanup SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(GettersEntityUtil.getFilm());
        var film = session.get(Film.class, 1L);
        film.setName("Another name");
        session.flush();

        assertEquals(session.get(Film.class, 1L).getName(), "Another name");
    }
}
