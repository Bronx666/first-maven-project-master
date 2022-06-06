package com.bronx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApplicationRunner {

    public static void main(String[] args) {

        var context = SpringApplication.run(ApplicationRunner.class, args);
//        var sessionFuck = context.getBean(SessionFactory.class);
//        var bean = context.getBean(TestDataImporter.class);
//        bean.importData(sessionFuck);
        System.out.println(context.getBeanDefinitionCount());
    }
}


