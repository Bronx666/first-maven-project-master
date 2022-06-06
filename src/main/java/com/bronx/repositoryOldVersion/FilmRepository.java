package com.bronx.repositoryOldVersion;


import com.bronx.entity.Film;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

//@Repository
public class FilmRepository extends RepositoryBase<Long, Film> {

    public FilmRepository(EntityManager entityManager) {
        super(Film.class, entityManager);
    }
}
