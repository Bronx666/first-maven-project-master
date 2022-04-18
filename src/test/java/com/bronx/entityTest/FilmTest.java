package com.bronx.entityTest;

import com.bronx.entity.Film;
import lombok.Cleanup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.Test;

public class FilmTest {
    @Test
    void checkReturnFilm() {
        Configuration config = new Configuration();
        config.configure();

        @Cleanup SessionFactory sessionFactory = config.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        var film = session.get(Film.class, 1L   );
        System.out.println(film.toString());

        session.getTransaction();
    }
}
