package com.bronx.daoTest;

import com.bronx.config.ApplicationConfiguration;
import com.bronx.entity.Hall;
import com.bronx.repository.HallRepository;
import com.bronx.testUtil.GettersEntityUtil;
import com.bronx.testUtil.TestDataImporter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class HallRepositoryTest {

    static private SessionFactory sessionFactory;
    static private Session session;
    static private HallRepository hallRepository;

    @BeforeAll
    static void init() {
        var context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        sessionFactory = context.getBean(SessionFactory.class);
        session = context.getBean(Session.class);
        hallRepository = context.getBean(HallRepository.class);
        TestDataImporter.importData(sessionFactory);
    }

    @AfterAll
    static void close() {
        sessionFactory.close();
    }

    @Test
    void findAll() {
        session.beginTransaction();

        List<Hall> results = hallRepository.findAll();
        assertThat(results).hasSize(3);

        List<String> fullNames = results.stream().map(Hall::getName).collect(toList());
        assertThat(fullNames).containsExactlyInAnyOrder("first galaxy hall", "second galaxy hall", "first helios hall");

        session.getTransaction().rollback();
    }

    @Test
    void findHallById() {

        session.beginTransaction();

        Optional<Hall> result = hallRepository.findById(1L);
        assertEquals(result.get().getName(), "first galaxy hall");

        session.getTransaction().rollback();
    }

    @Test
    void saveHallAndReturnWithId() {

        session.beginTransaction();
        var hall = GettersEntityUtil.getHall(GettersEntityUtil.getCinema());

        hall = hallRepository.save(hall);

        assertThat(hall.getId()).isEqualTo(4L);

        session.getTransaction().rollback();
    }

    @Test
    void deleteHall() {
        session.beginTransaction();

        hallRepository.delete(1L);
        assertNull(session.get(Hall.class,1L));

        session.getTransaction().rollback();
    }

    @Test
    void checkUpdateHallName (){
        session.beginTransaction();

        var hall = session.get(Hall.class, 1L);
        hall.setName("another name");
        hallRepository.update(hall);

        assertEquals(session.get(Hall.class, 1L).getName(), "another name");

        session.getTransaction().rollback();
    }
}
