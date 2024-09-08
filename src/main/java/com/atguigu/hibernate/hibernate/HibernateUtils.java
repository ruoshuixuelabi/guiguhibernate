package com.atguigu.hibernate.hibernate;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.cfg.Configuration;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author admin
 */
public class HibernateUtils {
    private HibernateUtils() {
    }

    private static final HibernateUtils instance = new HibernateUtils();

    public static HibernateUtils getInstance() {
        return instance;
    }

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Configuration configuration = new Configuration().configure();
            // ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
            // .applySettings(configuration.getProperties()).buildServiceRegistry();
            // sessionFactory =configuration.buildSessionFactory(serviceRegistry);
            sessionFactory = configuration.buildSessionFactory();
        }
        return sessionFactory;
    }

    public Session getSession() {
        return getSessionFactory().getCurrentSession();
    }
}
