package com.bronx.dao;

import com.bronx.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class UserDao extends AbstractDao implements IDao {


    @Override
    public void create(Object o) {
        Configuration conf = new Configuration();
        conf.configure();

        try (SessionFactory sf = conf.buildSessionFactory();
             Session session = sf.openSession()) {
            session.beginTransaction();
            session.save(o);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(Object o, int id) {

    }

    @Override
    public Object read(Object o, Long id) {
        User user = null;

        Configuration conf = new Configuration();
        conf.configure();

        try (SessionFactory sf = conf.buildSessionFactory();
             Session session = sf.openSession()) {
            session.beginTransaction();
            user = session.get(User.class, id);
            session.getTransaction().commit();
        }

        return user;
    }

    @Override
    public void update(Object o) {

    }
}
