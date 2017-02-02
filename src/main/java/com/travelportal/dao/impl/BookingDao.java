package com.travelportal.dao.impl;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DataRetrievalFailureException;

import com.travelportal.dao.IBookingDao;
import com.travelportal.exception.DaoException;
import com.travelportal.model.ConnectorFsrModel;
import com.travelportal.model.FlightModel;
import com.travelportal.model.PaymentModel;
import com.travelportal.model.TicketModel;
import com.travelportal.model.TravellerModel;

/**
 * 
 * DAO class to manage all the DB related operations during flight booking
 * process
 * 
 * @author naveen.kumar
 * 
 */
public class BookingDao implements IBookingDao {

    private Logger logger = Logger.getLogger(BookingDao.class);
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * 
     * DAO Method to search a flight Returns the connector id
     * 
     * @param connectorId
     *            {@link String}
     * @return connectorFsrModel {@link ConnectorFsrModel}
     * @throws DaoException
     */
    public ConnectorFsrModel getFlight(String connectorId) throws DaoException {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            logger.isInfoEnabled();
            logger.info(String.format("SEARCHING FLIGHT WITH CONNECTOR ID %s",
                    connectorId));
            ConnectorFsrModel connectorFsrModel = (ConnectorFsrModel) session
                    .createQuery(GET_FLIGHT_QUERY)
                    .setString(CONNECTOR_ID, connectorId).uniqueResult();
            tx.commit();
            return connectorFsrModel;
        } catch (DataRetrievalFailureException ex) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            logger.error(String.format("NO FLIGHT FOUND WITH CONNECTOR ID %s",
                    connectorId), ex);
            throw new DaoException("No flight found during search");
        }
    }

    /**
     * 
     * DAO Method to book a flight when searched from source to destination of
     * flight Also updates the seat availability according to no of passengers
     * 
     * @param connectorId
     *            {@link String}
     * @param passenger
     *            {@link Integer}
     * @return connectorFsrModel {@link ConnectorFsrModel}
     * @throws DaoException
     */
    public ConnectorFsrModel bookFlight1(String connectorId, int passenger)
            throws DaoException {
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();
            ConnectorFsrModel connectorFsrModel = (ConnectorFsrModel) session
                    .createQuery(BOOK1_CONNECTOR_QUERY)
                    .setString(CONNECTOR_ID, connectorId)
                    .setInteger(PASSENGER, passenger).uniqueResult();

            Object[] objects = (Object[]) session
                    .createQuery(BOOK_FLIGHT_CONNECTOR_QUERY)
                    .setString(CONNECTOR_ID, connectorId).uniqueResult();
            if (objects == null) {
                throw new HibernateException(ERROR);
            } else {
                FlightModel flightModel = (FlightModel) objects[0];
                logger.isInfoEnabled();
                logger.info(String.format("RESERVING YOUR SEATS IN %s",
                        flightModel.getFlightId()));
                if (flightModel.getFlightType().equalsIgnoreCase("stop")) {
                    session.createQuery(BOOK1_FLIGHT_QUERY_STOP)
                            .setInteger(PASSENGER, passenger)
                            .setString(FLIGHT_ID, flightModel.getFlightId())
                            .executeUpdate();
                } else {
                    session.createQuery(BOOK1_FLIGHT_QUERY)
                            .setInteger(PASSENGER, passenger)
                            .setString(FLIGHT_ID, flightModel.getFlightId())
                            .executeUpdate();
                }
                return connectorFsrModel;
            }
        } catch (DataAccessException ex) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            logger.error(
                    String.format(
                            "ERROR WHILE UPDATING AVAILABILITY OF FLIGHT WITH CONNECTOR ID ",
                            connectorId), ex);
            throw new DaoException("Cannot update the flight availability.");
        }
    }

    /**
     * 
     * DAO Method to book a flight when searched from source to via of flight
     * Also updates the seat availability according to no of passengers
     * 
     * @param connectorId
     *            {@link String}
     * @param passenger
     *            {@link Integer}
     * @return connectorFsrModel {@link ConnectorFsrModel}
     * @throws DaoException
     */
    public ConnectorFsrModel bookFlight2(String connectorId, int passenger)
            throws DaoException {
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();
            ConnectorFsrModel connectorFsrModel = (ConnectorFsrModel) session
                    .createQuery(BOOK2_CONEECTOR_QUERY)
                    .setString(CONNECTOR_ID, connectorId)
                    .setInteger(PASSENGER, passenger).uniqueResult();

            Object[] objects = (Object[]) session
                    .createQuery(BOOK_FLIGHT_CONNECTOR_QUERY)
                    .setString(CONNECTOR_ID, connectorId).uniqueResult();
            if (objects == null) {
                throw new HibernateException(ERROR);
            } else {
                FlightModel flightModel = (FlightModel) objects[0];
                logger.isInfoEnabled();
                logger.info(String.format("RESERVING YOUR SEATS IN %s",
                        flightModel.getFlightId()));
                session.createQuery(BOOK_FLIGHT2_QUERY)
                        .setInteger(PASSENGER, passenger)
                        .setString(FLIGHT_ID, flightModel.getFlightId())
                        .executeUpdate();
                return connectorFsrModel;
            }
        } catch (DataAccessException ex) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            logger.error(
                    String.format(
                            "ERROR WHILE UPDATING AVAILABILITY OF FLIGHT WITH CONNECTOR ID ",
                            connectorId), ex);
            throw new DaoException("Cannot update the flight availability.");
        }
    }

    /**
     * 
     * DAO Method to book a flight when searched from via to destination of
     * flight Also updates the seat availability according to no of passengers
     * 
     * @param connectorId
     *            {@link String}
     * @param passenger
     *            {@link Integer}
     * @return connectorFsrModel {@link ConnectorFsrModel}
     * @throws DaoException
     */
    public ConnectorFsrModel bookFlight3(String connectorId, int passenger)
            throws DaoException {
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();
            ConnectorFsrModel connectorFsrModel = (ConnectorFsrModel) session
                    .createQuery(BOOK3_CONNECTOR_QUERY)
                    .setString(CONNECTOR_ID, connectorId)
                    .setInteger(PASSENGER, passenger).uniqueResult();

            Object[] objects = (Object[]) session
                    .createQuery(BOOK_FLIGHT_CONNECTOR_QUERY)
                    .setString(CONNECTOR_ID, connectorId).uniqueResult();
            if (objects == null) {
                throw new HibernateException(ERROR);
            } else {
                FlightModel flightModel = (FlightModel) objects[0];
                logger.isInfoEnabled();
                logger.info(String.format("RESERVING YOUR SEATS IN %s",
                        flightModel.getFlightId()));
                session.createQuery(BOOK_FLIGHT3_QUERY)
                        .setInteger(PASSENGER, passenger)
                        .setString(FLIGHT_ID, flightModel.getFlightId())
                        .executeUpdate();
                return connectorFsrModel;
            }
        } catch (DataAccessException ex) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            logger.error(
                    String.format(
                            "ERROR WHILE UPDATING AVAILABILITY OF FLIGHT WITH CONNECTOR ID ",
                            connectorId), ex);
            throw new DaoException("Cannot update the flight availability.");
        }
    }

    /**
     * 
     * DAO Method to generate the ticket by passing user data
     * 
     * @param ticketModel
     *            {@link TicketModel}
     * @return ticketModel.getTicketId() {@link Integer}
     * @throws DaoException
     */
    public int passdata(TicketModel ticketModel) throws DaoException {
        Session session = sessionFactory.getCurrentSession();
        try {
            session.save(ticketModel);
            logger.isInfoEnabled();
            logger.info(String.format("TICKET WITH ID %s GENERATED",
                    ticketModel.getTicketId()));
            return ticketModel.getTicketId();
        } catch (DataIntegrityViolationException ex) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            logger.error(String.format(
                    "TICKET %s NOT GENERATED. ALREADY EXISTS.",
                    ticketModel.getTicketId()), ex);
            throw new DaoException(
                    "Ticket generation failed. Invalid ticket ID");
        }
    }

    /**
     * 
     * DAO Method to insert traveller details
     * 
     * @param model2
     *            {@link TravellerModel}
     * @return true {@link Boolean}
     * @throws DaoException
     */
    public boolean insertTraveller(TravellerModel model2) throws DaoException {
        Session session = sessionFactory.getCurrentSession();
        try {
            session.save(model2);
            logger.isInfoEnabled();
            logger.info(String.format(
                    "TRAVELLER CORRESPONDING TO TICKET ID %s INSERTED", model2
                            .getTicketId().getTicketId()));
            return true;
        } catch (DataIntegrityViolationException ex) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            logger.error(
                    String.format(
                            "ERROR IN INSERTING TRAVELLER CORRESPONDING TO TICKET ID %s",
                            model2.getTicketId().getTicketId()), ex);
            throw new DaoException("Traveller data insertion failed");
        }
    }

    /**
     * 
     * DAO method to store the payment details to DB
     * 
     * @param paymentModel
     *            {@link PaymentModel}
     * @return true {@link Boolean}
     * @throws DaoException
     */
    public boolean payment(PaymentModel paymentModel) throws DaoException {
        Session session = sessionFactory.getCurrentSession();
        try {
            session.save(paymentModel);
            session.getTransaction().commit();
            logger.isInfoEnabled();
            logger.info(String.format("PAYMENT DETAILS INSERTED WITH ID %s",
                    paymentModel.getPaymentId()));
            return true;
        } catch (DataIntegrityViolationException ex) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            logger.error(
                    String.format(
                            "ERROR IN INSERTING PAYMENT DETAILS CORRESPONDING TO TICKET ID %s",
                            paymentModel.getTicketId()), ex);
            throw new DaoException("Payment data insertion failed");
        }
    }

    /**
     * 
     * DAO method to fetch ticket data for ticket generation
     * 
     * @param ticketId
     *            {@link Integer}
     * @return ticketModel {@link TicketModel}
     * @throws DaoException
     */
    public TicketModel ticket(int ticketId) throws DaoException {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            TicketModel ticketModel = (TicketModel) session
                    .createQuery(FROM_TICKET_MODEL)
                    .setInteger(TICKET_ID, ticketId).uniqueResult();
            tx.commit();
            return ticketModel;
        } catch (DataRetrievalFailureException ex) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            logger.error(
                    String.format("NO DATA FOUND FOR TICKET ID %s", ticketId),
                    ex);
            throw new DaoException("No data found for ticket history");
        }
    }
}
