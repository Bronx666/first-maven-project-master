package com.bronx.repository;

import com.bronx.entity.Cinema;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class CinemaRepository extends RepositoryBase<Long, Cinema>{

    public CinemaRepository(EntityManager entityManager) {
        super(Cinema.class, entityManager);
    }
}
