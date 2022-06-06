package com.bronx.integration;

import com.bronx.entity.Ticket;
import com.bronx.repository.TicketRepository;
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
public class TicketRepositoryIT {

    private static final Long TICKET_ID = 1L;
    @Autowired
    TicketRepository ticketRepository;

    @Test
    void findAll() {
        List<Ticket> tickets = ticketRepository.findAll();

        assertThat(tickets).hasSize(3);
        var actual = tickets.stream().map(Ticket::getTicketNumber).collect(Collectors.toList());
        assertThat(actual).containsExactlyInAnyOrder(5, 6, 7);
    }

    @Test
    void findById() {
        var user = ticketRepository.findById(TICKET_ID);
        assertTrue(user.isPresent());
        assertEquals(user.get().getTicketNumber(), 5);
    }

    @Test
    void save() {

        var ticket = ticketRepository.findById(TICKET_ID).get();
        ticket.setId(null);
        ticketRepository.save(ticket);

        assertThat(ticketRepository.findById(4L).isPresent());
        assertEquals(null, ticket.getId());
    }

    @Test
    void deleteById() {
        ticketRepository.deleteById(TICKET_ID);

        assertEquals(Optional.empty(), ticketRepository.findById(TICKET_ID));
    }
}
