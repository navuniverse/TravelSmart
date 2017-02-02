/**
 * 
 */
package com.travelportal.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.travelportal.dao.IRouteDao;
import com.travelportal.exception.DaoException;
import com.travelportal.exception.ServiceLayerException;
import com.travelportal.service.IRouteService;

/**
 * 
 * Service class to dynamically fetch the list of available routes
 * 
 * @author naveen.kumar
 * 
 */
public class RouteService implements IRouteService {

    private static Logger logger = Logger.getLogger(RouteService.class);
    private IRouteDao routeDao;

    public void setRouteDao(IRouteDao routeDao) {
        this.routeDao = routeDao;
    }

    /**
     * 
     * Service method to get the list of source cities
     * 
     * @return routeDao.getSourceCities() {@link String}
     * @throws ServiceLayerException
     */
    public List<String> getSourceCities() throws ServiceLayerException {
        try {
            return routeDao.getSourceCities();
        } catch (DaoException ex) {
            logger.error("NO SOURCE CITY FOUND", ex);
            throw new ServiceLayerException("No source city found");
        }
    }

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
    public List<String> getDestinationCities(String fromCity)
            throws ServiceLayerException {
        try {
            return routeDao.getDestinationCities(fromCity);
        } catch (DaoException ex) {
            logger.error(String.format(
                    "NO DESTINATION CITY CORRESPONDING TO %s", fromCity), ex);
            throw new ServiceLayerException("No destination city found");
        }
    }
}
