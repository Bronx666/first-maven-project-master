package com.bronx.entityTest;

import com.bronx.entity.*;
import com.bronx.testUtil.GettersEntityUtil;
import com.bronx.util.HibernateUtil;
import lombok.Cleanup;
import org.hibernate.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TicketTest extends GettersEntityUtil {


    @BeforeAll
    static void getSession() {
        sessionFactory = HibernateUtil.buildSessionFactory();
    }


    @Test
    void checkReturnTicket() {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.flush();

        assertNotNull(session.get(Ticket.class, 1L));

        session.getTransaction().rollback();
    }

    @Test
    void checkCreateTicket() {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        assertNotNull(session.get(Ticket.class, 1L));
        session.getTransaction().rollback();
    }

    @Test
    void checkDeleteTicket() {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(user);
        session.save(film);
        session.save(cinema);
        session.save(hall);
        session.save(eventFilm);
        session.save(ticket);

        session.delete(ticket);

        session.flush();
        session.clear();

        assertNotNull(session.get(User.class, 1L));
        assertNull(session.get(Ticket.class, 1L));
        session.getTransaction().rollback();
    }

    @Test
    void updateTicketNumber() {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(user);
        session.save(film);
        session.save(cinema);
        session.save(hall);
        session.save(eventFilm);
        session.save(ticket);

        ticket.setTicketNumber(3);
        session.flush();
        session.clear();

        assertEquals(session.get(Ticket.class, 1L).getTicketNumber(), 3);
        session.getTransaction().rollback();
    }


}
