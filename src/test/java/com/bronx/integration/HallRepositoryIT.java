package com.bronx.integration;

import com.bronx.entity.Hall;
import com.bronx.repository.HallRepository;
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
public class HallRepositoryIT {

    private final static Long HALL_ID = 1L;

    @Autowired
    HallRepository hallRepository;


    @Test
    void findAll() {
        List<Hall> halls = hallRepository.findAll();

        assertThat(halls).hasSize(3);
        var actual = halls.stream().map(Hall::getName).collect(Collectors.toList());
        assertThat(actual).containsExactlyInAnyOrder("first galaxy hall", "second galaxy hall",
                "first helios hall");
    }

    @Test
    void findById() {
        var hall = hallRepository.findById(HALL_ID);
        assertTrue(hall.isPresent());
        assertEquals(hall.get().getName(), "first galaxy hall");
    }

    @Test
    void save() {
        var hall = hallRepository.findById(HALL_ID).get();
        hall.setId(null);
        hallRepository.save(hall);

        var actual = hallRepository.findById(4L);
        assertThat(actual.isPresent());
        assertEquals(4L, actual.get().getId());
    }

    @Test
    void deleteById() {
        hallRepository.deleteById(HALL_ID);

        assertEquals(Optional.empty(), hallRepository.findById(HALL_ID));
    }
}
