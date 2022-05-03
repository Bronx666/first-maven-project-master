package com.bronx.repository;

import com.bronx.entity.Cinema;

import javax.persistence.EntityManager;

public class CinemaRepository extends RepositoryBase<Long, Cinema>{

    public CinemaRepository(EntityManager entityManager) {
        super(Cinema.class, entityManager);
    }
}
