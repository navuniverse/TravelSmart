/**
 * 
 */
package com.travelportal.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.travelportal.dao.IAdminDao;
import com.travelportal.exception.DaoException;
import com.travelportal.exception.ServiceLayerException;
import com.travelportal.model.DealModel;
import com.travelportal.service.IDealService;

/**
 * 
 * Service class to manage deals
 * 
 * @author naveen.kumar
 * 
 */
public class DealService implements IDealService {

    private static Logger logger = Logger.getLogger(DealService.class);
    private IAdminDao adminDao;

    public void setAdminDao(IAdminDao adminDao) {
        this.adminDao = adminDao;
    }

    /**
     * 
     * Service method to get the top deals
     * 
     * @return adminDao.getDeal() {@link DealModel}
     * @throws ServiceLayerException
     */
    public List<DealModel> getDeals() throws ServiceLayerException {
        try {
            return adminDao.getDeal();
        } catch (DaoException ex) {
            logger.error("DEAL NOT FOUND", ex);
            throw new ServiceLayerException("No deal Found");
        }
    }
}
