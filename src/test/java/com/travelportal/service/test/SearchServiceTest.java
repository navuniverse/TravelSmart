/**
 * 
 */
package com.travelportal.service.test;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.travelportal.dao.impl.SearchDao;
import com.travelportal.exception.ServiceLayerException;
import com.travelportal.service.impl.SearchService;
import com.travelportal.util.test.HibernateUtil;

/**
 * @author naveen.kumar
 * 
 */
public class SearchServiceTest {

    protected static SearchService searchService;
    protected static SearchDao searchDao;
    protected static SessionFactory sessionFactory;

    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        searchDao = new SearchDao();
        searchService = new SearchService();
        sessionFactory = HibernateUtil.getSessionFactory();
        searchDao.setSessionFactory(sessionFactory);
        searchService.setSearchDao(searchDao);
    }

    /**
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        searchDao = null;
        searchService = null;
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
     * {@link com.travelportal.service.impl.SearchService#setSearchDao(com.travelportal.dao.ISearchDao)}
     * .
     */
    @Test
    public void testSetSearchDao() {
        searchService.setSearchDao(searchDao);
        assertEquals(true, true);
    }

    /**
     * Test method for
     * {@link com.travelportal.service.impl.SearchService#search(java.lang.String, java.lang.String, java.util.Date)}
     * .
     * 
     * @throws ServiceLayerException
     */
    @SuppressWarnings("deprecation")
    @Test
    public void testSearch() throws ServiceLayerException {
        searchService.setSearchDao(searchDao);
        assertEquals(true,
                searchService.search("DELHI", "MUMBAI", new Date("12/02/2013"))
                        .size() > 0);
    }

}
