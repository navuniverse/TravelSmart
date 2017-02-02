package com.travelportal.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.dao.DataRetrievalFailureException;

import com.travelportal.dao.IRouteDao;
import com.travelportal.exception.DaoException;

/**
 * 
 * DAO Class to fetch Source and Destination dynamically
 * 
 * @author naveen.kumar
 * 
 */
public class RouteDao implements IRouteDao {

    private static Logger logger = Logger.getLogger(RouteDao.class);
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * 
     * DAO Method to fetch list of source cities dynamically from DB
     * 
     * @return list of source cities
     * @throws DaoException
     */
    @SuppressWarnings(UNCHECKED)
    public List<String> getSourceCities() throws DaoException {
        Session session = sessionFactory.getCurrentSession();

        try {
            logger.isInfoEnabled();
            logger.info("FETCHING LIST OF SOURCE CITIES");
            session.beginTransaction();
            Query query = session.createQuery(SOURCE_DIRECT_QUERY);
            List<String> list = (List<String>) query.list();
            query = session.createQuery(SOURCE_STOP_QUERY);
            for (String city : (List<String>) query.list()) {
                if (!list.contains(city)) {
                    list.add(city);
                }
            }
            session.getTransaction().commit();
            return list;
        } catch (DataRetrievalFailureException ex) {
            logger.error("NO SOURCE CITY AVAILABLE", ex);
            throw new DaoException("No Source City available");
        }
    }

    /**
     * 
     * DAO Method to fetch list of destination cities dynamically from DB
     * corresponding to source city
     * 
     * @param fromCity
     *            {@link String}
     * @return list of destination cities corresponding to source city
     * @throws DaoException
     */
    @SuppressWarnings(UNCHECKED)
    public List<String> getDestinationCities(String fromCity)
            throws DaoException {
        Session session = sessionFactory.getCurrentSession();

        try {
            logger.isInfoEnabled();
            logger.info("FETCHING LIST OF DESTINATION CITIES");
            session.beginTransaction();
            Query query = session.createQuery(DESTINATION_DIRECT_QUERY)
                    .setString(FROM_CITY, fromCity);
            List<String> list = (List<String>) query.list();
            query = session.createQuery(DESTINATION_VIA_QUERY).setString(
                    FROM_CITY, fromCity);
            for (String city : (List<String>) query.list()) {
                if (!list.contains(city)) {
                    list.add(city);
                }
            }
            session.getTransaction().commit();
            return list;

        } catch (DataRetrievalFailureException ex) {
            logger.error(
                    String.format(
                            "NO DESTINATION CITY PRESENT CORRESPONDING TO SOURCE CITY %s",
                            fromCity), ex);
            sessionFactory.getCurrentSession().getTransaction().rollback();
            throw new DaoException("No destination City available");
        }
    }
}
