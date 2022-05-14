package com.bronx.repository;

import com.bronx.entity.Hall;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class HallRepository extends RepositoryBase<Long, Hall> {

    public HallRepository(EntityManager entityManager){
        super(Hall.class, entityManager);
    }
}
