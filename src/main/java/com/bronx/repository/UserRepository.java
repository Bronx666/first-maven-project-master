package com.bronx.repository;

import com.bronx.entity.User;

import javax.persistence.EntityManager;


public class UserRepository extends RepositoryBase<Long, User> {

    public UserRepository(EntityManager entityManager) {
        super(User.class, entityManager);
    }
}
