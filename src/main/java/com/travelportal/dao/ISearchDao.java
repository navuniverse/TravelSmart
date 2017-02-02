package com.travelportal.dao;

import java.util.Date;
import java.util.List;

import com.travelportal.exception.DaoException;
import com.travelportal.model.ConnectorFsrModel;

/**
 * 
 * DAO interface to manage all the DB related operations during flight search
 * 
 * @author naveen.kumar
 * 
 */
public interface ISearchDao {

    String UNCHECKED = "unchecked";
    String TO_CITY = "toCity";
    String FROM_CITY = "fromCity";
    String TRAVEL_DATE = "travelDate";
    String SEARCH_QUERY = "from ConnectorFsrModel c where (c.scheduleId.flightDate=:travelDate) and ((c.routeId.source=:fromCity and c.routeId.destination=:toCity) or (c.routeId.source=:fromCity and c.routeId.via=:toCity) or (c.routeId.via=:fromCity and c.routeId.destination=:toCity))";

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
    List<ConnectorFsrModel> searchFlight(String from, String to, Date date)
            throws DaoException;

}