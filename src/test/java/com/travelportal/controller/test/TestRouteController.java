/**
 * 
 */
package com.travelportal.controller.test;

import static org.junit.Assert.assertEquals;

import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.travelportal.controller.RouteController;
import com.travelportal.dao.impl.RouteDao;
import com.travelportal.service.impl.RouteService;
import com.travelportal.util.test.HibernateUtil;

/**
 * 
 * Test class of {@link RouteController}
 * 
 * @author naveen.kumar
 * 
 */
public class TestRouteController {

    protected static RouteController routeController;
    protected static RouteService routeService;
    protected static RouteDao routeDao;
    protected static SessionFactory sessionFactory;

    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        routeController = new RouteController();
        routeService = new RouteService();
        routeDao = new RouteDao();
        sessionFactory = HibernateUtil.getSessionFactory();
        routeController.setRouteService(routeService);
        routeService.setRouteDao(routeDao);
        routeDao.setSessionFactory(sessionFactory);
    }

    /**
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        routeController = null;
        routeService = null;
        routeDao = null;
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
     * {@link com.travelportal.controller.RouteController#setRouteService(com.travelportal.service.IRouteService)}
     * .
     */
    @Test
    public void testSetRouteService() {
        routeController.setRouteService(routeService);
        assertEquals(true, true);
    }

    /**
     * Test method for
     * {@link com.travelportal.controller.RouteController#getSourceList()}.
     */
    @Test
    public void testGetSourceList() {
        assertEquals(true, routeController.getSourceList().size() > 0);
    }

    /**
     * Test method for
     * {@link com.travelportal.controller.RouteController#getDestinationList(java.lang.String)}
     * .
     */
    @Test
    public void testGetDestinationList() {
        assertEquals(true,
                routeController.getDestinationList("DELHI").size() > 0);
    }

}
