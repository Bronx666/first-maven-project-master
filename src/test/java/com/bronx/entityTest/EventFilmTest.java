package com.bronx.entityTest;

import com.bronx.entity.EventFilm;
import com.bronx.entity.Hall;
import com.bronx.testUtil.GettersEntityUtil;
import com.bronx.util.HibernateUtil;
import lombok.Cleanup;
import org.hibernate.Session;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class EventFilmTest extends GettersEntityUtil{



    @BeforeAll
    static void getSession() {
        sessionFactory = HibernateUtil.buildSessionFactory();
    }

    @Test
    void checkReturnHall() {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();


        session.save(user);
        session.save(film);
        session.save(cinema);
        session.save(hall);
        session.save(eventFilm);
        session.save(ticket);

        session.flush();
        session.clear();

        assertNotNull(session.get(Hall.class, 1L));

        session.getTransaction().commit();
    }

    @Test
    void checkCreateEventFilm() {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(user);
        session.save(film);
        session.save(cinema);
        session.save(hall);
        session.save(eventFilm);
        session.save(ticket);

        assertNotNull(session.get(EventFilm.class, 1L));
        session.getTransaction().commit();
    }

    @Test
    @Disabled
    //StackOverFlow
    void checkDeleteEventFilm() {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(user);
        session.save(film);
        session.save(cinema);
        session.save(hall);
        session.save(eventFilm);
        session.save(ticket);

        session.delete(eventFilm);

        session.clear();

        assertNull(session.get(EventFilm.class, 1L));
        session.getTransaction().commit();
    }

    @Test
    void updateFreeSeats() {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(user);
        session.save(film);
        session.save(cinema);
        session.save(hall);
        session.save(eventFilm);
        session.save(ticket);

        eventFilm.setFreeSeats(7);
        session.flush();
        session.clear();

        assertEquals(session.get(EventFilm.class, 1L).getFreeSeats(), 39);
    }
}
