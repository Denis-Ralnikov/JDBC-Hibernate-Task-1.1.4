package jm.task.core.jdbc.util;


import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    // реализуйте настройку соеденения с БД
    public static SessionFactory sessionFactory;

    public static Session session() {
        return getSessionFactory().getCurrentSession();
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Properties properties = new Properties();

                properties.setProperty(Environment.DRIVER, "org.postgresql.Driver");
                properties.setProperty(Environment.URL, "jdbc:postgresql://localhost:5432/itmentor");
                properties.setProperty(Environment.USER, "postgres");
                properties.setProperty(Environment.PASS, "root");
                properties.setProperty(Environment.DIALECT, "org.hibernate.dialect.PostgreSQL95Dialect");
                properties.setProperty(Environment.SHOW_SQL, "true");
                properties.setProperty(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                properties.setProperty(Environment.HBM2DDL_AUTO, "update");

                sessionFactory = new Configuration().addAnnotatedClass(User.class).addProperties(properties).buildSessionFactory();
                System.out.println("HIBER OK");
            } catch (Exception e) {
                System.out.println("ERROR AMBASSADOR HIBER");
            }
        }
        return sessionFactory;
    }

    public static Connection connection() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/itmentor", "postgres", "root");
            return connection;
        } catch (SQLException e) {
            System.out.println("Connection error");
            throw new RuntimeException(e);
        }
    }
}
