package com.project.Util;

import com.project.Models.User;
import lombok.Getter;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    @Getter
    private static SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            Configuration configuration = new Configuration().configure();
            configuration.addAnnotatedClass(User.class);
            return configuration.buildSessionFactory();
        } catch (Exception e) {
            System.err.println("Error building SessionFactory: " + e);
            throw new RuntimeException("Error building SessionFactory", e);
        }
    }

    public static void shutdown() {
        if (sessionFactory != null && !sessionFactory.isClosed()) {
            sessionFactory.close();
        }
    }

    public static void overrideSessionFactory(SessionFactory factory) {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
        sessionFactory = factory;
    }
}
