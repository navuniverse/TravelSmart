package com.travelportal.dao;

import com.travelportal.exception.DaoException;
import com.travelportal.model.ConnectorFsrModel;
import com.travelportal.model.PaymentModel;
import com.travelportal.model.TicketModel;
import com.travelportal.model.TravellerModel;

/**
 * 
 * DAO interface to manage all the DB related operations during flight booking
 * process
 * 
 * @author naveen.kumar
 * 
 */
public interface IBookingDao {

    String FROM_TICKET_MODEL = "From TicketModel where ticketId=:ticketId";
    String TICKET_ID = "ticketId";
    String BOOK1_FLIGHT_QUERY = "update AvailabilityModel set availSourceDestination=availSourceDestination-:passenger where flightId=:flightId";
    String BOOK1_FLIGHT_QUERY_STOP = "update AvailabilityModel set availSourceDestination=availSourceDestination-:passenger, availViaDestination=availViaDestination-:passenger, availSourceVia=availSourceVia-:passenger where flightId=:flightId";
    String BOOK_FLIGHT2_QUERY = "update AvailabilityModel set availSourceDestination=availSourceDestination-:passenger, availSourceVia=availSourceVia-:passenger where flightId=:flightId";
    String BOOK_FLIGHT3_QUERY = "update AvailabilityModel set availSourceDestination=availSourceDestination-:passenger, availViaDestination=availViaDestination-:passenger where flightId=:flightId";
    String FLIGHT_ID = "flightId";
    String BOOK_FLIGHT_CONNECTOR_QUERY = "From FlightModel f,ConnectorFsrModel c where (f.flightId=c.flightId.flightId) and c.connectorId=:connectorId";
    String BOOK3_CONNECTOR_QUERY = "From ConnectorFsrModel c where connectorId=connectorId and c.availId.availViaDestination>=passenger";
    String ERROR = "No Flight Available";
    String BOOK2_CONEECTOR_QUERY = "From ConnectorFsrModel c where connectorId=connectorId and c.availId.availSourceVia>=passenger";
    String BOOK1_CONNECTOR_QUERY = "From ConnectorFsrModel c where connectorId=:connectorId and c.availId.availSourceDestination>=:passenger";
    String PASSENGER = "passenger";
    String CONNECTOR_ID = "connectorId";
    String GET_FLIGHT_QUERY = "From ConnectorFsrModel c where connectorId=:connectorId";

    /**
     * 
     * DAO Method to search a flight Returns the connector id
     * 
     * @param connectorId
     *            {@link String}
     * @return connectorFsrModel {@link ConnectorFsrModel}
     * @throws DaoException
     */
    ConnectorFsrModel getFlight(String connectorId) throws DaoException;

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
    ConnectorFsrModel bookFlight1(String connectorId, int passenger)
            throws DaoException;

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
    ConnectorFsrModel bookFlight2(String connectorId, int passenger)
            throws DaoException;

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
    ConnectorFsrModel bookFlight3(String connectorId, int passenger)
            throws DaoException;

    /**
     * 
     * DAO Method to generate the ticket by passing user data
     * 
     * @param ticketModel
     *            {@link TicketModel}
     * @return ticketModel.getTicketId() {@link Integer}
     * @throws DaoException
     */
    int passdata(TicketModel ticketModel) throws DaoException;

    /**
     * 
     * DAO Method to insert traveller details
     * 
     * @param model2
     *            {@link TravellerModel}
     * @return true {@link Boolean}
     * @throws DaoException
     */
    boolean insertTraveller(TravellerModel model2) throws DaoException;

    /**
     * 
     * DAO method to store the payment details to DB
     * 
     * @param paymentModel
     *            {@link PaymentModel}
     * @return true {@link Boolean}
     * @throws DaoException
     */
    boolean payment(PaymentModel paymentModel) throws DaoException;

    /**
     * 
     * DAO method to fetch ticket data for ticket generation
     * 
     * @param ticketId
     *            {@link Integer}
     * @return ticketModel {@link TicketModel}
     * @throws DaoException
     */
    TicketModel ticket(int ticketId) throws DaoException;

}