/**
 * 
 */
package com.travelportal.controller.test;

import static org.junit.Assert.assertEquals;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.ui.Model;

import com.travelportal.controller.RegisterController;
import com.travelportal.dao.impl.UserDao;
import com.travelportal.model.UserModel;
import com.travelportal.service.impl.MailService;
import com.travelportal.service.impl.PasswordService;
import com.travelportal.service.impl.RegistrationService;
import com.travelportal.util.test.HibernateUtil;

/**
 * 
 * Test class of {@link RegisterController}
 * 
 * @author naveen.kumar
 * 
 */
public class TestRegisterController {

    protected static RegisterController registerController;
    protected static RegistrationService registrationService;
    protected static MailService mailService;
    protected static PasswordService passwordService;
    protected static UserDao userDao;
    protected static SessionFactory sessionFactory;
    protected static UserModel userModel;
    protected static Model model;

    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        registerController = new RegisterController();
        registrationService = new RegistrationService();
        mailService = new MailService();
        passwordService = new PasswordService();
        userDao = new UserDao();
        userModel = new UserModel();
        sessionFactory = HibernateUtil.getSessionFactory();
        registerController.setMailService(mailService);
        registerController.setRegistrationService(registrationService);
        registrationService.setUserDao(userDao);
        registrationService.setPasswordService(passwordService);
        userDao.setSessionFactory(sessionFactory);
        model = new Model() {

            public Model mergeAttributes(Map<String, ?> arg0) {
                return null;
            }

            public boolean containsAttribute(String arg0) {
                return false;
            }

            public Map<String, Object> asMap() {
                return null;
            }

            public Model addAttribute(String arg0, Object arg1) {
                return null;
            }

            public Model addAttribute(Object arg0) {
                return null;
            }

            public Model addAllAttributes(Map<String, ?> arg0) {
                return null;
            }

            public Model addAllAttributes(Collection<?> arg0) {
                return null;
            }
        };
    }

    /**
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        registerController = null;
        registrationService = null;
        mailService = null;
        passwordService = null;
        userDao = null;
        sessionFactory = null;
        model = null;
        userModel = null;
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
     * {@link com.travelportal.controller.RegisterController#setRegistrationService(com.travelportal.service.IRegistrationService)}
     * .
     */
    @Test
    public void testSetRegistrationService() {
        registerController.setRegistrationService(registrationService);
        assertEquals(true, true);
    }

    /**
     * Test method for
     * {@link com.travelportal.controller.RegisterController#setMailService(com.travelportal.service.IMailService)}
     * .
     */
    @Test
    public void testSetMailService() {
        registerController.setMailService(mailService);
        assertEquals(true, true);
    }

    /**
     * Test method for
     * {@link com.travelportal.controller.RegisterController#registration(com.travelportal.model.UserModel, org.springframework.ui.Model)}
     * .
     */
    @SuppressWarnings("deprecation")
    @Test
    public void testRegistration() {
        userModel.setUsername("test@test.com");
        userModel.setPassword("12345");
        userModel.setFirstname("Naveen");
        userModel.setLastname("Kumar");
        userModel.setMobileno("9716554117");
        userModel.setDob(new Date("08/07/1992"));
        assertEquals("start", registerController.registration(userModel, model));
    }

}
