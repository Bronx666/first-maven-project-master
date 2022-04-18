package com.bronx.entityTest;

import com.bronx.entity.Cinema;
import lombok.Cleanup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.Test;

public class CinemaTest {
    @Test
    void checkReturnEventCinema() {
        Configuration config = new Configuration();
        config.configure();

        @Cleanup SessionFactory sessionFactory = config.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        var cinema = session.get(Cinema.class, 1L);
        System.out.println(cinema.toString());

        session.getTransaction();
    }
}
