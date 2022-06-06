package com.bronx.entityTest;

import com.bronx.entity.Cinema;
import com.bronx.testUtil.GettersEntityUtil;
import com.bronx.util.HibernateUtil;
import lombok.Cleanup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CinemaTest {
    @Test
    void checkReturnCinema() {
        @Cleanup SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(GettersEntityUtil.getCinema());

        var cinema = session.get(Cinema.class, 1L);
        assertNotNull(cinema);

        session.getTransaction().commit();
    }

    @Test
    void checkCreateCinema() {
        @Cleanup SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(GettersEntityUtil.getCinema());

        assertNotNull(session.get(Cinema.class, 1L));

        session.getTransaction().commit();
    }

    @Test
    void checkDeleteCinema() {
        @Cleanup SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(GettersEntityUtil.getCinema());
        var cinema = session.get(Cinema.class, 1L);
        session.delete(cinema);

        session.flush();

        assertNull(session.get(Cinema.class, 1L));
    }

    @Test
    void updateCinemaName() {
        @Cleanup SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(GettersEntityUtil.getCinema());
        var cinema = session.get(Cinema.class, 1L);
        cinema.setName("AnotherName");
        session.flush();

        assertEquals(session.get(Cinema.class, 1L).getName(), "AnotherName");
    }
}
