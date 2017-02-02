/**
 * 
 */
package com.travelportal.service.test;

import static org.junit.Assert.assertEquals;

import java.security.NoSuchAlgorithmException;
import java.util.Date;

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
import com.travelportal.service.impl.RegistrationService;
import com.travelportal.util.test.HibernateUtil;

/**
 * @author naveen.kumar
 * 
 */
public class RegistrationServiceTest {

    protected static RegistrationService registrationService;
    protected static PasswordService passwordService;
    protected static UserModel userModel;
    protected static UserDao userDao;
    protected static SessionFactory sessionFactory;

    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        registrationService = new RegistrationService();
        passwordService = new PasswordService();
        userDao = new UserDao();
        userModel = new UserModel();
        sessionFactory = HibernateUtil.getSessionFactory();
        userDao.setSessionFactory(sessionFactory);
        registrationService.setPasswordService(passwordService);
        registrationService.setUserDao(userDao);
    }

    /**
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        registrationService = null;
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
     * {@link com.travelportal.service.impl.RegistrationService#setUserDao(com.travelportal.dao.IUserDao)}
     * .
     */
    @Test
    public void testSetUserDao() {
        registrationService.setUserDao(userDao);
        assertEquals(true, true);
    }

    /**
     * Test method for
     * {@link com.travelportal.service.impl.RegistrationService#register(com.travelportal.model.UserModel)}
     * .
     * 
     * @throws HibernateException
     * @throws ServiceLayerException
     * @throws NoSuchAlgorithmException
     */
    @SuppressWarnings("deprecation")
    @Test
    public void testRegister() throws HibernateException, ServiceLayerException {
        userModel.setFirstname("new");
        userModel.setLastname("new");
        userModel.setUsername("new@new.com");
        userModel.setMobileno("971655417");
        userModel.setPassword("12345");
        userModel.setRole("NORMAL");
        userModel.setDob(new Date("08/07/1992"));
        assertEquals(true, registrationService.register(userModel) != null);
    }

    /**
     * Test method for
     * {@link com.travelportal.service.impl.RegistrationService#encryptPassword(java.lang.String)}
     * .
     * 
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testEncryptPassword() throws NoSuchAlgorithmException {
        assertEquals(true, passwordService.encryptPassword("12345") != null);
    }

}
