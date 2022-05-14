package com.bronx.daoTest;

import com.bronx.config.ApplicationConfiguration;
import com.bronx.entity.Film;
import com.bronx.repository.FilmRepository;
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

public class FilmRepositoryTest {

    static private SessionFactory sessionFactory;
    static private Session session;
    static private FilmRepository filmRepository;

    @BeforeAll
    static void init() {
        var context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        sessionFactory = context.getBean(SessionFactory.class);
        session = context.getBean(Session.class);
        filmRepository = context.getBean(FilmRepository.class);
        TestDataImporter.importData(sessionFactory);
    }

    @AfterAll
    static void close() {
        sessionFactory.close();
    }

    @Test
    void findAll() {
        session.beginTransaction();

        List<Film> results = filmRepository.findAll();
        assertThat(results).hasSize(3);

        List<String> fullNames = results.stream().map(Film::getName).collect(toList());
        assertThat(fullNames).containsExactlyInAnyOrder("Morbius", "Viking", "Stranger");

        session.getTransaction().rollback();
    }

    @Test
    void findFilmById() {

        session.beginTransaction();

        Optional<Film> result = filmRepository.findById(1L);
        assertEquals(result.get().getName(), "Morbius");

        session.getTransaction().rollback();
    }

    @Test
    void saveFilmAndReturnHimWithId() {

        session.beginTransaction();
        var film = GettersEntityUtil.getFilm();

        film = filmRepository.save(film);

        assertThat(film.getId()).isEqualTo(4L);

        session.getTransaction().rollback();
    }

    @Test
    void deleteFilm() {
        session.beginTransaction();

        filmRepository.delete(1L);
        assertNull(session.get(Film.class, 1L));

        session.getTransaction().rollback();
    }

    @Test
    void checkUpdateFilmName() {
        session.beginTransaction();

        var film = session.get(Film.class, 1L);
        film.setName("new name");
        filmRepository.update(film);

        assertEquals(session.get(Film.class, 1L).getName(), "new name");

        session.getTransaction().rollback();
    }
}
