package com.bronx.repository;


import com.bronx.entity.Film;

import javax.persistence.EntityManager;

public class FilmRepository extends RepositoryBase<Long, Film> {

    public FilmRepository(EntityManager entityManager) {
        super(Film.class, entityManager);
    }
}
