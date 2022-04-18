package com.bronx.entityTest;

import com.bronx.entity.LevelAccess;
import com.bronx.entity.User;
import lombok.Cleanup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserTest {


    @Test
    void checkReturnUser() {
        Configuration config = new Configuration();
        config.configure();

        @Cleanup SessionFactory sessionFactory = config.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        var user = session.get(User.class, 6L);
        System.out.println(user.toString());

        session.getTransaction();
    }

    @Test
    void checkCreateUser() {
        User user = User.builder()
                .username("Alex")
                .password("1111")
                .levelAccess(LevelAccess.USER)
                .build();

        Configuration config = new Configuration();
        config.configure();

        @Cleanup SessionFactory sessionFactory = config.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(user);

        session.getTransaction().commit();
    }

    @Test
    void checkDeleteUser() {
        User user = User.builder()
                .username("Alex")
                .password("1111")
                .levelAccess(LevelAccess.USER)
                .build();

        Configuration config = new Configuration();
        config.configure();

        try (SessionFactory sessionFactory = config.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            session.save(user);

            user = session.load(User.class, user.getId());

            session.delete(user);

            session.getTransaction().commit();
            System.out.println("deleted");

        } catch (Exception exception) {
            System.out.println("not deleted");
        }

    }


}
