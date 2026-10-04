package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Util {
    // реализуйте настройку соеденения с
    private static final SessionFactory sessionFactory;

    static {
    
        Configuration configuration = new Configuration();

        configuration.setProperty(
                "hibernate.connection.url",
                "jdbc:mysql://localhost:3306/mydb"
        );

        configuration.setProperty(
                "hibernate.connection.username",
                "root"
        );

        configuration.setProperty(
                "hibernate.connection.password",
                "5944200Aa"
        );

        configuration.setProperty(
                "hibernate.hbm2ddl.auto",
                "none"
        );



        configuration.addAnnotatedClass(User.class);

        sessionFactory = configuration.buildSessionFactory();
    }

    public static SessionFactory getSessionFactory() {
        return  sessionFactory;
    }


}
