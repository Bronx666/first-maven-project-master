package com.bronx.integration;


import com.bronx.entity.User;
import com.bronx.repository.UserRepository;
import com.bronx.testUtil.GettersEntityUtil;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@Transactional
@Rollback
@RequiredArgsConstructor
public class UserRepositoryIT extends IntegrationTestBase{

    private static final Long USER_ID = 1L;

    @Autowired
    UserRepository userRepository;


    @Test
    void findAll() {
        List<User> users = userRepository.findAll();

        assertThat(users).hasSize(3);
        var actual = users.stream().map(User::getUsername).collect(Collectors.toList());
        assertThat(actual).containsExactlyInAnyOrder("Ivan", "Andrew", "Nick");
    }

    @Test
    void findById() {
        var user = userRepository.findById(USER_ID);
        assertTrue(user.isPresent());
        assertEquals(user.get().getUsername(), "Ivan");
    }

    @Test
    void save() {
        var user = userRepository.save(GettersEntityUtil.getUser());

        assertThat(userRepository.findById(4L).isPresent());
        assertEquals(4L, user.getId());
    }

    @Test
    void deleteById() {
        userRepository.deleteById(USER_ID);

        assertEquals(Optional.empty(), userRepository.findById(USER_ID));
    }
}









