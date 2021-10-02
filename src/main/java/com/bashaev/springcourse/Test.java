package com.bashaev.springcourse;

import com.bashaev.springcourse.config.HibernateConfig;
import com.bashaev.springcourse.config.SpringConfig;
import com.bashaev.springcourse.dao.UserDao;
import com.bashaev.springcourse.dao.UserDaoImpl;
import com.bashaev.springcourse.entity.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: Рустам
 */
//public class Test {
//
//    public static void main(String[] args) {
//
//        AnnotationConfigApplicationContext context =
//                new AnnotationConfigApplicationContext();
//        context.register(HibernateConfig.class);
//
//
//        User user = new User("fff", "ddd", "fff");
//
//        UserDaoImpl userDao = context.getBean(UserDaoImpl.class);
//        userDao.saveUser(user);
//
//        context.close();
//    }
//}
