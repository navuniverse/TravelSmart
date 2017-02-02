package com.travelportal.dao.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.dao.DataRetrievalFailureException;

import com.travelportal.dao.ISearchDao;
import com.travelportal.exception.DaoException;
import com.travelportal.model.ConnectorFsrModel;

/**
 * 
 * DAO class to manage all the DB related operations during flight search
 * 
 * @author naveen.kumar
 * 
 */
public class SearchDao implements ISearchDao {
    private Logger logger = Logger.getLogger(SearchDao.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * 
     * DAO Method to search a flight by fromCity, toCity and travelDate
     * 
     * @param from
     *            {@link String}
     * @param to
     *            {@link String}
     * @param date
     *            {@link Date}
     * @return connectors list of {@link ConnectorFsrModel}
     * @throws DaoException
     */
    @SuppressWarnings(UNCHECKED)
    public List<ConnectorFsrModel> searchFlight(String from, String to,
            Date date) throws DaoException {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = null;
        java.util.Date s = date;
        try {
            tx = session.beginTransaction();
            List<ConnectorFsrModel> connectors = (List<ConnectorFsrModel>) session
                    .createQuery(SEARCH_QUERY).setDate(TRAVEL_DATE, s)
                    .setString(FROM_CITY, from).setString(TO_CITY, to).list();
            logger.isInfoEnabled();
            logger.info(String.format(
                    "FLIGHT SEARCH SUCCESSFULL FROM %s TO %s ON %s", from, to,
                    s));
            tx.commit();
            return connectors;
        } catch (DataRetrievalFailureException ex) {
            logger.error(String.format(
                    "FLIGHT SEARCH FAILED FROM %s TO %s ON %s", from, to, s),
                    ex);
            throw new DaoException("Flight Search failed");
        }
    }
}
