package com.travelportal.service;

import java.util.List;

import com.travelportal.dao.IRouteDao;
import com.travelportal.exception.ServiceLayerException;

/**
 * 
 * Service interface to dynamically fetch the list of available routes
 * 
 * @author naveen.kumar
 * 
 */
public interface IRouteService {

    /**
     * 
     * Setter for routeDao object
     * 
     * @param routeDao
     *            {@link IRouteDao}
     */
    void setRouteDao(IRouteDao routeDao);

    /**
     * 
     * Service method to get the list of source cities
     * 
     * @return routeDao.getSourceCities() {@link String}
     * @throws ServiceLayerException
     */
    List<String> getSourceCities() throws ServiceLayerException;

    /**
     * 
     * Service method to get the list of destination cities corresponding to
     * source city
     * 
     * @param fromCity
     *            {@link String}
     * @return routeDao .getDestinationCities(fromCity) {@link String}
     * @throws ServiceLayerException
     */
    List<String> getDestinationCities(String fromCity)
            throws ServiceLayerException;

}