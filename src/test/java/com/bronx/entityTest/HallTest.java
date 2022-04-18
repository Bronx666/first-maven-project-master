package com.bronx.entityTest;

import com.bronx.entity.Hall;
import com.bronx.entity.User;
import lombok.Cleanup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.Test;

public class HallTest {

    @Test
    void checkReturnHall() {
        Configuration config = new Configuration();
        config.configure();

        @Cleanup SessionFactory sessionFactory = config.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        var hall = session.get(Hall.class, 6L);
        System.out.println(hall.toString());

        session.getTransaction();
    }
}
