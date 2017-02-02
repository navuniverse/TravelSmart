package com.travelportal.service;

import com.travelportal.dao.IUserDao;
import com.travelportal.exception.ServiceLayerException;
import com.travelportal.model.UserModel;

/**
 * 
 * Service interface to manage user login
 * 
 * @author naveen.kumar
 * 
 */
public interface ILoginService {

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
     * Service method for user log in
     * 
     * @param userModel
     *            {@link UserModel}
     * @return userModel {@link UserModel}
     * @throws ServiceLayerException
     */
    UserModel userLogin(UserModel userModel) throws ServiceLayerException;

    /**
     * 
     * Method to set the data of user on user login
     * 
     * @param tempUser
     *            {@link UserModel}
     * @param user
     *            {@link UserModel}
     */
    void setProp(UserModel tempUser, UserModel user);

}