package com.travelportal.service;

import java.util.List;

import com.travelportal.dao.IAdminDao;
import com.travelportal.exception.ServiceLayerException;
import com.travelportal.model.ConnectorFsrModel;
import com.travelportal.model.FlightModel;

/**
 * 
 * Service interface to perform all the admin functionalities
 * 
 * @author naveen.kumar
 * 
 */
public interface IAdminService {

    /**
     * 
     * Setter for adminDao object
     * 
     * @param adminDao
     *            {@link IAdminDao}
     */
    void setAdminDao(IAdminDao adminDao);

    /**
     * 
     * Service method to add a new flight
     * 
     * @param flightModel
     *            {@link FlightModel}
     * @return true {@link Boolean}
     * @throws ServiceLayerException
     */
    Boolean addFlight(FlightModel flightModel) throws ServiceLayerException;

    /**
     * 
     * Service method to update an existing flight
     * 
     * @param flightModel
     *            {@link FlightModel}
     * @return true {@link Boolean}
     * @throws ServiceLayerException
     */
    Boolean updateFlight(FlightModel flightModel) throws ServiceLayerException;

    /**
     * 
     * Service method to delete a flight
     * 
     * @param flightModel
     *            {@link FlightModel}
     * @return true {@link Boolean}
     * @throws ServiceLayerException
     */
    Boolean deleteFlight(FlightModel flightModel) throws ServiceLayerException;

    /**
     * 
     * Service method to get the schedule of a flight
     * 
     * @param flightId
     *            {@link String}
     * @return fsrModels {@link ConnectorFsrModel}
     * @throws ServiceLayerException
     */
    List<ConnectorFsrModel> getSchedule(String flightId)
            throws ServiceLayerException;

}