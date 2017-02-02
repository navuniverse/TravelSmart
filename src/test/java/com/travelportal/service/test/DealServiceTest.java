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
import com.travelportal.service.impl.DealService;
import com.travelportal.util.test.HibernateUtil;

/**
 * @author naveen.kumar
 * 
 */
public class DealServiceTest {

    protected static DealService dealService;
    protected static AdminDao adminDao;
    protected static SessionFactory sessionFactory;

    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        dealService = new DealService();
        adminDao = new AdminDao();
        sessionFactory = HibernateUtil.getSessionFactory();
        adminDao.setSessionFactory(sessionFactory);
    }

    /**
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        dealService = null;
        adminDao = null;
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
     * {@link com.travelportal.service.impl.DealService#getDeals()}.
     * 
     * @throws ServiceLayerException
     */
    @Test
    public void testGetDeals() throws ServiceLayerException {
        dealService.setAdminDao(adminDao);
        assertEquals(true, dealService.getDeals().size() > 0);
    }

}
