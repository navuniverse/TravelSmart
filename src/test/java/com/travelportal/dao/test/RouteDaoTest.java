/**
 * 
 */
package com.travelportal.dao.test;

import static org.junit.Assert.assertEquals;

import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.travelportal.dao.impl.RouteDao;
import com.travelportal.util.test.HibernateUtil;

/**
 * 
 * Test class for com.travelportal.dao.RouteDao class
 * 
 * @author naveen.kumar
 * 
 */
public class RouteDaoTest {

    protected static RouteDao routeDao;
    protected static SessionFactory sessionFactory;

    /**
     * 
     * Work to be done done before the class load
     * 
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        routeDao = new RouteDao();
        sessionFactory = HibernateUtil.getSessionFactory();
        routeDao.setSessionFactory(sessionFactory);
    }

    /**
     * 
     * Work to be done after the class has executed
     * 
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
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
     * Test method for {@link com.travelportal.dao.impl.RouteDao#getSource()}.
     * 
     * @throws java.lang.Exception
     */
    @Test
    public void testGetSource() throws Exception {
        assertEquals(true, routeDao.getSourceCities().size() > 0);
    }

    /**
     * Test method for
     * {@link com.travelportal.dao.impl.RouteDao#getDestination(java.lang.String)}
     * .
     * 
     * @throws java.lang.Exception
     */
    @Test
    public void testGetDestination() throws Exception {
        assertEquals(true, routeDao.getDestinationCities("DELHI").size() > 0);
    }

}
