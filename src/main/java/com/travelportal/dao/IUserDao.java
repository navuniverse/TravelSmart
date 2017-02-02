package com.travelportal.dao;

import java.util.List;

import com.travelportal.exception.DaoException;
import com.travelportal.model.TicketModel;
import com.travelportal.model.TravellerModel;
import com.travelportal.model.UserModel;

/**
 * 
 * DAO interface to manage all the DB related operations for a user like
 * registration, login, profile update, history etc.
 * 
 * @author naveen.kumar
 * 
 */
public interface IUserDao {

    String FROM_TICKET_MODEL = "From TicketModel where ticketId=:ticketId";
    String UPDATE_AVAILABILITY_MODEL3 = "update AvailabilityModel set availSourceDestination=availSourceDestination+:passenger, availViaDestination=availViaDestination+:passenger where flightId=:flightId";
    String UPDATE_AVAILABILITY_MODEL2 = "update AvailabilityModel set availSourceDestination=availSourceDestination+:passenger, availSourceVia=availSourceVia+:passenger where flightId=:flightId";
    String UPDATE_AVAILABILITY_MODEL1 = "update AvailabilityModel set availSourceDestination=availSourceDestination+:passenger, availViaDestination=availViaDestination+:passenger, availSourceVia=availSourceVia+:passenger where flightId=:flightId";
    String FLIGHT_ID = "flightId";
    String PASSENGER = "passenger";
    String FROM_TRAVELLER_MODEL = "From TravellerModel where ticketId=:ticketId";
    String FROM_CONNECTOR_FSR_MODEL = "From ConnectorFsrModel where connectorId=:connectorId";
    String CONNECTOR_ID = "connectorId";
    String HISTORY_QUERY = "From TicketModel where userId.username=:email";
    String EMAIL = "email";
    String TICKET_DETAILS_QUERY = "From TravellerModel where ticketId.ticketId=:ticketId";
    String TICKET_ID = "ticketId";
    String CANCEL_TICKET_QUERY = "update TicketModel set status='CANCELLED' where ticketId=:ticketId";
    String TRAVEL_HISTORY_QUERY = "From TicketModel where userId.username=:username";
    String UNCHECKED = "unchecked";
    String UPDATE_USER_QUERY = "update UserModel set password=:password where username=:username";
    String FORGET_PASSWORD_QUERY = "From UserModel where username=:username";
    String PASSWORD = "password";
    String USERNAME = "username";
    String LOGIN_QUERY = "From UserModel where username=:username";

    /**
     * 
     * DAO method to register a new user
     * 
     * @param userModel
     *            {@link UserModel}
     * @return true {@link Boolean}
     * @throws DaoException
     */
    Boolean insertUser(UserModel userModel) throws DaoException;

    /**
     * 
     * DAO method to retrieve a user at the time of login
     * 
     * @param userModel
     *            {@link UserModel}
     * @return user {@link UserModel}
     * @throws DaoException
     */
    UserModel getUser(UserModel userModel) throws DaoException;

    /**
     * 
     * DAO method to get user password
     * 
     * @param userId
     *            {@link String}
     * @return userModel {@link UserModel}
     * @throws DaoException
     */
    UserModel getPassword(String userId) throws DaoException;

    /**
     * 
     * DAO method to update user profile
     * 
     * @param userModel
     *            {@link UserModel}
     * @return true {@link Boolean}
     * @throws DaoException
     */
    Boolean updateUser(UserModel userModel) throws DaoException;

    /**
     * 
     * DAO method to get user travel history
     * 
     * @param userModel
     *            {@link UserModel}
     * @return ticketModel {@link TicketModel}
     * @throws DaoException
     */
    List<TicketModel> getHistory(UserModel userModel) throws DaoException;

    /**
     * 
     * DAO method to cancel a ticket
     * 
     * @param ticketId
     *            {@link Integer}
     * @return true {@link Boolean}
     * @throws DaoException
     */
    Boolean cancelTicket(int ticketId) throws DaoException;

    /**
     * 
     * DAO method to get user travel history
     * 
     * @param email
     *            {@link String}
     * @return ticketModel {@link TicketModel}
     * @throws DaoException
     */
    List<TicketModel> getHistory(String email) throws DaoException;

    /**
     * 
     * DAO method to get list of travellers per ticket
     * 
     * @param ticketId
     *            {@link Integer}
     * @return travellerModels {@link TravellerModel}
     * @throws DaoException
     */
    List<TravellerModel> getTicketDetails(int ticketId) throws DaoException;

}