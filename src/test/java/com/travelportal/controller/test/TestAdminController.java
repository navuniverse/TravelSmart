/**
 * 
 */
package com.travelportal.controller.test;

import static org.junit.Assert.assertEquals;

import java.util.Collection;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.ui.Model;

import com.travelportal.controller.AdminController;
import com.travelportal.controller.BaseController;
import com.travelportal.dao.impl.AdminDao;
import com.travelportal.model.FlightModel;
import com.travelportal.service.impl.AdminService;
import com.travelportal.service.impl.DealService;
import com.travelportal.service.impl.PDFService;
import com.travelportal.util.test.HibernateUtil;

/**
 * 
 * Test Class of {@link AdminController}
 * 
 * @author naveen.kumar
 * 
 */
public class TestAdminController {

    protected static AdminController adminController;
    protected static FlightModel flightModel;
    protected static Model model;
    protected static AdminService adminService;
    protected static AdminDao adminDao;
    protected static SessionFactory sessionFactory;
    protected static MockHttpServletRequest request;
    protected static MockHttpServletResponse response;
    protected static MockHttpSession session;
    protected static PDFService pdfService;
    protected static DealService dealService;
    protected static BaseController baseController;

    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        adminController = new AdminController();
        flightModel = new FlightModel();
        adminService = new AdminService();
        adminController.setAdminService(adminService);
        adminDao = new AdminDao();
        adminService.setAdminDao(adminDao);
        sessionFactory = HibernateUtil.getSessionFactory();
        adminDao.setSessionFactory(sessionFactory);
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        session = new MockHttpSession();
        pdfService = new PDFService();
        adminController.setPdfService(pdfService);
        pdfService.setAdminDao(adminDao);
        dealService = new DealService();
        adminController.setDealService(dealService);
        dealService.setAdminDao(adminDao);
        baseController = new BaseController();
        adminController.setBaseController(baseController);
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
        adminController = null;
        flightModel = null;
        adminDao = null;
        adminService = null;

        model = null;
        request = null;
        response = null;
        session = null;
        pdfService = null;
        dealService = null;
        baseController = null;
    }

    /* *//**
     * Before.
     */
    /*
     * @Before public void before() {
     * 
     * sessionFactory.getCurrentSession().beginTransaction();
     * 
     * }
     */

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
     * {@link com.travelportal.controller.AdminController#setBaseController(com.travelportal.controller.BaseController)}
     * .
     */

    @Test
    public void testSetBaseController() {
        adminController.setBaseController(baseController);
        assertEquals(true, true);
    }

    /**
     * Test method for
     * {@link com.travelportal.controller.AdminController#setAdminService(com.travelportal.service.IAdminService)}
     * .
     */
    @Test
    public void testSetAdminService() {
        adminController.setAdminService(adminService);
        assertEquals(true, true);
    }

    /**
     * Test method for
     * {@link com.travelportal.controller.AdminController#setDealService(com.travelportal.service.IDealService)}
     * .
     */

    @Test
    public void testSetDealService() {
        adminController.setDealService(dealService);
        assertEquals(true, true);
    }

    /**
     * Test method for
     * {@link com.travelportal.controller.AdminController#setPdfService(com.travelportal.service.IPDFService)}
     * .
     */
    @Test
    public void testSetPdfService() {
        adminController.setPdfService(pdfService);
        assertEquals(true, true);
    }

    /**
     * Test method for
     * {@link com.travelportal.controller.AdminController#addFlight(com.travelportal.model.FlightModel, org.springframework.ui.Model)}
     * .
     */
    @Test
    public void testAddFlight() {
        flightModel.setFlightId("AI102");
        flightModel.setCapacity(150);
        flightModel.setFlightType("STOP");
        flightModel.setProvider("AIR INDIA");
        assertEquals("addflight", adminController.addFlight(flightModel, model));
    }

    /**
     * Test method for
     * {@link com.travelportal.controller.AdminController#deleteFlight(com.travelportal.model.FlightModel, org.springframework.ui.Model)}
     * .
     */
    @Test
    public void testDeleteFlight() {
        flightModel.setFlightId("AI102");
        assertEquals("deleteflight",
                adminController.deleteFlight(flightModel, model));
    }

    /**
     * Test method for
     * {@link com.travelportal.controller.AdminController#updateFlight(com.travelportal.model.FlightModel, org.springframework.ui.Model)}
     * .
     */
    @Test
    public void testUpdateFlight() {
        flightModel.setFlightId("AI102");
        flightModel.setCapacity(200);
        flightModel.setFlightType("STOP");
        flightModel.setProvider("AIR INDIA");
        assertEquals("updateflight",
                adminController.updateFlight(flightModel, model));
    }

    /**
     * Test method for
     * {@link com.travelportal.controller.AdminController#showSchedule(java.lang.String, javax.servlet.http.HttpServletRequest, org.springframework.ui.Model)}
     * .
     */
    @Test
    public void testShowSchedule() {
        assertEquals("schedule",
                adminController.showSchedule("SG301", request, model));
    }

    /**
     * Test method for
     * {@link com.travelportal.controller.AdminController#showDeals(javax.servlet.http.HttpServletRequest, org.springframework.ui.Model)}
     * .
     */
    @Test
    public void testShowDeals() {
        assertEquals("deals", adminController.showDeals(request, model));
    }

    /**
     * Test method for
     * {@link com.travelportal.controller.AdminController#createSchedulePDF(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)}
     * .
     */
    @Test
    public void testCreateSchedulePDF() {
        session.setAttribute("flightId", "SG301");
        assertEquals("schedule",
                adminController.createSchedulePDF(request, response));
    }

}
