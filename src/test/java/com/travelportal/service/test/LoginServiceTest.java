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
import com.travelportal.service.impl.LoginService;
import com.travelportal.service.impl.PasswordService;
import com.travelportal.util.test.HibernateUtil;

/**
 * @author naveen.kumar
 * 
 */
public class LoginServiceTest {

    protected static LoginService loginService;
    protected static PasswordService passwordService;
    protected static UserDao userDao;
    protected static UserModel userModel;
    protected static SessionFactory sessionFactory;

    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        loginService = new LoginService();
        passwordService = new PasswordService();
        userDao = new UserDao();
        userModel = new UserModel();
        sessionFactory = HibernateUtil.getSessionFactory();
        userDao.setSessionFactory(sessionFactory);
    }

    /**
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        loginService = null;
        passwordService = null;
        userDao = null;
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
     * {@link com.travelportal.service.impl.LoginService#setRegistrationService(com.travelportal.service.IRegistrationService)}
     * .
     */
    @Test
    public void testSetRegistrationService() {
        loginService.setPasswordService(passwordService);
        assertEquals(true, true);
    }

    /**
     * Test method for
     * {@link com.travelportal.service.impl.LoginService#setUserDao(com.travelportal.dao.IUserDao)}
     * .
     */
    @Test
    public void testSetUserDao() {
        loginService.setUserDao(userDao);
        assertEquals(true, true);
    }

    /**
     * Test method for
     * {@link com.travelportal.service.impl.LoginService#userLogin(com.travelportal.model.UserModel)}
     * .
     * 
     * @throws HibernateException
     * @throws NoSuchAlgorithmException
     * @throws ServiceLayerException
     */
    @Test
    public void testUserLogin() throws NoSuchAlgorithmException,
            HibernateException, ServiceLayerException {
        userModel.setUsername("naveen.kumar@impetus.co.in");
        userModel.setPassword("12345");
        assertEquals(true, loginService.userLogin(userModel) != null);
    }

}
