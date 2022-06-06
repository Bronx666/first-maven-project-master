package com.bronx.integration;

import com.bronx.entity.Film;
import com.bronx.repository.FilmRepository;
import com.bronx.testUtil.GettersEntityUtil;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Disabled;
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
public class FilmRepositoryIT {
    private static final Long FILM_ID = 1L;
    @Autowired
    FilmRepository filmRepository;


    @Test
    void findAll() {
        List<Film> films = filmRepository.findAll();

        assertThat(films).hasSize(3);
        var actual = films.stream().map(Film::getName).collect(Collectors.toList());
        assertThat(actual).containsExactlyInAnyOrder("Morbius", "Viking", "Stranger");
    }

    @Test
    void findById() {
        var film = filmRepository.findById(FILM_ID);
        assertTrue(film.isPresent());
        assertEquals(film.get().getName(), "Morbius");
    }

    @Test
    @Disabled
    void save() {
        var film = filmRepository.save(GettersEntityUtil.getFilm());

        var actual = filmRepository.findById(4L);
        assertThat(actual.isPresent());
        assertEquals(4L, actual.get().getId());
    }

    @Test
    void deleteById() {
        filmRepository.deleteById(FILM_ID);

        assertEquals(Optional.empty(), filmRepository.findById(FILM_ID));
    }
}
