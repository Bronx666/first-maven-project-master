package com.bronx.config;


import com.bronx.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;

@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = "com.bronx",
        useDefaultFilters = false,
        includeFilters = {
                @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Component.class),
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = "com\\..+Repository")

        })

public class ApplicationConfiguration {

    @Bean
    public SessionFactory sessionFactory(){
        return HibernateUtil.buildSessionFactory();
    }

    @Bean
    public Session getSession() {
        SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        return (Session) Proxy.newProxyInstance(SessionFactory.class.getClassLoader(), new Class[]{Session.class},
                (proxy, method, args1) -> method.invoke(sessionFactory.getCurrentSession(), args1));
    }

}
