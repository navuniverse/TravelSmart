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

import com.travelportal.dao.impl.RouteDao;
import com.travelportal.exception.ServiceLayerException;
import com.travelportal.service.impl.RouteService;
import com.travelportal.util.test.HibernateUtil;

/**
 * @author naveen.kumar
 * 
 */
public class RouteServiceTest {

    protected static RouteService routeService;
    protected static RouteDao routeDao;
    protected static SessionFactory sessionFactory;

    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        routeDao = new RouteDao();
        routeService = new RouteService();
        sessionFactory = HibernateUtil.getSessionFactory();
        routeDao.setSessionFactory(sessionFactory);
        routeService.setRouteDao(routeDao);
    }

    /**
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        routeDao = null;
        routeService = null;
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
     * {@link com.travelportal.service.impl.RouteService#setRouteDao(com.travelportal.dao.IRouteDao)}
     * .
     */
    @Test
    public void testSetRouteDao() {
        routeService.setRouteDao(routeDao);
        assertEquals(true, true);
    }

    /**
     * Test method for
     * {@link com.travelportal.service.impl.RouteService#getSourceCities()}.
     * 
     * @throws ServiceLayerException
     */
    @Test
    public void testGetSourceCities() throws ServiceLayerException {
        assertEquals(true, routeService.getSourceCities().size() > 0);
    }

    /**
     * Test method for
     * {@link com.travelportal.service.impl.RouteService#getDestinationCities(java.lang.String)}
     * .
     * 
     * @throws ServiceLayerException
     */
    @Test
    public void testGetDestinationCities() throws ServiceLayerException {
        assertEquals(true,
                routeService.getDestinationCities("DELHI").size() > 0);
    }

}
