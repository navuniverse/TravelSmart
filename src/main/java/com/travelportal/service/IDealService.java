package com.travelportal.service;

import java.util.List;

import com.travelportal.dao.IAdminDao;
import com.travelportal.exception.ServiceLayerException;
import com.travelportal.model.DealModel;

/**
 * 
 * Service interface to manage deals
 * 
 * @author naveen.kumar
 * 
 */
public interface IDealService {

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
     * Service method to get the top deals
     * 
     * @return adminDao.getDeal() {@link DealModel}
     * @throws ServiceLayerException
     */
    List<DealModel> getDeals() throws ServiceLayerException;

}