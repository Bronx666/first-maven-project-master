package com.bronx.repository;

import com.bronx.entity.Ticket;

import javax.persistence.EntityManager;

public class TicketRepository extends RepositoryBase<Long, Ticket>{

    public TicketRepository(EntityManager entityManager) {
        super(Ticket.class, entityManager);
    }
}
