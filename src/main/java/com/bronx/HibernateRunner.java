package com.bronx;

import com.bronx.repository.UserRepository;
import com.bronx.entity.Role;
import com.bronx.entity.User;
import com.bronx.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.lang.reflect.Proxy;

public class HibernateRunner {

    public static void main(String[] args) {

        SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();

        var session = (Session) Proxy.newProxyInstance(SessionFactory.class.getClassLoader(), new Class[]{Session.class},
                (proxy, method, args1) -> method.invoke(sessionFactory.getCurrentSession(), args1));

        var user = User.builder()
                .username("Andrew")
                .password("1234")
                .role(Role.USER)
                .build();

        session.beginTransaction();
        var userRepository = new UserRepository(session);
        System.out.println(userRepository.save(user));

        session.getTransaction().commit();
    }
}


