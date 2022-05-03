package com.bronx.entityTest;

import com.bronx.entity.User;
import com.bronx.testUtil.GettersEntityUtil;
import com.bronx.util.HibernateUtil;
import lombok.Cleanup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class UserTest extends GettersEntityUtil {


    @BeforeAll
    static void getSession() {
        sessionFactory = HibernateUtil.buildSessionFactory();
    }

    @AfterAll
    static void close(){
        sessionFactory.close();
    }

    @Test
    void checkReturnUser() {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        var user = getUser();
        session.save(user);

        assertNotNull(session.get(User.class, 1L));

        session.getTransaction().rollback();
    }

    @Test
    void checkCreateUser() {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        var user = getUser();
        session.save(user);

        assertNotNull(session.get(User.class, 1L));
        session.getTransaction().rollback();
    }

    @Test
    void checkDeleteUser() {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(user);
        var user = session.get(User.class, 1L);
        session.remove(user);

        session.flush();


        assertNull(session.get(User.class, 1L));
        session.getTransaction().rollback();

    }

    @Test
    void updateUserUsername() {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(user);
        var user = session.get(User.class, 1L);
        user.setUsername("NotAlex");

        session.flush();
        session.clear();

        assertEquals(session.get(User.class, 1L).getUsername(), "NotAlex");
        session.getTransaction().rollback();
    }

}
