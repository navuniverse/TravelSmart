/**
 * 
 */
package com.travelportal.service.test;

import static org.junit.Assert.assertEquals;

import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.travelportal.dao.impl.AdminDao;
import com.travelportal.exception.ServiceLayerException;
import com.travelportal.model.FlightModel;
import com.travelportal.service.impl.AdminService;
import com.travelportal.util.test.HibernateUtil;

/**
 * @author Naveen
 * 
 */
public class AdminServiceTest {

    protected static AdminDao adminDao;
    protected static AdminService adminService;
    protected static FlightModel flightModel;
    protected static SessionFactory sessionFactory;

    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        adminDao = new AdminDao();
        adminService = new AdminService();
        adminService.setAdminDao(adminDao);
        flightModel = new FlightModel();
        sessionFactory = HibernateUtil.getSessionFactory();
        adminDao.setSessionFactory(sessionFactory);
    }

    /**
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        adminDao = null;
        adminService = null;
        flightModel = null;
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
     * {@link com.travelportal.service.impl.AdminService#addFlight(com.travelportal.model.FlightModel)}
     * .
     * 
     * @throws ServiceLayerException
     */
    @Test
    public void testAddFlight() throws ServiceLayerException {
        flightModel.setFlightId("IN103");
        flightModel.setCapacity(150);
        flightModel.setFlightType("STOP");
        flightModel.setProvider("INDIGO");
        assertEquals(true, adminService.addFlight(flightModel));
    }

    /**
     * Test method for
     * {@link com.travelportal.service.impl.AdminService#updateFlight(com.travelportal.model.FlightModel)}
     * .
     * 
     * @throws ServiceLayerException
     */
    @Test
    public void testUpdateFlight() throws ServiceLayerException {
        flightModel.setFlightId("IN104");
        flightModel.setCapacity(200);
        flightModel.setFlightType("STOP");
        flightModel.setProvider("INDIGO");
        assertEquals(true, adminService.updateFlight(flightModel));
    }

    /**
     * Test method for
     * {@link com.travelportal.service.impl.AdminService#deleteFlight(com.travelportal.model.FlightModel)}
     * .
     * 
     * @throws ServiceLayerException
     */
    @Test
    public void testDeleteFlight() throws ServiceLayerException {
        flightModel.setFlightId("IN103");
        assertEquals(true, adminService.deleteFlight(flightModel));
    }

    /**
     * Test method for
     * {@link com.travelportal.service.impl.AdminService#getSchedule(java.lang.String)}
     * .
     * 
     * @throws ServiceLayerException
     */
    @Test
    public void testGetSchedule() throws ServiceLayerException {
        assertEquals(true, adminService.getSchedule("SG301").size() > 0);
    }

}
