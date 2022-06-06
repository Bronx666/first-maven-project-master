package com.bronx.integration;

import com.bronx.entity.Cinema;
import com.bronx.repository.CinemaRepository;
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
public class CinemaRepositoryIT {

    private static final Long CINEMA_ID = 1L;
    @Autowired
    CinemaRepository cinemaRepository;


    @Test
    void findAll() {
        List<Cinema> cinemas = cinemaRepository.findAll();

        assertThat(cinemas).hasSize(2);
        var actual = cinemas.stream().map(Cinema::getName).collect(Collectors.toList());
        assertThat(actual).containsExactlyInAnyOrder("Galaxy", "Helios");
    }

    @Test
    void findById() {
        var cinema = cinemaRepository.findById(CINEMA_ID);
        assertTrue(cinema.isPresent());
        assertEquals(cinema.get().getName(), "Galaxy");
    }

    @Test
    void deleteById() {
        cinemaRepository.deleteById(CINEMA_ID);

        assertEquals(Optional.empty(), cinemaRepository.findById(CINEMA_ID));
    }
}
