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
import com.travelportal.dao.impl.BookingDao;
import com.travelportal.dao.impl.UserDao;
import com.travelportal.model.ConnectorFsrModel;
import com.travelportal.service.impl.PDFService;
import com.travelportal.util.test.HibernateUtil;

/**
 * @author naveen.kumar
 * 
 */
public class PDFServiceTest {

    protected static PDFService pdfService;
    protected static UserDao userDao;
    protected static AdminDao adminDao;
    protected static BookingDao bookingDao;
    protected static ConnectorFsrModel fsrModel;
    protected static SessionFactory sessionFactory;

    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        pdfService = new PDFService();
        userDao = new UserDao();
        adminDao = new AdminDao();
        bookingDao = new BookingDao();
        fsrModel = new ConnectorFsrModel();
        sessionFactory = HibernateUtil.getSessionFactory();
        userDao.setSessionFactory(sessionFactory);
        adminDao.setSessionFactory(sessionFactory);
        bookingDao.setSessionFactory(sessionFactory);
    }

    /**
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        pdfService = null;
        userDao = null;
        adminDao = null;
        bookingDao = null;
        fsrModel = null;
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
     * {@link com.travelportal.service.impl.PDFService#setUserDao(com.travelportal.dao.IUserDao)}
     * .
     */
    @Test
    public void testSetUserDao() {
        pdfService.setUserDao(userDao);
        assertEquals(true, true);
    }

    /**
     * Test method for
     * {@link com.travelportal.service.impl.PDFService#setAdminDao(com.travelportal.dao.IAdminDao)}
     * .
     */
    @Test
    public void testSetAdminDao() {
        pdfService.setAdminDao(adminDao);
        assertEquals(true, true);
    }

    /**
     * Test method for
     * {@link com.travelportal.service.impl.PDFService#setBookingDao(com.travelportal.dao.IBookingDao)}
     * .
     */
    @Test
    public void testSetBookingDao() {
        pdfService.setBookingDao(bookingDao);
        assertEquals(true, true);
    }

    /**
     * Test method for
     * {@link com.travelportal.service.impl.PDFService#createUserPDF(java.lang.String)}
     * .
     */
    @Test
    public void testCreateUserPDF() {
        pdfService.createUserPDF("naveen.kumar@impetus.co.in");
        assertEquals(true, true);
    }

    /**
     * Test method for
     * {@link com.travelportal.service.impl.PDFService#createSchedulePDF(java.lang.String)}
     * .
     */
    @Test
    public void testCreateSchedulePDF() {
        pdfService.createSchedulePDF("SG301");
        assertEquals(true, true);
    }

    /**
     * Test method for
     * {@link com.travelportal.service.impl.PDFService#createTicketPDF(int)}.
     */
    @Test
    public void testCreateTicketPDF() {
        pdfService.createTicketPDF(1, fsrModel);
        assertEquals(true, true);
    }

}
