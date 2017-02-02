package com.travelportal.service;

import com.travelportal.dao.IUserDao;
import com.travelportal.exception.ServiceLayerException;
import com.travelportal.model.UserModel;

/**
 * 
 * Service interface for User Registration
 * 
 * @author naveen.kumar
 * 
 */
public interface IRegistrationService {

    int I_0XFF = 0xff;

    /**
     * 
     * Setter for userDao object
     * 
     * @param userDao
     *            {@link IUserDao}
     */
    void setUserDao(IUserDao userDao);

    /**
     * 
     * Service method to register a new user
     * 
     * @param userModel
     *            {@link UserModel}
     * @return true {@link Boolean}
     * @throws ServiceLayerException
     */
    Boolean register(UserModel userModel) throws ServiceLayerException;

}