package com.bronx.daoTest;

import com.bronx.repository.FilmRepository;
import com.bronx.entity.Film;
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

public class FilmRepositoryTest {

    static private final SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
    static private Session session;

    static private FilmRepository filmRepository;

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
        filmRepository = new FilmRepository(session);

        List<Film> results = filmRepository.findAll();
        assertThat(results).hasSize(3);

        List<String> fullNames = results.stream().map(Film::getName).collect(toList());
        assertThat(fullNames).containsExactlyInAnyOrder("Morbius", "Viking", "Stranger");

        session.getTransaction().rollback();
    }

    @Test
    void findFilmById() {

        session.beginTransaction();
        filmRepository = new FilmRepository(session);

        Optional<Film> result = filmRepository.findById(1L);
        assertEquals(result.get().getName(), "Morbius");

        session.getTransaction().rollback();
    }

    @Test
    void saveFilmAndReturnHimWithId() {

        session.beginTransaction();
        var film = GettersEntityUtil.getFilm();
        filmRepository = new FilmRepository(session);

        film = filmRepository.save(film);

        assertThat(film.getId()).isEqualTo(4L);

        session.getTransaction().rollback();
    }

    @Test
    void deleteFilm() {
        session.beginTransaction();
        filmRepository = new FilmRepository(session);

        filmRepository.delete(1L);
        assertNull(session.get(Film.class, 1L));

        session.getTransaction().rollback();
    }

    @Test
    void checkUpdateFilmName() {
        session.beginTransaction();
        filmRepository = new FilmRepository(session);

        var film = session.get(Film.class, 1L);
        film.setName("new name");
        filmRepository.update(film);

        assertEquals(session.get(Film.class, 1L).getName(), "new name");

        session.getTransaction().rollback();
    }
}
