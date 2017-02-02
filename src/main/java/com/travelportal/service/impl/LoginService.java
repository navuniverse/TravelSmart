/**
 * 
 */
package com.travelportal.service.impl;

import org.apache.log4j.Logger;

import com.travelportal.dao.IUserDao;
import com.travelportal.exception.DaoException;
import com.travelportal.exception.ServiceLayerException;
import com.travelportal.model.UserModel;
import com.travelportal.service.ILoginService;
import com.travelportal.service.IPasswordService;

/**
 * 
 * Service class to manage user login
 * 
 * @author naveen.kumar
 * 
 */
public class LoginService implements ILoginService {

    private static Logger logger = Logger.getLogger(LoginService.class);
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
     * Service method for user log in
     * 
     * @param userModel
     *            {@link UserModel}
     * @return userModel {@link UserModel}
     * @throws ServiceLayerException
     */
    public UserModel userLogin(UserModel userModel)
            throws ServiceLayerException {
        try {
            UserModel tempUserModel = userDao.getPassword(userModel
                    .getUsername());
            String decryptedPassword = passwordService
                    .decryptPassword(tempUserModel.getPassword());

            if (userModel.getPassword().equals(decryptedPassword)) {
                UserModel user = userDao.getUser(userModel);
                setProp(user, userModel);
                logger.isInfoEnabled();
                logger.info(String.format("%s USER SUCCESSFULLY LOGGED IN",
                        user.getUsername()));
            }
            return userModel;
        } catch (DaoException ex) {
            logger.error(
                    String.format("%s USER LOGIN FAILED",
                            userModel.getUsername()), ex);
            throw new ServiceLayerException("Invalid Username/Password");
        }
    }

    /**
     * 
     * Method to set the data of user on user login
     * 
     * @param tempUser
     *            {@link UserModel}
     * @param user
     *            {@link UserModel}
     */
    public void setProp(UserModel tempUser, UserModel user) {
        user.setFirstname(tempUser.getFirstname());
        user.setLastname(tempUser.getLastname());
        user.setMobileno(tempUser.getMobileno());
        user.setDob(tempUser.getDob());
        user.setRole(tempUser.getRole());
    }
}
