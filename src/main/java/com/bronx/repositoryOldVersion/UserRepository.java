package com.bronx.repositoryOldVersion;

import com.bronx.entity.User;

import javax.persistence.EntityManager;

//@Component
public class UserRepository extends RepositoryBase<Long, User> {

    public UserRepository(EntityManager entityManager) {
        super(User.class, entityManager);
    }


}