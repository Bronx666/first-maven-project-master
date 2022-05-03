package com.bronx.repository;

import com.bronx.entity.Hall;

import javax.persistence.EntityManager;

public class HallRepository extends RepositoryBase<Long, Hall> {

    public HallRepository(EntityManager entityManager){
        super(Hall.class, entityManager);
    }
}
