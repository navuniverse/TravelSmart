/**
 * 
 */
package com.travelportal.service.test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.travelportal.dao.impl.AdminDao;
import com.travelportal.exception.ServiceLayerException;
import com.travelportal.service.impl.UploadService;
import com.travelportal.util.test.HibernateUtil;

/**
 * @author naveen.kumar
 * 
 */
public class UploadServiceTest {

    protected static UploadService uploadService;
    protected static AdminDao adminDao;
    protected static SessionFactory sessionFactory;

    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        uploadService = new UploadService();
        adminDao = new AdminDao();
        sessionFactory = HibernateUtil.getSessionFactory();
        adminDao.setSessionFactory(sessionFactory);
        uploadService.setAdminDao(adminDao);
    }

    /**
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        uploadService = null;
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
     * {@link com.travelportal.service.impl.UploadService#setAdminDao(com.travelportal.dao.IAdminDao)}
     * .
     */
    @Test
    public void testSetAdminDao() {
        uploadService.setAdminDao(adminDao);
        assertEquals(true, true);
    }

    /**
     * Test method for
     * {@link com.travelportal.service.impl.UploadService#xmlParser()}.
     * 
     * @throws ServiceLayerException
     */
    @Test
    public void testXmlParser() throws ServiceLayerException {
        uploadService.xmlParser();
        assertEquals(true, true);
    }

    /**
     * Test method for
     * {@link com.travelportal.service.impl.UploadService#csvParser()}.
     * 
     * @throws IOException
     * @throws ServiceLayerException
     */
    @Test
    public void testCsvParser() throws IOException, ServiceLayerException {
        uploadService.csvParser();
        assertEquals(true, true);
    }

}
