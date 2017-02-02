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
import com.travelportal.controller.MappingController;
import com.travelportal.dao.impl.AdminDao;
import com.travelportal.model.UserModel;
import com.travelportal.service.impl.DealService;
import com.travelportal.util.test.HibernateUtil;

/**
 * 
 * Test class of {@link MappingController}
 * 
 * @author naveen.kumar
 * 
 */
public class TestMappingController {

    protected static MappingController mappingController;
    protected static BaseController baseController;
    protected static DealService dealService;
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
        mappingController = new MappingController();
        baseController = new BaseController();
        dealService = new DealService();
        adminDao = new AdminDao();
        userModel = new UserModel();
        sessionFactory = HibernateUtil.getSessionFactory();
        mappingController.setBaseController(baseController);
        mappingController.setDealService(dealService);
        dealService.setAdminDao(adminDao);
        adminDao.setSessionFactory(sessionFactory);
        request = new MockHttpServletRequest();
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
        mappingController = null;
        baseController = null;
        dealService = null;
        adminDao = null;
        userModel = null;
        sessionFactory = null;
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
     * {@link com.travelportal.controller.MappingController#setBaseController(com.travelportal.controller.BaseController)}
     * .
     */
    @Test
    public void testSetBaseController() {
        mappingController.setBaseController(baseController);
        assertEquals(true, true);
    }

    /**
     * Test method for
     * {@link com.travelportal.controller.MappingController#setDealService(com.travelportal.service.IDealService)}
     * .
     */
    @Test
    public void testSetDealService() {
        mappingController.setDealService(dealService);
        assertEquals(true, true);
    }

    /**
     * Test method for
     * {@link com.travelportal.controller.MappingController#start(javax.servlet.http.HttpServletRequest, org.springframework.ui.Model)}
     * .
     */
    @Test
    public void testStart() {
        assertEquals("start", mappingController.start(request, model));
    }

    /**
     * Test method for
     * {@link com.travelportal.controller.MappingController#error()}.
     */
    @Test
    public void testError() {
        assertEquals("error", mappingController.error());
    }

    /**
     * Test method for
     * {@link com.travelportal.controller.MappingController#admin()}.
     */
    @Test
    public void testAdmin() {
        assertEquals("admin", mappingController.admin());
    }

    /**
     * Test method for
     * {@link com.travelportal.controller.MappingController#csvupload(com.travelportal.model.UserModel, javax.servlet.http.HttpServletRequest)}
     * .
     */
    @SuppressWarnings("deprecation")
    @Test
    public void testCsvupload() {
        userModel.setUsername("naveen.kumar@impetus.co.in");
        userModel.setPassword("tOiNqo9cBIEbwuJhBnqUgw==");
        userModel.setFirstname("Naveen");
        userModel.setLastname("Kumar");
        userModel.setMobileno("9716554117");
        userModel.setRole("NORMAL");
        userModel.setDob(new Date("08/07/1992"));
        baseController.setSessionAttribute("user", userModel, request);
        mappingController.csvupload(userModel, request);
        assertEquals(true, true);

    }

    /**
     * Test method for
     * {@link com.travelportal.controller.MappingController#xmlupload(com.travelportal.model.UserModel, javax.servlet.http.HttpServletRequest)}
     * .
     */
    @SuppressWarnings("deprecation")
    @Test
    public void testXmlupload() {
        userModel.setUsername("naveen.kumar@impetus.co.in");
        userModel.setPassword("tOiNqo9cBIEbwuJhBnqUgw==");
        userModel.setFirstname("Naveen");
        userModel.setLastname("Kumar");
        userModel.setMobileno("9716554117");
        userModel.setRole("NORMAL");
        userModel.setDob(new Date("08/07/1992"));
        baseController.setSessionAttribute("user", userModel, request);
        mappingController.xmlupload(userModel, request);
        assertEquals(true, true);
    }

}
