package com.travelportal.util.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Class for getting session from session factory
 * 
 * @author naveen.kumar
 * 
 */

public final class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static Session sess = null;

    /**
     * Function to build session factory
     * 
     * @return sessionFactory
     */
    @SuppressWarnings("deprecation")
    private static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            return new Configuration().configure().buildSessionFactory();
        } catch (Exception ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     * Function to get SessionFactory
     * 
     * @return sessionFactory or null
     */
    public static SessionFactory getSessionFactory() {
        if (sessionFactory != null) {
            return sessionFactory;
        } else {
            return buildSessionFactory();
        }
    }

    /**
     * Function to get session
     * 
     * @return session
     * @throws HibernateException
     */
    public static Session getSession() throws HibernateException {

        if (sess == null) {
            // Initializing session if it is and returning it
            sess = HibernateUtil.getSessionFactory().openSession();
        } else {
            // Returning current session if it already exist
            sess = HibernateUtil.getSessionFactory().getCurrentSession();
        }
        return sess;
    }
}
