package com.bronx.daoTest;

import com.bronx.entity.Ticket;
import com.bronx.repository.TicketRepository;
import com.bronx.testUtil.GettersEntityUtil;
import com.bronx.testUtil.TestDataImporter;
import com.bronx.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TicketRepositoryTest {


    static private final SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
    static private Session session;

    static private TicketRepository ticketRepository;

    @BeforeAll
    static void init() {
        TestDataImporter.importData(sessionFactory);
        session = (Session) Proxy.newProxyInstance(SessionFactory.class.getClassLoader(),
                new Class[]{Session.class},
                (proxy, method, args1) -> method.invoke(sessionFactory.getCurrentSession(), args1));
    }

    @AfterAll
    static void close() {
        sessionFactory.close();
    }

    @Test
    void findAll() {
        session.beginTransaction();
        ticketRepository = new TicketRepository(session);

        List<Ticket> results = ticketRepository.findAll();
        assertThat(results).hasSize(3);

        List<Integer> fullNames = results.stream().map(Ticket::getTicketNumber).collect(toList());
        assertThat(fullNames).containsExactlyInAnyOrder(40, 35, 50);

        session.getTransaction().rollback();
    }

    @Test
    void findTicketById() {

        session.beginTransaction();
        ticketRepository = new TicketRepository(session);

        Optional<Ticket> result = ticketRepository.findById(1L);
        assertEquals(result.get().getTicketNumber(), 40);

        session.getTransaction().rollback();
    }

    @Test
    void saveTicketAndReturnWithId() {

        session.beginTransaction();
        ticketRepository = new TicketRepository(session);
        var ticket = GettersEntityUtil
                .getTicket(GettersEntityUtil.getUser(),
                        GettersEntityUtil.getEventFilm(GettersEntityUtil
                                        .getHall(GettersEntityUtil.getCinema()),
                                GettersEntityUtil.getFilm()));

        ticket = ticketRepository.save(ticket);

        assertThat(ticket.getId()).isEqualTo(4L);

        session.getTransaction().rollback();
    }

    @Test
    void deleteTicket() {
        session.beginTransaction();
        ticketRepository = new TicketRepository(session);

        ticketRepository.delete(1L);
        assertNull(session.get(Ticket.class, 1L));

        session.getTransaction().rollback();
    }

    @Test
    void checkUpdateUsername() {
        session.beginTransaction();
        ticketRepository = new TicketRepository(session);

        var ticket = session.get(Ticket.class, 1L);
        ticket.setTicketNumber(34);
        ticketRepository.update(ticket);

        assertEquals(session.get(Ticket.class, 1L).getTicketNumber(), 34);

        session.getTransaction().rollback();
    }
}
