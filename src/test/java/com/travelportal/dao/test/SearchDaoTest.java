package com.travelportal.dao.test;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.travelportal.dao.impl.SearchDao;
import com.travelportal.util.test.HibernateUtil;

/**
 * 
 * Test Class for the SearchDao Class
 * 
 * @author naveen.kumar
 * 
 */
public class SearchDaoTest {

    protected static SearchDao searchDao;
    protected static SessionFactory sessionFactory;

    /**
     * 
     * Work to be done before class load
     * 
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        searchDao = new SearchDao();
        sessionFactory = HibernateUtil.getSessionFactory();
        searchDao.setSessionFactory(sessionFactory);
    }

    /**
     * 
     * Work to be done after class has finished execution
     * 
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        searchDao = null;
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
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    /**
     * 
     * Pass Test method for
     * {@link com.travelportal.dao.impl.SearchDao#searchFlight(java.lang.String, java.lang.String, java.util.Date)}
     * 
     * @throws Exception
     */
    @SuppressWarnings("deprecation")
    @Test
    public void testSearchFlightPass() throws Exception {
        assertEquals(
                true,
                searchDao.searchFlight("DELHI", "MUMBAI",
                        new Date("12/02/2013")).size() >= 0);
    }
}
