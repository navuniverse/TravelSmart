/**
 * 
 */
package com.travelportal.service.impl;

import org.apache.log4j.Logger;

import com.travelportal.dao.IUserDao;
import com.travelportal.exception.DaoException;
import com.travelportal.exception.ServiceLayerException;
import com.travelportal.model.UserModel;
import com.travelportal.service.IPasswordService;
import com.travelportal.service.IRegistrationService;

/**
 * 
 * Service class for User Registration
 * 
 * @author naveen.kumar
 * 
 */
public class RegistrationService implements IRegistrationService {

    private static Logger logger = Logger.getLogger(RegistrationService.class);
    private IUserDao userDao;
    private IPasswordService passwordService;

    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }

    public void setPasswordService(IPasswordService passwordService) {
        this.passwordService = passwordService;
    }

    /**
     * 
     * Service method to register a new user
     * 
     * @param userModel
     *            {@link UserModel}
     * @return true {@link Boolean}
     * @throws ServiceLayerException
     */
    public Boolean register(UserModel userModel) throws ServiceLayerException {
        userModel.setPassword(passwordService.encryptPassword(userModel
                .getPassword()));
        userModel.setRole("NORMAL");
        try {
            return userDao.insertUser(userModel);
        } catch (DaoException ex) {
            logger.error(
                    String.format("%s USER ALREADY REGISTERED",
                            userModel.getUsername()), ex);
            throw new ServiceLayerException("User already Registered");
        }

    }

}
