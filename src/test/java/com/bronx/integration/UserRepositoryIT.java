package com.bronx.integration;


import com.bronx.entity.User;
import com.bronx.repository.UserRepository;
import com.bronx.testUtil.GettersEntityUtil;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class UserRepositoryIT {

    private static final Long USER_ID = 1L;

    @Autowired
    Session session;
    @Autowired
    UserRepository userRepository;

    @Test
    void findById() {

        session.beginTransaction();
        User user = userRepository.save(GettersEntityUtil.getUser());

        var actualResult = userRepository.findById(USER_ID);

        assertTrue(actualResult.isPresent());
        actualResult.ifPresent(actual -> assertEquals(user, actual));

        session.getTransaction().rollback();

    }
}
