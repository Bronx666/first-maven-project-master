package com.bronx.daoTest;

import com.bronx.repository.HallRepository;
import com.bronx.entity.Hall;
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

public class HallRepositoryTest {

    static private final SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
    static private Session session;

    static private HallRepository hallRepository;

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
        hallRepository = new HallRepository(session);

        List<Hall> results = hallRepository.findAll();
        assertThat(results).hasSize(3);

        List<String> fullNames = results.stream().map(Hall::getName).collect(toList());
        assertThat(fullNames).containsExactlyInAnyOrder("first galaxy hall", "second galaxy hall", "first helios hall");

        session.getTransaction().rollback();
    }

    @Test
    void findHallById() {

        session.beginTransaction();
        hallRepository = new HallRepository(session);

        Optional<Hall> result = hallRepository.findById(1L);
        assertEquals(result.get().getName(), "first galaxy hall");

        session.getTransaction().rollback();
    }

    @Test
    void saveHallAndReturnWithId() {

        session.beginTransaction();
        var hall = GettersEntityUtil.getHall(GettersEntityUtil.getCinema());
        hallRepository = new HallRepository(session);

        hall = hallRepository.save(hall);

        assertThat(hall.getId()).isEqualTo(4L);

        session.getTransaction().rollback();
    }

    @Test
    void deleteHall() {
        session.beginTransaction();
        hallRepository = new HallRepository(session);

        hallRepository.delete(1L);
        assertNull(session.get(Hall.class,1L));

        session.getTransaction().rollback();
    }

    @Test
    void checkUpdateHallName (){
        session.beginTransaction();
        hallRepository = new HallRepository(session);

        var hall = session.get(Hall.class, 1L);
        hall.setName("another name");
        hallRepository.update(hall);

        assertEquals(session.get(Hall.class, 1L).getName(), "another name");

        session.getTransaction().rollback();
    }
}
