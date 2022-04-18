package com.bronx.dao;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public abstract class AbstractDao implements IDao{

    //under development
    private void connection (){
        Configuration conf = new Configuration();
        conf.configure();

        try(SessionFactory sf = conf.buildSessionFactory();
            Session session = sf.openSession())
        {
            System.out.println("hi!============");
        }
    }

    @Override
    public void create(Object o) {

    }

    @Override
    public Object read(Object o, Long id) {
        return null;
    }

    @Override
    public void update(Object o) {

    }

    @Override
    public void delete(Object o, int id) {

    }
}
