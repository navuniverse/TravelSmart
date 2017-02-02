package com.travelportal.service;

import java.util.Date;
import java.util.List;

import com.travelportal.dao.ISearchDao;
import com.travelportal.exception.ServiceLayerException;
import com.travelportal.model.ConnectorFsrModel;

/**
 * 
 * Service interface to search a flight
 * 
 * @author naveen.kumar
 * 
 */
public interface ISearchService {

    String MINUTE_S = "minute(s)";
    String HOUR_S = "hour(s) ";
    int SIXTY = 60;
    long SIXTYTHOUSAND = 60000;
    String MS = ":00";

    /**
     * 
     * Setter for searchDao object
     * 
     * @param searchDao
     *            {@link ISearchDao}
     */
    void setSearchDao(ISearchDao searchDao);

    /**
     * 
     * Service method to search a flight
     * 
     * @param fromCity
     *            {@link String}
     * @param toCity
     *            {@link String}
     * @param travelDate
     *            {@link Date}
     * @return connectors {@link ConnectorFsrModel}
     * @throws ServiceLayerException
     */
    List<ConnectorFsrModel> search(String fromCity, String toCity,
            Date travelDate) throws ServiceLayerException;

    /**
     * 
     * Method to calculate the flight duration
     * 
     * @param connectors
     *            {@link ConnectorFsrModel}
     * @param fromCity
     *            {@link String}
     * @param toCity
     *            {@link String}
     */
    void durationCalculator(List<ConnectorFsrModel> connectors,
            String fromCity, String toCity);

}