/**
 * 
 */
package com.travelportal.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.travelportal.dao.IUserDao;
import com.travelportal.exception.DaoException;
import com.travelportal.exception.ServiceLayerException;
import com.travelportal.model.TicketModel;
import com.travelportal.model.UserModel;
import com.travelportal.service.IPasswordService;
import com.travelportal.service.IUserService;

/**
 * 
 * Service class for all user related operations
 * 
 * @author naveen.kumar
 * 
 */
public class UserService implements IUserService {

    private static Logger logger = Logger.getLogger(UserService.class);
    private IUserDao userDao;
    private IPasswordService passwordService;

    public void setPasswordService(IPasswordService passwordService) {
        this.passwordService = passwordService;
    }

    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * 
     * Service Method to get the user history
     * 
     * @param userModel
     *            {@link UserModel}
     * @return userDao.getHistory(userModel) {@link TicketModel}
     * @throws ServiceLayerException
     */
    public List<TicketModel> getUserHistory(UserModel userModel)
            throws ServiceLayerException {
        try {
            return userDao.getHistory(userModel);
        } catch (DaoException e) {
            logger.error(
                    String.format("%s USER HISTORY NOT FOUND",
                            userModel.getUsername()), e);
            throw new ServiceLayerException("User history not found");
        }
    }

    /**
     * 
     * Service Method to cancel a ticket
     * 
     * @param ticketId
     *            {@link Integer}
     * @throws HibernateException
     * @return true {@link Boolean}
     * @throws ServiceLayerException
     */
    public Boolean cancelTicket(int ticketId) throws ServiceLayerException {
        try {
            userDao.cancelTicket(ticketId);
            return true;
        } catch (DaoException ex) {
            logger.error(
                    String.format("TICKET %s NOT FOUND TO CANCEL", ticketId),
                    ex);
            throw new ServiceLayerException("No ticket found to cancel");
        }

    }

    /**
     * 
     * Service method to update user profile
     * 
     * @param userModel
     *            {@link UserModel}
     * @return true {@link Boolean}
     * @throws HibernateException
     * @throws ServiceLayerException
     */
    public Boolean updateUser(UserModel userModel) throws ServiceLayerException {
        try {
            userModel.setPassword(passwordService.encryptPassword(userModel
                    .getPassword()));
            return userDao.updateUser(userModel);
        } catch (DaoException ex) {
            logger.error(String.format("%s USER NOT FOUND TO UPDATE PROFILE",
                    userModel.getUsername()), ex);
            throw new ServiceLayerException("User Profile not found");
        }
    }

    /**
     * 
     * Service method to retrieve user password
     * 
     * @param userId
     *            {@link String}
     * @return user {@link UserModel}
     * @throws ServiceLayerException
     */
    public UserModel getUserPassword(String userId)
            throws ServiceLayerException {
        try {
            UserModel user = userDao.getPassword(userId);
            user.setPassword(passwordService.decryptPassword(user.getPassword()));
            return user;
        } catch (DaoException ex) {
            logger.error(String.format("%s USER PASSWORD NOT FOUND", userId),
                    ex);
            throw new ServiceLayerException("User password not found");
        }
    }
}
