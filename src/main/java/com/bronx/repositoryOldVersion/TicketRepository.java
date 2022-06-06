package com.bronx.repositoryOldVersion;

import com.bronx.entity.Ticket;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

//@Repository
public class TicketRepository extends RepositoryBase<Long, Ticket> {

    public TicketRepository(EntityManager entityManager) {
        super(Ticket.class, entityManager);
    }
}
