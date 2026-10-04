package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private final SessionFactory sessionFactory = Util.getSessionFactory();



    @Override
    public void createUsersTable() {
        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();

        session.createNativeQuery(
                "CREATE TABLE IF NOT EXISTS Users (" +
                        "ID BIGINT PRIMARY KEY AUTO_INCREMENT, " +
                        "NAME VARCHAR(50) NOT NULL, " +
                        "LASTNAME VARCHAR(50) NOT NULL, " +
                        "AGE TINYINT NOT NULL)"
        ).executeUpdate();

        transaction.commit();
        session.close();

    }

    @Override
    public void dropUsersTable() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.createNativeQuery("DROP TABLE IF EXISTS Users").executeUpdate();

        transaction.commit();
        session.close();

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        User user = new User(name, lastName, age);

        session.persist(user);

        transaction.commit();
        session.close();

    }

    @Override
    public void removeUserById(long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        User user = session.get(User.class, id);

        if (user != null) {
            session.remove(user);
        }

        transaction.commit();
        session.close();

    }

    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.openSession();

        List<User> userList;
        userList = session.createQuery("FROM User", User.class).getResultList();

        session.close();

        return userList;
    }

    @Override
    public void cleanUsersTable() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.createNativeQuery("TRUNCATE TABLE Users").executeUpdate();

        transaction.commit();
        session.close();


    }
}
