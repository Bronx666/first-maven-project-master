package com.bronx.entityTest;

import com.bronx.entity.*;
import lombok.Cleanup;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.Test;

public class TicketTest {

    @Test
    void checkReturnTicket() {
        Configuration config = new Configuration();
        config.configure();

        @Cleanup SessionFactory sessionFactory = config.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        var ticket = session.get(Ticket.class, 1L);
        System.out.println(ticket.toString());

        session.getTransaction();
    }




}
