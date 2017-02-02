/**
 * 
 */
package com.travelportal.service.test;

import static org.junit.Assert.assertEquals;

import java.security.NoSuchAlgorithmException;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.travelportal.dao.impl.UserDao;
import com.travelportal.exception.ServiceLayerException;
import com.travelportal.model.UserModel;
import com.travelportal.service.impl.PasswordService;
import com.travelportal.service.impl.UserService;
import com.travelportal.util.test.HibernateUtil;

/**
 * @author naveen.kumar
 * 
 */
public class UserServiceTest {

    protected static UserService userService;
    protected static PasswordService passwordService;
    protected static UserDao userDao;
    protected static UserModel userModel;
    protected static SessionFactory sessionFactory;

    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        userDao = new UserDao();
        userService = new UserService();
        passwordService = new PasswordService();
        userModel = new UserModel();
        sessionFactory = HibernateUtil.getSessionFactory();
        userDao.setSessionFactory(sessionFactory);
        userService.setUserDao(userDao);
        userService.setPasswordService(passwordService);
    }

    /**
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        userDao = null;
        userService = null;
        passwordService = null;
        userModel = null;
        sessionFactory = null;
    }

    /**
     * After.
     */
    @After
    public void after() {
        if (sessionFactory.getCurrentSession().getTransaction().isActive()) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
        }
    }

    /**
     * Test method for
     * {@link com.travelportal.service.impl.UserService#setRegistrationService(com.travelportal.service.IRegistrationService)}
     * .
     */
    @Test
    public void testSetRegistrationService() {
        userService.setPasswordService(passwordService);
        assertEquals(true, true);
    }

    /**
     * Test method for
     * {@link com.travelportal.service.impl.UserService#setUserDao(com.travelportal.dao.IUserDao)}
     * .
     */
    @Test
    public void testSetUserDao() {
        userService.setUserDao(userDao);
        assertEquals(true, true);
    }

    /**
     * Test method for
     * {@link com.travelportal.service.impl.UserService#getUserHistory(com.travelportal.model.UserModel)}
     * .
     * 
     * @throws ServiceLayerException
     */
    @Test
    public void testGetUserHistory() throws ServiceLayerException {
        userModel.setUsername("naveen.kumar@impetus.co.in");
        assertEquals(true, userService.getUserHistory(userModel).size() > 0);
    }

    /**
     * Test method for
     * {@link com.travelportal.service.impl.UserService#cancelTicket(int)}.
     * 
     * @throws ServiceLayerException
     */
    @Test
    public void testCancelTicket() throws ServiceLayerException {
        assertEquals(true, userService.cancelTicket(1));
    }

    /**
     * Test method for
     * {@link com.travelportal.service.impl.UserService#updateUser(com.travelportal.model.UserModel)}
     * .
     * 
     * @throws HibernateException
     * @throws NoSuchAlgorithmException
     * @throws ServiceLayerException
     */
    @Test
    public void testUpdateUser() throws NoSuchAlgorithmException,
            HibernateException, ServiceLayerException {
        userModel.setPassword("12345");
        userModel.setUsername("naveen.kumar@impetus.co.in");
        assertEquals(true, userService.updateUser(userModel));
    }

    /**
     * Test method for
     * {@link com.travelportal.service.impl.UserService#getUserPassword(java.lang.String)}
     * .
     * 
     * @throws ServiceLayerException
     */
    @Test
    public void testGetUserPassword() throws ServiceLayerException {
        assertEquals("NORMAL",
                userService.getUserPassword("naveen.kumar@impetus.co.in")
                        .getRole());
    }

}
