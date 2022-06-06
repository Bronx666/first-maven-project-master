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
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class HallTest extends GettersEntityUtil {


    static SessionFactory sessionFactory;

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
    void checkCreateHall() {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(user);
        session.save(film);
        session.save(cinema);
        session.save(hall);
        session.save(eventFilm);
        session.save(ticket);

        assertNotNull(session.get(Hall.class, 1L));
        session.getTransaction().commit();
    }

    @Test
    @Disabled
    void checkDeleteHall() {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(user);
        session.save(film);
        session.save(cinema);
        session.save(hall);
        session.save(eventFilm);
        session.save(ticket);


        session.delete(hall);

        session.flush();
        session.clear();

        assertNotNull(session.get(User.class, 1L));
        assertNotNull(session.get(Cinema.class, 1L));
        assertNull(session.get(Hall.class, 1L));

        session.getTransaction().commit();
    }

    @Test
    void updateHallName() {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(user);
        session.save(film);
        session.save(cinema);
        session.save(hall);
        session.save(eventFilm);
        session.save(ticket);

        hall.setName("Another name");
        session.flush();
        session.clear();

        assertEquals(session.get(Hall.class, 1L).getName(), "Another name");
        session.getTransaction().commit();
    }
}
