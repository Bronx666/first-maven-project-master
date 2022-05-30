package com.bronx.repository;

import com.bronx.entity.User;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
public class UserRepository extends RepositoryBase<Long, User> {

    public UserRepository(EntityManager entityManager) {
        super(User.class, entityManager);
    }


}
