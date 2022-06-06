package com.bronx.integration;

import com.bronx.entity.EventFilm;
import com.bronx.repository.EventFilmRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Sql(scripts = "classpath:sqlScripts/dropSchema.sql")
@Sql(scripts = "classpath:sqlScripts/createSchema.sql")
@Sql(scripts = "classpath:sqlScripts/insertData.sql")
@Transactional
@Rollback
@RequiredArgsConstructor
public class EventFilmRepositoryIT {

    private static final Long EVENTFILM_ID = 1L;
    @Autowired
    EventFilmRepository eventFilmRepository;


    @Test
    void findAll() {
        List<EventFilm> eventsFilm = eventFilmRepository.findAll();

        assertThat(eventsFilm).hasSize(3);
        var actual = eventsFilm.stream().map(EventFilm::getFreeSeats).collect(Collectors.toList());
        assertThat(actual).containsExactlyInAnyOrder(40, 35, 50);
    }

    @Test
    void findById() {
        var eventFilm = eventFilmRepository.findById(EVENTFILM_ID);
        assertTrue(eventFilm.isPresent());
        assertEquals(eventFilm.get().getFreeSeats(), 40);
    }


    @Test
    void deleteById() {
        eventFilmRepository.deleteById(EVENTFILM_ID);

        assertEquals(Optional.empty(), eventFilmRepository.findById(EVENTFILM_ID));
    }
}
