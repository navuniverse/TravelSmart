package com.travelportal.dao;

import java.util.List;

import com.travelportal.exception.DaoException;
import com.travelportal.model.ConnectorFsrModel;
import com.travelportal.model.DealModel;
import com.travelportal.model.FlightModel;

/**
 * 
 * DAO interface to manage all the DB related admin operations
 * 
 * @author naveen.kumar
 * 
 */
public interface IAdminDao {

    String DEAL_QUERY = "From DealModel";
    String UNCHECKED = "unchecked";
    String FLIGHT_ID = "flightId";
    String GET_SCHEDULE_QUERY = "From ConnectorFsrModel where flightId.flightId=:flightId";
    String CALL_ADD_NEW_SCHEDULE = "CALL ADD_NEW_SCHEDULE(:SCHEDULE_ID,:FLIGHT_DATE,:SOURCE_DEPARTURE_TIME,:VIA_ARRIVAL_TIME,:VIA_DEPARTURE_TIME,:DESTINATION_ARRIVAL_TIME,:FARE_SOURCE_DESTINATION,:DEAL_ID,:FARE_SOURCE_VIA,:FARE_VIA_DESTINATION,:AVAIL_ID,:FLIGHT_ID,:AVAIL_SOURCE_VIA,:AVAIL_VIA_DESTINATION,:AVAIL_SOURCE_DESTINATION,:ROUTE_ID,:CONNECTOR_ID)";
    String CALL_DELETE_SCHEDULE = "CALL DELETE_SCHEDULE(:SCHEDULE_I,:FLIGHT_DATE,:SOURCE_DEPARTURE_TIME,:VIA_ARRIVAL_TIME,:VIA_DEPARTURE_TIME,:DESTINATION_ARRIVAL_TIME,:FARE_SOURCE_DESTINATION,:DEAL_ID,:FARE_SOURCE_VIA,:FARE_VIA_DESTINATION,:AVAIL_I,:FLIGHT_ID,:AVAIL_SOURCE_VIA,:AVAIL_VIA_DESTINATION,:AVAIL_SOURCE_DESTINATION,:ROUTE_ID,:CONNECTOR_I)";
    String CALL_UPDATE_SCHEDULE = "CALL UPDATE_SCHEDULE(:SCHEDULE_I,:FLIGHT_DAT,:SOURCE_DEPARTURE_TIM,:VIA_ARRIVAL_TIM,:VIA_DEPARTURE_TIM,:DESTINATION_ARRIVAL_TIM,:FARE_SOURCE_DESTINATIO,:DEAL_I,:FARE_SOURCE_VI,:FARE_VIA_DESTINATIO,:AVAIL_I,:FLIGHT_I,:AVAIL_SOURCE_VI,:AVAIL_VIA_DESTINATIO,:AVAIL_SOURCE_DESTINATIO,:ROUTE_I,:CONNECTOR_I)";

    /**
     * 
     * DAO Method to add new flight
     * 
     * @param flightModel
     *            {@link FlightModel}
     * @return true {@link Boolean}
     * @throws DaoException
     */
    Boolean addFlight(FlightModel flightModel) throws DaoException;

    /**
     * 
     * DAO Method to delete a flight
     * 
     * @param flightModel
     *            {@link FlightModel}
     * @return true {@link Boolean}
     * @throws DaoException
     */
    Boolean deleteFlight(FlightModel flightModel) throws DaoException;

    /**
     * 
     * DAO Method to update a flight
     * 
     * @param flightModel
     *            {@link FlightModel}
     * @return true {@link Boolean}
     * @throws DaoException
     */
    Boolean updateFlight(FlightModel flightModel) throws DaoException;

    /**
     * 
     * DAO Method to add a new schedule from CSV by calling DB Stored Procedure
     * ADD_NEW_SCHEDULE
     * 
     * @param row
     *            {@link String} return true
     * @throws DaoException
     */
    boolean addSchedule(String[] row) throws DaoException;

    /**
     * 
     * DAO Method to update a schedule from CSV by calling DB Stored Procedure
     * UPDATE_SCHEDULE
     * 
     * @param row
     *            {@link String} return true
     * @throws DaoException
     */
    boolean updateSchedule(String row[]) throws DaoException;

    /**
     * 
     * DAO Method to delete a schedule from CSV by calling DB Stored Procedure
     * DELETE_SCHEDULE
     * 
     * @param row
     *            {@link String} return true
     * @throws DaoException
     */
    boolean deleteSchedule(String row[]) throws DaoException;

    /**
     * 
     * DAO Method to add a new deal
     * 
     * @param dealModel
     *            {@link DealModel}
     * @return true {@link Boolean}
     * @throws DaoException
     */
    boolean addDeal(DealModel dealModel) throws DaoException;

    /**
     * 
     * DAO Method to add a new deal
     * 
     * @param dealModel
     *            {@link DealModel}
     * @return true {@link Boolean}
     * @throws DaoException
     */
    boolean updateDeal(DealModel dealModel) throws DaoException;

    /**
     * 
     * DAO Method to add a new deal
     * 
     * @param dealModel
     *            {@link DealModel}
     * @return true {@link Boolean}
     * @throws DaoException
     */
    boolean deleteDeal(DealModel dealModel) throws DaoException;

    /**
     * 
     * DAO method to fetch the schedule providing a flightId
     * 
     * @param flightId
     *            {@link String}
     * @return connectorFsrModels {@link ConnectorFsrModel}
     * @throws DaoException
     */
    List<ConnectorFsrModel> getSchedule(String flightId) throws DaoException;

    /**
     * 
     * DAO Method to get all the deals available
     * 
     * @return dealModel {@link DealModel}
     * @throws DaoException
     */
    List<DealModel> getDeal() throws DaoException;

}