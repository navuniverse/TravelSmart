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
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.ui.Model;

import com.travelportal.controller.BaseController;
import com.travelportal.controller.UserController;
import com.travelportal.dao.impl.UserDao;
import com.travelportal.model.TicketModel;
import com.travelportal.model.UserModel;
import com.travelportal.service.impl.MailService;
import com.travelportal.service.impl.PDFService;
import com.travelportal.service.impl.PasswordService;
import com.travelportal.service.impl.UserService;
import com.travelportal.util.test.HibernateUtil;

/**
 * 
 * Test class of {@link UserController}
 * 
 * @author naveen.kumar
 * 
 */
public class TestUserController {

    protected static UserController userController;
    protected static UserService userService;
    protected static MailService mailService;
    protected static PDFService pdfService;
    protected static BaseController baseController;
    protected static UserDao userDao;
    protected static PasswordService passwordService;
    protected static UserModel userModel;
    protected static TicketModel ticketModel;
    protected static MockHttpServletRequest request;
    protected static MockHttpServletResponse response;
    protected static SessionFactory sessionFactory;
    protected static Model model;

    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        userController = new UserController();
        userService = new UserService();
        mailService = new MailService();
        pdfService = new PDFService();
        baseController = new BaseController();
        userDao = new UserDao();
        passwordService = new PasswordService();
        userModel = new UserModel();
        ticketModel = new TicketModel();
        sessionFactory = HibernateUtil.getSessionFactory();
        userController.setBaseController(baseController);
        userController.setMailService(mailService);
        userController.setPdfService(pdfService);
        userController.setUserService(userService);
        userService.setUserDao(userDao);
        userService.setPasswordService(passwordService);
        mailService.setPasswordService(passwordService);
        pdfService.setUserDao(userDao);
        userDao.setSessionFactory(sessionFactory);
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
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
        userController = null;
        userDao = null;
        userService = null;
        mailService = null;
        passwordService = null;
        pdfService = null;
        userModel = null;
        ticketModel = null;
        request = null;
        response = null;
        model = null;
        baseController = null;
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
     * {@link com.travelportal.controller.UserController#setBaseController(com.travelportal.controller.BaseController)}
     * .
     */
    @Test
    public void testSetBaseController() {
        userController.setBaseController(baseController);
        assertEquals(true, true);
    }

    /**
     * Test method for
     * {@link com.travelportal.controller.UserController#setUserService(com.travelportal.service.IUserService)}
     * .
     */
    @Test
    public void testSetUserService() {
        userController.setUserService(userService);
        assertEquals(true, true);
    }

    /**
     * Test method for
     * {@link com.travelportal.controller.UserController#setMailService(com.travelportal.service.IMailService)}
     * .
     */
    @Test
    public void testSetMailService() {
        userController.setMailService(mailService);
        assertEquals(true, true);
    }

    /**
     * Test method for
     * {@link com.travelportal.controller.UserController#setPdfService(com.travelportal.service.IPDFService)}
     * .
     */
    @Test
    public void testSetPdfService() {
        userController.setPdfService(pdfService);
        assertEquals(true, true);
    }

    /**
     * Test method for
     * {@link com.travelportal.controller.UserController#history(com.travelportal.model.TicketModel, javax.servlet.http.HttpServletRequest, org.springframework.ui.Model)}
     * .
     */
    @SuppressWarnings("deprecation")
    @Test
    public void testHistory() {
        userModel.setUsername("naveen.kumar@impetus.co.in");
        userModel.setPassword("tOiNqo9cBIEbwuJhBnqUgw==");
        userModel.setFirstname("Naveen");
        userModel.setLastname("Kumar");
        userModel.setMobileno("9716554117");
        userModel.setRole("NORMAL");
        userModel.setDob(new Date("08/07/1992"));
        baseController.setSessionAttribute("user", userModel, request);
        userController.history(ticketModel, request, model);
        assertEquals(true, true);
    }

    /**
     * Test method for
     * {@link com.travelportal.controller.UserController#cancelTicket(int, com.travelportal.model.TicketModel, org.springframework.ui.Model)}
     * .
     */
    @Test
    public void testCancelTicket() {
        assertEquals("redirect:/history",
                userController.cancelTicket(1, ticketModel, model));
    }

    /**
     * Test method for
     * {@link com.travelportal.controller.UserController#profile(com.travelportal.model.UserModel, javax.servlet.http.HttpServletRequest, org.springframework.ui.Model)}
     * .
     */
    @SuppressWarnings("deprecation")
    @Test
    public void testProfile() {
        userModel.setUsername("naveen.kumar@impetus.co.in");
        userModel.setPassword("12345");
        userModel.setFirstname("Naveen");
        userModel.setLastname("Kumar");
        userModel.setMobileno("9716554117");
        userModel.setRole("NORMAL");
        userModel.setDob(new Date("08/07/1992"));
        assertEquals("updateprofile",
                userController.profile(userModel, request, model));
    }

    /**
     * Test method for
     * {@link com.travelportal.controller.UserController#forgetPassword(java.lang.String, org.springframework.ui.Model)}
     * .
     */
    @Test
    public void testForgetPassword() {
        assertEquals("login", userController.forgetPassword(
                "naveen.kumar@impetus.co.in", model));
    }

    /**
     * Test method for
     * {@link com.travelportal.controller.UserController#createHistoryPDF(com.travelportal.model.UserModel, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)}
     * .
     */
    @SuppressWarnings("deprecation")
    @Test
    public void testCreateHistoryPDF() {
        userModel.setUsername("naveen.kumar@impetus.co.in");
        userModel.setPassword("tOiNqo9cBIEbwuJhBnqUgw==");
        userModel.setFirstname("Naveen");
        userModel.setLastname("Kumar");
        userModel.setMobileno("9716554117");
        userModel.setRole("NORMAL");
        userModel.setDob(new Date("08/07/1992"));
        baseController.setSessionAttribute("user", userModel, request);
        assertEquals("history",
                userController.createHistoryPDF(userModel, request, response));
    }

    /**
     * Test method for
     * {@link com.travelportal.controller.UserController#contactUs(java.lang.String, java.lang.String, org.springframework.ui.Model)}
     * .
     */
    @Test
    public void testContactUs() {
        assertEquals("contact",
                userController.contactUs("a@a.com", "TEST MAIL", model));
    }

}
