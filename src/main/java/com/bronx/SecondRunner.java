package com.bronx;

import com.bronx.dao.UserDao;
import com.bronx.entity.EventFilm;
import com.bronx.entity.Hall;
import com.bronx.entity.LevelAccess;
import com.bronx.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SecondRunner {

    public static void main(String[] args) {

        UserDao ud = new UserDao();

        User user = User.builder()
                .username("Andrew")
                .password("password")
                .levelAccess(LevelAccess.SUPER_ADMIN)
                .build();
        ud.create(user);

        ud.read(user, 4L);
    }
}
