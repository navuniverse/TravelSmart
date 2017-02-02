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
import org.springframework.ui.Model;

import com.travelportal.controller.BaseController;
import com.travelportal.controller.LoginController;
import com.travelportal.dao.impl.AdminDao;
import com.travelportal.dao.impl.UserDao;
import com.travelportal.model.UserModel;
import com.travelportal.service.impl.DealService;
import com.travelportal.service.impl.LoginService;
import com.travelportal.service.impl.PDFService;
import com.travelportal.service.impl.PasswordService;
import com.travelportal.util.test.HibernateUtil;

/**
 * 
 * Test class of {@link LoginController}
 * 
 * @author naveen.kumar
 * 
 */
public class TestLoginController {

    protected static LoginController loginController;
    protected static LoginService loginService;
    protected static PDFService pdfService;
    protected static DealService dealService;
    protected static PasswordService passwordService;
    protected static BaseController baseController;
    protected static UserDao userDao;
    protected static AdminDao adminDao;
    protected static UserModel userModel;
    protected static SessionFactory sessionFactory;
    protected static MockHttpServletRequest request;
    protected static Model model;

    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        loginController = new LoginController();
        loginService = new LoginService();
        pdfService = new PDFService();
        dealService = new DealService();
        passwordService = new PasswordService();
        baseController = new BaseController();
        userDao = new UserDao();
        adminDao = new AdminDao();
        userModel = new UserModel();
        sessionFactory = HibernateUtil.getSessionFactory();
        request = new MockHttpServletRequest();
        loginController.setBaseController(baseController);
        loginController.setDealService(dealService);
        loginController.setLoginService(loginService);
        loginController.setPasswordService(passwordService);
        loginService.setUserDao(userDao);
        loginService.setPasswordService(passwordService);
        dealService.setAdminDao(adminDao);
        userDao.setSessionFactory(sessionFactory);
        adminDao.setSessionFactory(sessionFactory);
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
        loginController = null;
        loginService = null;
        pdfService = null;
        dealService = null;
        passwordService = null;
        baseController = null;
        userDao = null;
        adminDao = null;
        userModel = null;
        request = null;
        model = null;
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
     * {@link com.travelportal.controller.LoginController#setPasswordService(com.travelportal.service.IPasswordService)}
     * .
     */
    @Test
    public void testSetPasswordService() {
        loginController.setPasswordService(passwordService);
        assertEquals(true, true);
    }

    /**
     * Test method for
     * {@link com.travelportal.controller.LoginController#setBaseController(com.travelportal.controller.BaseController)}
     * .
     */
    @Test
    public void testSetBaseController() {
        loginController.setBaseController(baseController);
        assertEquals(true, true);
    }

    /**
     * Test method for
     * {@link com.travelportal.controller.LoginController#setLoginService(com.travelportal.service.ILoginService)}
     * .
     */
    @Test
    public void testSetLoginService() {
        loginController.setLoginService(loginService);
        assertEquals(true, true);
    }

    /**
     * Test method for
     * {@link com.travelportal.controller.LoginController#setDealService(com.travelportal.service.IDealService)}
     * .
     */
    @Test
    public void testSetDealService() {
        loginController.setDealService(dealService);
        assertEquals(true, true);
    }

    /**
     * Test method for
     * {@link com.travelportal.controller.LoginController#forgetpassword()}.
     */
    @Test
    public void testForgetpassword() {
        assertEquals("forgetpassword", loginController.forgetpassword());
    }

    /**
     * Test method for
     * {@link com.travelportal.controller.LoginController#logout(javax.servlet.http.HttpServletRequest, org.springframework.ui.Model)}
     * .
     */
    @SuppressWarnings("deprecation")
    @Test
    public void testLogout() {
        userModel.setUsername("naveen.kumar@impetus.co.in");
        userModel.setPassword("tOiNqo9cBIEbwuJhBnqUgw==");
        userModel.setFirstname("Naveen");
        userModel.setLastname("Kumar");
        userModel.setMobileno("9716554117");
        userModel.setRole("NORMAL");
        userModel.setDob(new Date("08/07/1992"));
        baseController.setSessionAttribute("user", userModel, request);
        assertEquals("redirect:/start", loginController.logout(request, model));
    }

    /**
     * Test method for
     * {@link com.travelportal.controller.LoginController#welcomePage(com.travelportal.model.UserModel, javax.servlet.http.HttpServletRequest)}
     * .
     */
    @Test
    public void testWelcomePage() {
        loginController.welcomePage(userModel, request);
        assertEquals(true, true);
    }

    /**
     * Test method for
     * {@link com.travelportal.controller.LoginController#login(com.travelportal.model.UserModel, javax.servlet.http.HttpServletRequest, org.springframework.ui.Model)}
     * .
     */
    @Test
    public void testLogin() {
        userModel.setUsername("naveen.kumar@impetus.co.in");
        userModel.setPassword("12345");
        assertEquals("welcome",
                loginController.login(userModel, request, model));
    }

}
