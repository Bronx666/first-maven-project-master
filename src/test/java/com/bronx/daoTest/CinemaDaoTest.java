package com.bronx.daoTest;

import com.bronx.repository.CinemaRepository;
import com.bronx.entity.Cinema;
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

public class CinemaDaoTest {

    static private final SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
    static private Session session;

    static private CinemaRepository cinemaRepository;

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
        cinemaRepository = new CinemaRepository(session);

        List<Cinema> results = cinemaRepository.findAll();
        assertThat(results).hasSize(2);

        List<String> fullNames = results.stream().map(Cinema::getName).collect(toList());
        assertThat(fullNames).containsExactlyInAnyOrder("Galaxy", "Helios");

        session.getTransaction().rollback();
    }

    @Test
    void findCinemaById() {
        session.beginTransaction();
        cinemaRepository = new CinemaRepository(session);

        Optional<Cinema> result = cinemaRepository.findById(1L);
        assertEquals(result.get().getName(), "Galaxy");

        session.getTransaction().rollback();
        session.close();

    }

    @Test
    void saveCinemaAndReturnWithId() {
        session.beginTransaction();
        var cinema = GettersEntityUtil.getCinema();
        cinemaRepository = new CinemaRepository(session);

        cinema = cinemaRepository.save(cinema);

        assertThat(cinema.getId()).isEqualTo(3L);

        session.getTransaction().rollback();
        session.close();

    }

    @Test
    void deleteCinema() {
        session.beginTransaction();
        cinemaRepository = new CinemaRepository(session);

        cinemaRepository.delete(1L);
        assertNull(session.get(Cinema.class,1L));

        session.getTransaction().rollback();
        session.close();

    }

    @Test
    void checkUpdateUsername (){
        session.beginTransaction();
        cinemaRepository = new CinemaRepository(session);

        var cinema = session.get(Cinema.class, 1L);
        cinema.setName("GGalaxy");
        cinemaRepository.update(cinema);

        assertEquals(session.get(Cinema.class, 1L).getName(), "GGalaxy");

        session.getTransaction().rollback();
        session.close();

    }
}
