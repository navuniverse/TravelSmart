package com.travelportal.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DataRetrievalFailureException;

import com.travelportal.dao.IUserDao;
import com.travelportal.exception.DaoException;
import com.travelportal.model.ConnectorFsrModel;
import com.travelportal.model.TicketModel;
import com.travelportal.model.TravellerModel;
import com.travelportal.model.UserModel;

/**
 * 
 * DAO class to manage all the DB related operations for a user like
 * registration, login, profile update, history etc.
 * 
 * @author naveen.kumar
 * 
 */
public class UserDao implements IUserDao {

    private Logger logger = Logger.getLogger(UserDao.class);
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * 
     * DAO method to register a new user
     * 
     * @param userModel
     *            {@link UserModel}
     * @return true {@link Boolean}
     * @throws DaoException
     */
    public Boolean insertUser(UserModel userModel) throws DaoException {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(userModel);
            tx.commit();
            logger.isInfoEnabled();
            logger.info(String.format("%s USER SUCCESSFULLY REGISTERED",
                    userModel.getUsername()));
            return true;
        } catch (DataIntegrityViolationException ex) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            logger.error(
                    String.format("%s USER ALREADY EXIST",
                            userModel.getUsername()), ex);
            throw new DaoException("User already registered");
        }
    }

    /**
     * 
     * DAO method to retrieve a user at the time of login
     * 
     * @param userModel
     *            {@link UserModel}
     * @return user {@link UserModel}
     * @throws DaoException
     */
    public UserModel getUser(UserModel userModel) throws DaoException {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            UserModel user = (UserModel) session.createQuery(LOGIN_QUERY)
                    .setString(USERNAME, userModel.getUsername())
                    .uniqueResult();
            tx.commit();
            if (user == null) {
                throw new DaoException("User Not Found!!!");
            }
            return user;
        } catch (DataRetrievalFailureException ex) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            logger.error(
                    String.format("%s USER NOT FOUND", userModel.getUsername()),
                    ex);
            throw new DaoException("User Not Found!!");
        }
    }

    /**
     * 
     * DAO method to get user password
     * 
     * @param userId
     *            {@link String}
     * @return userModel {@link UserModel}
     * @throws DaoException
     */
    public UserModel getPassword(String userId) throws DaoException {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            UserModel userModel = (UserModel) session
                    .createQuery(FORGET_PASSWORD_QUERY)
                    .setString(USERNAME, userId).uniqueResult();
            tx.commit();
            return userModel;
        } catch (DataRetrievalFailureException ex) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            logger.error(String.format("%s USER NOT FOUND", userId), ex);
            throw new DaoException("User Not Found!!");
        }
    }

    /**
     * 
     * DAO method to update user profile
     * 
     * @param userModel
     *            {@link UserModel}
     * @return true {@link Boolean}
     * @throws DaoException
     */
    public Boolean updateUser(UserModel userModel) throws DaoException {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery(UPDATE_USER_QUERY)
                    .setString(PASSWORD, userModel.getPassword())
                    .setString(USERNAME, userModel.getUsername());
            query.executeUpdate();
            tx.commit();
            logger.isInfoEnabled();
            logger.info(String.format("%s USER SUCCESSFULLY UPDATED",
                    userModel.getUsername()));
            return true;
        } catch (DataRetrievalFailureException ex) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            logger.error(
                    String.format("%s USER NOT FOUND", userModel.getUsername()),
                    ex);
            throw new DaoException("User Not Found!!!");
        }
    }

    /**
     * 
     * DAO method to get user travel history
     * 
     * @param userModel
     *            {@link UserModel}
     * @return ticketModel {@link TicketModel}
     * @throws DaoException
     */
    public List<TicketModel> getHistory(UserModel userModel)
            throws DaoException {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            @SuppressWarnings(UNCHECKED)
            List<TicketModel> ticketModel = session
                    .createQuery(TRAVEL_HISTORY_QUERY)
                    .setString(USERNAME, userModel.getUsername()).list();
            tx.commit();
            logger.isInfoEnabled();
            logger.info(String.format(
                    "TRAVEL HISTORY OF USER %s SUCCESSFULLY FETCHED",
                    userModel.getUsername()));
            return ticketModel;
        } catch (DataRetrievalFailureException ex) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            logger.error(
                    String.format("%s USER HISTORY NOT FOUND",
                            userModel.getUsername()), ex);
            throw new DaoException("User history Not Found!!!");
        }
    }

    /**
     * 
     * DAO method to cancel a ticket
     * 
     * @param ticketId
     *            {@link Integer}
     * @return true {@link Boolean}
     * @throws DaoException
     */
    public Boolean cancelTicket(int ticketId) throws DaoException {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.createQuery(CANCEL_TICKET_QUERY)
                    .setInteger(TICKET_ID, ticketId).executeUpdate();

            TicketModel ticketModel = (TicketModel) session
                    .createQuery(FROM_TICKET_MODEL)
                    .setInteger(TICKET_ID, ticketId).uniqueResult();
            String connectorId = ticketModel.getConnectorId();

            ConnectorFsrModel fsrModel = (ConnectorFsrModel) session
                    .createQuery(FROM_CONNECTOR_FSR_MODEL)
                    .setString(CONNECTOR_ID, connectorId).uniqueResult();
            String flightId = fsrModel.getFlightId().getFlightId();
            @SuppressWarnings(UNCHECKED)
            List<TravellerModel> trList = session
                    .createQuery(FROM_TRAVELLER_MODEL)
                    .setInteger(TICKET_ID, ticketModel.getTicketId()).list();
            int passengers = 0;
            for (TravellerModel travellerModel : trList) {
                if (travellerModel.getAge() > 2) {
                    passengers++;
                }
            }

            // Updating Availability when flight is cancelled from flight source
            // to destination
            if (fsrModel.getRouteId().getSource()
                    .equals(ticketModel.getSource())
                    && fsrModel.getRouteId().getDestination()
                            .equals(ticketModel.getDestination())) {
                session.createQuery(UPDATE_AVAILABILITY_MODEL1)
                        .setInteger(PASSENGER, passengers)
                        .setString(FLIGHT_ID, flightId).executeUpdate();
            }

            // Updating Availability when flight is cancelled from flight source
            // to via
            else if (fsrModel.getRouteId().getSource()
                    .equals(ticketModel.getSource())
                    && fsrModel.getRouteId().getVia()
                            .equals(ticketModel.getDestination())) {
                session.createQuery(UPDATE_AVAILABILITY_MODEL2)
                        .setInteger(PASSENGER, passengers)
                        .setString(FLIGHT_ID, flightId).executeUpdate();
            }

            // Updating Availability when flight is cancelled from flight via to
            // destination
            else {
                session.createQuery(UPDATE_AVAILABILITY_MODEL3)
                        .setInteger(PASSENGER, passengers)
                        .setString(FLIGHT_ID, flightId).executeUpdate();

            }
            tx.commit();
            logger.isInfoEnabled();
            logger.info(String.format("TICKET %s SUCCESSFULLY CANCELLED",
                    ticketId));
            return true;
        } catch (DataRetrievalFailureException ex) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            logger.error(String.format("TICKET %s NOT FOUND", ticketId), ex);
            throw new DaoException("Ticket not found.");
        }
    }

    /**
     * 
     * DAO method to get user travel history
     * 
     * @param email
     *            {@link String}
     * @return ticketModel {@link TicketModel}
     * @throws DaoException
     */
    public List<TicketModel> getHistory(String email) throws DaoException {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            @SuppressWarnings(UNCHECKED)
            List<TicketModel> ticketModel = (List<TicketModel>) session
                    .createQuery(HISTORY_QUERY).setString(EMAIL, email).list();

            logger.info("TRAVEL HISTORY SUCCESSFULLY FETCHED");
            tx.commit();
            return ticketModel;
        } catch (DataRetrievalFailureException ex) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            logger.error(String.format("%s USER HISTORY NOT FOUND", email), ex);
            throw new DaoException("User history Not Found!!!");
        }
    }

    /**
     * 
     * DAO method to get list of travellers per ticket
     * 
     * @param ticketId
     *            {@link Integer}
     * @return travellerModels {@link TravellerModel}
     * @throws DaoException
     */
    public List<TravellerModel> getTicketDetails(int ticketId)
            throws DaoException {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            @SuppressWarnings(UNCHECKED)
            List<TravellerModel> travellerModels = (List<TravellerModel>) session
                    .createQuery(TICKET_DETAILS_QUERY)
                    .setInteger(TICKET_ID, ticketId).list();
            tx.commit();
            logger.info("TRAVELLER LIST SUCCESSFULLY FETCHED");
            return travellerModels;

        } catch (DataRetrievalFailureException ex) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            logger.error(
                    String.format("TICKET %s DETAILS NOT FOUND", ticketId), ex);
            throw new DaoException("Ticket details not found.");
        }
    }
}
