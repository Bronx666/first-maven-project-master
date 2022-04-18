package com.bronx.entityTest;

import com.bronx.entity.EventFilm;
import com.bronx.entity.Hall;
import lombok.Cleanup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.Test;

public class EventFilmTest {

    @Test
    void checkReturnEventFilm() {
        Configuration config = new Configuration();
        config.configure();

        @Cleanup SessionFactory sessionFactory = config.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        var eventFilm = session.get(EventFilm.class, 1L);
        System.out.println(eventFilm.toString());

        session.getTransaction();
    }
}
