package com.bronx.daoTest;

import com.bronx.entity.EventFilm;
import com.bronx.entity.dto.EventFilter;
import com.bronx.repositoryOldVersion.EventFilmRepository;
import com.bronx.testUtil.GettersEntityUtil;
import com.bronx.testUtil.TestDataImporter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class EventFilmRepositoryTest {


    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private Session session;
    @Autowired
    private EventFilmRepository eventFilmRepository;

    @BeforeEach
    void init() {
        TestDataImporter.importData(sessionFactory);
    }


    @Test
    void findAll() {
        session.beginTransaction();

        List<EventFilm> results = eventFilmRepository.findAll();
        assertThat(results).hasSize(3);

        List<Integer> fullNames = results.stream().map(EventFilm::getFreeSeats).collect(toList());
        assertThat(fullNames).containsExactlyInAnyOrder(40, 35, 50);

        session.getTransaction().rollback();
    }

    @Test
    void findEventFilmById() {

        session.beginTransaction();

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

        var eventFilm = session.get(EventFilm.class, 1L);
        eventFilm.setFreeSeats(34);
        eventFilmRepository.update(eventFilm);

        assertEquals(session.get(EventFilm.class, 1L).getFreeSeats(), 34);

        session.getTransaction().rollback();
    }

    @Test
    void findEventFromSpecifiedFilmIdAndHallId() {
        session.beginTransaction();

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
