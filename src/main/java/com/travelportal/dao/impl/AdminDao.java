package com.travelportal.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DataRetrievalFailureException;

import com.travelportal.dao.IAdminDao;
import com.travelportal.exception.DaoException;
import com.travelportal.model.ConnectorFsrModel;
import com.travelportal.model.DealModel;
import com.travelportal.model.FlightModel;

/**
 * 
 * DAO class to manage all the DB related admin operations
 * 
 * @author naveen.kumar
 * 
 */
public class AdminDao implements IAdminDao {
    private Logger logger = Logger.getLogger(AdminDao.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * 
     * DAO Method to add new flight
     * 
     * @param flightModel
     *            {@link FlightModel}
     * @return true {@link Boolean}
     * @throws DaoException
     */
    public Boolean addFlight(FlightModel flightModel) throws DaoException {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(flightModel);
            logger.isInfoEnabled();
            logger.info(String.format("%s FLIGHT SUCCESSFULLY ADDED",
                    flightModel.getFlightId()));
            tx.commit();
            return true;
        } catch (DataIntegrityViolationException ex) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            logger.error(
                    String.format("%s FLIGHT ALREADY EXISTS",
                            flightModel.getFlightId()), ex);
            throw new DaoException(
                    "Adding new flight failed. Flight already exists.");
        }
    }

    /**
     * 
     * DAO Method to update a flight
     * 
     * @param flightModel
     *            {@link FlightModel}
     * @return true {@link Boolean}
     * @throws DaoException
     */
    public Boolean updateFlight(FlightModel flightModel) throws DaoException {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(flightModel);
            tx.commit();
            logger.isInfoEnabled();
            logger.info(String.format("%s FLIGHT SUCCESSFULLY UPDATED",
                    flightModel.getFlightId()));
            return true;
        } catch (DataIntegrityViolationException ex) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            logger.error(String.format(
                    "CANNOT UPDATE %s FLIGHT ALREADY EXIST IN DB",
                    flightModel.getFlightId()), ex);
            throw new DaoException(
                    "Updating flight failed. Flight already exist with same data.");
        }
    }

    /**
     * 
     * DAO Method to delete a flight
     * 
     * @param flightModel
     *            {@link FlightModel}
     * @return true {@link Boolean}
     * @throws DaoException
     */
    public Boolean deleteFlight(FlightModel flightModel) throws DaoException {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(flightModel);
            tx.commit();
            logger.isInfoEnabled();
            logger.info(String.format("%s FLIGHT SUCCESSFULLY DELETED",
                    flightModel.getFlightId()));
            return true;
        } catch (DataRetrievalFailureException ex) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            logger.error(String.format(
                    "%s FLIGHT NOT DELETED. NOT AVAILABLE IN DB",
                    flightModel.getFlightId()), ex);
            throw new DaoException("Flight not found in db to delete");
        }
    }

    /**
     * 
     * DAO Method to add a new schedule from CSV by calling DB Stored Procedure
     * ADD_NEW_SCHEDULE
     * 
     * @param row
     *            {@link String}
     * @return true {@link Boolean}
     * @throws DaoException
     */
    public boolean addSchedule(String[] row) throws DaoException {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = null;
        int i = 1;
        try {
            tx = session.beginTransaction();
            session.createSQLQuery(CALL_ADD_NEW_SCHEDULE)
                    .setParameter("SCHEDULE_ID", row[i])
                    .setParameter("FLIGHT_DATE", row[++i])
                    .setParameter("SOURCE_DEPARTURE_TIME", row[++i])
                    .setParameter("VIA_ARRIVAL_TIME", row[++i])
                    .setParameter("VIA_DEPARTURE_TIME", row[++i])
                    .setParameter("DESTINATION_ARRIVAL_TIME", row[++i])
                    .setParameter("FARE_SOURCE_DESTINATION", row[++i])
                    .setParameter("DEAL_ID", row[++i])
                    .setParameter("FARE_SOURCE_VIA", row[++i])
                    .setParameter("FARE_VIA_DESTINATION", row[++i])
                    .setParameter("AVAIL_ID", row[++i])
                    .setParameter("FLIGHT_ID", row[++i])
                    .setParameter("AVAIL_SOURCE_VIA", row[++i])
                    .setParameter("AVAIL_VIA_DESTINATION", row[++i])
                    .setParameter("AVAIL_SOURCE_DESTINATION", row[++i])
                    .setParameter("ROUTE_ID", row[++i])
                    .setParameter("CONNECTOR_ID", row[++i]).executeUpdate();
            logger.isInfoEnabled();
            logger.info("SCHEDULE SUCCESSFULLY ADDED");
            tx.commit();
            return true;
        } catch (DataIntegrityViolationException ex) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            logger.error(String.format("%s SCHEDULE ALREADY EXIST", row[1]), ex);
            throw new DaoException(
                    "Adding new schedule failed. Schedule already exists.");
        }
    }

    /**
     * 
     * DAO Method to update a schedule from CSV by calling DB Stored Procedure
     * UPDATE_SCHEDULE
     * 
     * @param row
     *            {@link String}
     * @return true {@link Boolean}
     * @throws DaoException
     */
    public boolean updateSchedule(String row[]) throws DaoException {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = null;
        int i = 1;
        try {
            tx = session.beginTransaction();
            session.createSQLQuery(CALL_UPDATE_SCHEDULE)
                    .setParameter("SCHEDULE_I", row[i])
                    .setParameter("FLIGHT_DAT", row[++i])
                    .setParameter("SOURCE_DEPARTURE_TIM", row[++i])
                    .setParameter("VIA_ARRIVAL_TIM", row[++i])
                    .setParameter("VIA_DEPARTURE_TIM", row[++i])
                    .setParameter("DESTINATION_ARRIVAL_TIM", row[++i])
                    .setParameter("FARE_SOURCE_DESTINATIO", row[++i])
                    .setParameter("DEAL_I", row[++i])
                    .setParameter("FARE_SOURCE_VI", row[++i])
                    .setParameter("FARE_VIA_DESTINATIO", row[++i])
                    .setParameter("AVAIL_I", row[++i])
                    .setParameter("FLIGHT_I", row[++i])
                    .setParameter("AVAIL_SOURCE_VI", row[++i])
                    .setParameter("AVAIL_VIA_DESTINATIO", row[++i])
                    .setParameter("AVAIL_SOURCE_DESTINATIO", row[++i])
                    .setParameter("ROUTE_I", row[++i])
                    .setParameter("CONNECTOR_I", row[++i]).executeUpdate();
            logger.isInfoEnabled();
            logger.info("SCHEDULE SUCCESSFULLY UPDATED");
            tx.commit();
            return true;
        } catch (DataIntegrityViolationException ex) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            logger.error(String.format(
                    "CANNOT UPDATE %s SCHEDULE ALREADY EXIST", row[1]), ex);
            throw new DaoException(
                    "Updating schedule failed. Schedule already exists with same data.");
        }
    }

    /**
     * 
     * DAO Method to delete a schedule from CSV by calling DB Stored Procedure
     * DELETE_SCHEDULE
     * 
     * @param row
     *            {@link String}
     * @return true {@link Boolean}
     * @throws DaoException
     */
    public boolean deleteSchedule(String row[]) throws DaoException {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = null;
        int i = 1;
        try {
            tx = session.beginTransaction();
            session.createSQLQuery(CALL_DELETE_SCHEDULE)
                    .setParameter("SCHEDULE_I", row[i])
                    .setParameter("FLIGHT_DATE", row[++i])
                    .setParameter("SOURCE_DEPARTURE_TIME", row[++i])
                    .setParameter("VIA_ARRIVAL_TIME", row[++i])
                    .setParameter("VIA_DEPARTURE_TIME", row[++i])
                    .setParameter("DESTINATION_ARRIVAL_TIME", row[++i])
                    .setParameter("FARE_SOURCE_DESTINATION", row[++i])
                    .setParameter("DEAL_ID", row[++i])
                    .setParameter("FARE_SOURCE_VIA", row[++i])
                    .setParameter("FARE_VIA_DESTINATION", row[++i])
                    .setParameter("AVAIL_I", row[++i])
                    .setParameter("FLIGHT_ID", row[++i])
                    .setParameter("AVAIL_SOURCE_VIA", row[++i])
                    .setParameter("AVAIL_VIA_DESTINATION", row[++i])
                    .setParameter("AVAIL_SOURCE_DESTINATION", row[++i])
                    .setParameter("ROUTE_ID", row[++i])
                    .setParameter("CONNECTOR_I", row[++i]).executeUpdate();
            logger.isInfoEnabled();
            logger.info("SCHEDULE SUCCESSFULLY DELETED");
            tx.commit();
            return true;
        } catch (DataRetrievalFailureException ex) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            logger.error(String.format("%s SCHEDULE ALREADY EXIST", row[1]), ex);
            throw new DaoException(
                    "Deleting schedule failed. Schedule does not exists.");
        }
    }

    /**
     * 
     * DAO Method to add a new deal
     * 
     * @param dealModel
     *            {@link DealModel}
     * @return true {@link Boolean}
     * @throws DaoException
     */
    public boolean addDeal(DealModel dealModel) throws DaoException {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(dealModel);
            tx.commit();
            logger.isInfoEnabled();
            logger.info(String.format("%s DEAL SUCCESSFULLY ADDED",
                    dealModel.getDealId()));
            return true;
        } catch (DataIntegrityViolationException ex) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            logger.error(
                    String.format("%s DEAL ALREADY EXIST",
                            dealModel.getDealId()), ex);
            throw new DaoException(
                    "Adding new deal failed. Deal already exists.");
        }
    }

    /**
     * 
     * DAO Method to add a new deal
     * 
     * @param dealModel
     *            {@link DealModel}
     * @return true {@link Boolean}
     * @throws DaoException
     */
    public boolean updateDeal(DealModel dealModel) throws DaoException {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(dealModel);
            tx.commit();
            logger.isInfoEnabled();
            logger.info(String.format("%s DEAL SUCCESSFULLY UPDATED",
                    dealModel.getDealId()));
            return true;
        } catch (DataIntegrityViolationException ex) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            logger.error(String.format(
                    "CANNOT UPDATE %s DEAL ALREADY EXIST IN DB",
                    dealModel.getDealId()), ex);
            throw new DaoException(
                    "Updating deal failed. Deal already exist with same data.");
        }
    }

    /**
     * 
     * DAO Method to add a new deal
     * 
     * @param dealModel
     *            {@link DealModel}
     * @return true {@link Boolean}
     * @throws DaoException
     */
    public boolean deleteDeal(DealModel dealModel) throws DaoException {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(dealModel);
            tx.commit();
            logger.isInfoEnabled();
            logger.info(String.format("%s DEAL SUCCESSFULLY DELETED",
                    dealModel.getDealId()));
            return true;
        } catch (DataRetrievalFailureException ex) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            logger.error(
                    String.format("%s DEAL DOES NOT EXIST TO DELETE",
                            dealModel.getDealId()), ex);
            throw new DaoException(
                    "Deleting deal failed. Deal does not exists.");
        }
    }

    /**
     * 
     * DAO method to fetch the schedule providing a flightId
     * 
     * @param flightId
     *            {@link String}
     * @return connectorFsrModels {@link ConnectorFsrModel}
     * @throws DaoException
     */
    public List<ConnectorFsrModel> getSchedule(String flightId)
            throws DaoException {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            @SuppressWarnings(UNCHECKED)
            List<ConnectorFsrModel> connectorFsrModels = session
                    .createQuery(GET_SCHEDULE_QUERY)
                    .setString(FLIGHT_ID, flightId).list();
            tx.commit();
            logger.isInfoEnabled();
            logger.info(String.format(
                    "SCHEDULE OF FLIGHT %s SUCCESSFULLY FETCHED", flightId));
            return connectorFsrModels;
        } catch (DataRetrievalFailureException ex) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            logger.error(String.format("SCHEDULE OF FLIGHT %s NOT AVAILABLE",
                    flightId), ex);
            throw new DaoException("Flight Schedule not available");
        }
    }

    /**
     * 
     * DAO Method to get all the deals available
     * 
     * @return dealModel {@link DealModel}
     * @throws DaoException
     */
    public List<DealModel> getDeal() throws DaoException {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            @SuppressWarnings(UNCHECKED)
            List<DealModel> dealModel = session.createQuery(DEAL_QUERY).list();
            tx.commit();
            logger.isInfoEnabled();
            logger.info("DEALS SUCCESSFULLY FETCHED");
            return dealModel;
        } catch (DataRetrievalFailureException ex) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            logger.error("DEALS NOT AVAILABLE", ex);
            throw new DaoException("No deal available");
        }
    }
}
