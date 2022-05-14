package com.bronx.daoTest;

import com.bronx.config.ApplicationConfiguration;
import com.bronx.entity.User;
import com.bronx.repository.UserRepository;
import com.bronx.testUtil.GettersEntityUtil;
import com.bronx.testUtil.TestDataImporter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserRepositoryTest {

    static private SessionFactory sessionFactory;
    static private Session session;
    static private UserRepository userRepository;

    @BeforeAll
    static void init() {
        var context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        sessionFactory = context.getBean(SessionFactory.class);
        session = context.getBean(Session.class);
        userRepository = context.getBean(UserRepository.class);
        TestDataImporter.importData(sessionFactory);
    }

    @AfterAll
    static void close() {
        sessionFactory.close();
    }

    @Test
    void findAll() {
        session.beginTransaction();

        List<User> results = userRepository.findAll();
        assertThat(results).hasSize(3);

        List<String> fullNames = results.stream().map(User::getUsername).collect(toList());
        assertThat(fullNames).containsExactlyInAnyOrder("Ivan", "Andrew", "Nick");

        session.getTransaction().rollback();
    }

    @Test
    void findUserById() {

        session.beginTransaction();

        Optional<User> result = userRepository.findById(1L);
        assertEquals(result.get().getUsername(), "Ivan");

        session.getTransaction().rollback();
    }

    @Test
    void saveUserAndReturnWithId() {

        session.beginTransaction();
        var user = GettersEntityUtil.getUser();

        user = userRepository.save(user);

        assertThat(user.getId()).isEqualTo(4L);

        session.getTransaction().rollback();
    }

    @Test
    void deleteUser() {
        session.beginTransaction();

        userRepository.delete(1L);
        assertNull(session.get(User.class, 1L));

        session.getTransaction().rollback();
    }

    @Test
    void checkUpdateUsername() {
        session.beginTransaction();

        var user = session.get(User.class, 1L);
        user.setUsername("IIvan");
        userRepository.update(user);

        assertEquals(session.get(User.class, 1L).getUsername(), "IIvan");

        session.getTransaction().rollback();
    }
}
