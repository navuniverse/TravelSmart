package com.travelportal.dao.test;

import static org.junit.Assert.assertEquals;

import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.travelportal.dao.impl.AdminDao;
import com.travelportal.exception.DaoException;
import com.travelportal.model.FlightModel;
import com.travelportal.util.test.HibernateUtil;

/**
 * 
 * Test Class for AdminDao class
 * 
 * @author naveen.kumar
 * 
 */
public class AdminDaoTest {

    protected static FlightModel flightModel;
    protected static AdminDao adminDao;
    protected static SessionFactory sessionFactory;

    /**
     * 
     * Work to be done done before the class load
     * 
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        flightModel = new FlightModel();
        adminDao = new AdminDao();
        sessionFactory = HibernateUtil.getSessionFactory();
        adminDao.setSessionFactory(sessionFactory);
    }

    /**
     * 
     * Work to be done after the class has executed
     * 
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {

        flightModel = null;
        adminDao = null;
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
     * 
     * Pass Test method for
     * {@link com.travelportal.dao.impl.AdminDao#addFlight(com.travelportal.model.FlightModel)}
     * .
     * 
     * @throws java.lang.Exception
     */
    @Test
    public void testAddFlightPass() throws Exception {
        flightModel.setFlightId("IN102");
        flightModel.setCapacity(150);
        flightModel.setFlightType("NONSTOP");
        flightModel.setProvider("INDIGO");
        assertEquals(true, adminDao.addFlight(flightModel));
    }

    /**
     * Pass Test method for
     * {@link com.travelportal.dao.impl.AdminDao#updateFlight(com.travelportal.model.FlightModel)}
     * .
     * 
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateFlightPass() throws Exception {
        flightModel.setFlightId("IN102");
        flightModel.setCapacity(200);
        flightModel.setFlightType("NONSTOP");
        flightModel.setProvider("INDIGO");

        assertEquals(true, adminDao.updateFlight(flightModel));
    }

    /**
     * Pass Test method for
     * {@link com.travelportal.dao.impl.AdminDao#deleteFlight(com.travelportal.model.FlightModel)}
     * .
     * 
     * @throws DaoException
     * 
     * @throws java.lang.Exception
     */
    @Test
    public void testDeleteFlightPass() throws DaoException {
        flightModel.setFlightId("IN102");
        flightModel.setCapacity(150);
        flightModel.setFlightType("NONSTOP");
        flightModel.setProvider("INDIGO");
        assertEquals(true, adminDao.deleteFlight(flightModel));
    }

    /**
     * Pass Test method for
     * {@link com.travelportal.dao.impl.AdminDao#showSchedule(java.lang.String)}
     * .
     * 
     * @throws java.lang.Exception
     */
    @Test
    public void testgetSchedulePass() throws Exception {
        assertEquals(true, adminDao.getSchedule("SG301").size() > 0);
    }

    /**
     * Pass Test method for {@link com.travelportal.dao.impl.AdminDao#getDeal()}
     * .
     * 
     * @throws java.lang.Exception
     */
    @Test
    public void testgetDeal() throws Exception {
        assertEquals(true, adminDao.getDeal().size() > 0);
    }
}
