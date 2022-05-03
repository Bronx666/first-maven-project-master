package com.bronx.daoTest;

import com.bronx.repository.EventFilmRepository;
import com.bronx.dto.EventFilter;
import com.bronx.entity.EventFilm;
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

public class EventFilmRepositoryTest {


    static private final SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
    static private Session session;

    static private EventFilmRepository eventFilmRepository;

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
        eventFilmRepository = new EventFilmRepository(session);

        List<EventFilm> results = eventFilmRepository.findAll();
        assertThat(results).hasSize(3);

        List<Integer> fullNames = results.stream().map(EventFilm::getFreeSeats).collect(toList());
        assertThat(fullNames).containsExactlyInAnyOrder(40, 35, 50);

        session.getTransaction().rollback();
    }

    @Test
    void findEventFilmById() {

        session.beginTransaction();
        eventFilmRepository = new EventFilmRepository(session);

        Optional<EventFilm> result = eventFilmRepository.findById(1L);
        assertEquals(result.get().getId(), 1L);

        session.getTransaction().rollback();
    }

    @Test
    void saveEventFilmAndReturnWithId() {

        session.beginTransaction();
        var eventFilm = GettersEntityUtil.getEventFilm(
                GettersEntityUtil.getHall(GettersEntityUtil.getCinema()),
                GettersEntityUtil.getFilm());
        eventFilmRepository = new EventFilmRepository(session);

        eventFilm = eventFilmRepository.save(eventFilm);

        assertThat(eventFilm.getId()).isEqualTo(4L);

        session.getTransaction().rollback();
    }

    @Test
    void deleteEventFilm() {
        session.beginTransaction();
        eventFilmRepository = new EventFilmRepository(session);

        eventFilmRepository.delete(1L);
        assertNull(session.get(EventFilm.class, 1L));

        session.getTransaction().rollback();
    }

    @Test
    void checkUpdateUsername() {
        session.beginTransaction();
        eventFilmRepository = new EventFilmRepository(session);

        var eventFilm = session.get(EventFilm.class, 1L);
        eventFilm.setFreeSeats(34);
        eventFilmRepository.update(eventFilm);

        assertEquals(session.get(EventFilm.class, 1L).getFreeSeats(), 34);

        session.getTransaction().rollback();
    }

    @Test
    void findEventFromSpecifiedFilmIdAndHallId() {
        session.beginTransaction();
        eventFilmRepository = new EventFilmRepository(session);

        EventFilter filter = EventFilter.builder()
                .filmId(3L)
                .HallId(1L)
                .build();

        var result = eventFilmRepository.findEventByFilmAndHall(filter);
        assertThat(result).hasSize(1);

        System.out.println(result.toString());

        List<Integer> amountFreeSeats = result.stream().map(EventFilm::getFreeSeats).toList();
        assertThat(amountFreeSeats).containsExactlyInAnyOrder(40);
        assertThat(result.get(0).getId()).isEqualTo(1L);

        session.getTransaction().rollback();
    }
}
