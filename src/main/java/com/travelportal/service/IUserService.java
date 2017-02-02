package com.travelportal.service;

import java.util.List;

import com.travelportal.dao.IUserDao;
import com.travelportal.exception.ServiceLayerException;
import com.travelportal.model.TicketModel;
import com.travelportal.model.UserModel;

/**
 * 
 * Service interface for all user related operations
 * 
 * @author naveen.kumar
 * 
 */
public interface IUserService {

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
     * Service Method to get the user history
     * 
     * @param userModel
     *            {@link UserModel}
     * @return userDao.getHistory(userModel) {@link TicketModel}
     * @throws ServiceLayerException
     */
    List<TicketModel> getUserHistory(UserModel userModel)
            throws ServiceLayerException;

    /**
     * 
     * Service Method to cancel a ticket
     * 
     * @param ticketId
     *            {@link Integer}
     * @return true {@link Boolean}
     * @throws ServiceLayerException
     */
    Boolean cancelTicket(int ticketId) throws ServiceLayerException;

    /**
     * 
     * Service method to update user profile
     * 
     * @param userModel
     *            {@link UserModel}
     * @return true {@link Boolean}
     * @throws ServiceLayerException
     */
    Boolean updateUser(UserModel userModel) throws ServiceLayerException;

    /**
     * 
     * Service method to retrieve user password
     * 
     * @param userId
     *            {@link String}
     * @return user {@link UserModel}
     * @throws ServiceLayerException
     */
    UserModel getUserPassword(String userId) throws ServiceLayerException;

}