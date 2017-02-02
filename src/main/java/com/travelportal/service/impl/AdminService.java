/**
 * 
 */
package com.travelportal.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.travelportal.dao.IAdminDao;
import com.travelportal.exception.DaoException;
import com.travelportal.exception.ServiceLayerException;
import com.travelportal.model.ConnectorFsrModel;
import com.travelportal.model.FlightModel;
import com.travelportal.service.IAdminService;

/**
 * 
 * Service class to perform all the admin functionalities
 * 
 * @author naveen.kumar
 * 
 */
public class AdminService implements IAdminService {

    private static Logger logger = Logger.getLogger(AdminService.class);
    private IAdminDao adminDao;

    public void setAdminDao(IAdminDao adminDao) {
        this.adminDao = adminDao;
    }

    /**
     * 
     * Service method to add a new flight
     * 
     * @param flightModel
     *            {@link FlightModel}
     * @return true {@link Boolean}
     * @throws ServiceLayerException
     */
    public Boolean addFlight(FlightModel flightModel)
            throws ServiceLayerException {
        try {
            adminDao.addFlight(flightModel);
            return true;
        } catch (DaoException ex) {
            logger.error(
                    String.format("Flight %s not added",
                            flightModel.getFlightId()), ex);
            throw new ServiceLayerException("Adding new flight failed");
        }
    }

    /**
     * 
     * Service method to update an existing flight
     * 
     * @param flightModel
     *            {@link FlightModel}
     * @return true {@link Boolean}
     * @throws DaoException
     * @throws ServiceLayerException
     */
    public Boolean updateFlight(FlightModel flightModel)
            throws ServiceLayerException {
        try {
            adminDao.updateFlight(flightModel);
            return true;
        } catch (DaoException ex) {
            logger.error(
                    String.format("Flight %s not updated",
                            flightModel.getFlightId()), ex);
            throw new ServiceLayerException("Updating flight failed");
        }

    }

    /**
     * 
     * Service method to delete a flight
     * 
     * @param flightModel
     *            {@link FlightModel}
     * @throws HibernateException
     * @return true {@link Boolean}
     * @throws DaoException
     * @throws ServiceLayerException
     */
    public Boolean deleteFlight(FlightModel flightModel)
            throws ServiceLayerException {
        try {
            adminDao.deleteFlight(flightModel);
            return true;
        } catch (DaoException ex) {
            logger.error(
                    String.format("Flight %s not deleted",
                            flightModel.getFlightId()), ex);
            throw new ServiceLayerException("Deleting flight failed");
        }

    }

    /**
     * 
     * Service method to get the schedule of a flight
     * 
     * @param flightId
     *            {@link String}
     * @return fsrModels {@link ConnectorFsrModel}
     * @throws ServiceLayerException
     */
    public List<ConnectorFsrModel> getSchedule(String flightId)
            throws ServiceLayerException {
        try {
            List<ConnectorFsrModel> fsrModels = (List<ConnectorFsrModel>) adminDao
                    .getSchedule(flightId);
            return fsrModels;
        } catch (DaoException ex) {
            logger.error(String.format("SCHEDULE OF FLIGHT %s NOT AVAILABLE",
                    flightId), ex);
            throw new ServiceLayerException("Flight schedule not found");
        }
    }
}
