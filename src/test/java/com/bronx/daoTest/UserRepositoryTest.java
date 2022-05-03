package com.bronx.daoTest;

import com.bronx.repository.UserRepository;
import com.bronx.entity.User;
import com.bronx.testUtil.GettersEntityUtil;
import com.bronx.testUtil.TestDataImporter;
import com.bronx.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.lang.reflect.Proxy;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserRepositoryTest {

    static private final SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
    static private Session session;

    static private UserRepository userRepository;

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
        userRepository = new UserRepository(session);

        List<User> results = userRepository.findAll();
        assertThat(results).hasSize(3);

        List<String> fullNames = results.stream().map(User::getUsername).collect(toList());
        assertThat(fullNames).containsExactlyInAnyOrder("Ivan", "Andrew", "Nick");

        session.getTransaction().rollback();
    }

    @Test
    void findUserById() {

        session.beginTransaction();
        userRepository = new UserRepository(session);

        Optional<User> result = userRepository.findById(1L);
        assertEquals(result.get().getUsername(), "Ivan");

        session.getTransaction().rollback();
    }

    @Test
    void saveUserAndReturnWithId() {

        session.beginTransaction();
        var user = GettersEntityUtil.getUser();
        userRepository = new UserRepository(session);

        user = userRepository.save(user);

        assertThat(user.getId()).isEqualTo(4L);

        session.getTransaction().rollback();
    }

    @Test
    void deleteUser() {
        session.beginTransaction();
        userRepository = new UserRepository(session);

        userRepository.delete(1L);
        assertNull(session.get(User.class,1L));

        session.getTransaction().rollback();
    }

    @Test
    void checkUpdateUsername (){
        session.beginTransaction();
        userRepository = new UserRepository(session);

        var user = session.get(User.class, 1L);
        user.setUsername("IIvan");
        userRepository.update(user);

        assertEquals(session.get(User.class, 1L).getUsername(), "IIvan");

        session.getTransaction().rollback();
    }
}
