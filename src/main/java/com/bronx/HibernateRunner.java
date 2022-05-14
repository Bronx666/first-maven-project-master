package com.bronx;

import com.bronx.config.ApplicationConfiguration;
import com.bronx.repository.UserRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.Serializable;

public class HibernateRunner {

    public static void main(String[] args) {

        String value = "hello";
        System.out.println(CharSequence.class.isAssignableFrom(value.getClass()));
        System.out.println(BeanFactoryPostProcessor.class.isAssignableFrom(value.getClass()));
        System.out.println(Serializable.class.isAssignableFrom(value.getClass()));

        try (var context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class)) {
            var userRepository = context.getBean("userRepository", UserRepository.class);
            var session = context.getBean(Session.class);
            session.beginTransaction();

            System.out.println(userRepository);
            System.out.println("--------" + userRepository.findById(1L));


            session.getTransaction().commit();
        }

    }
}


