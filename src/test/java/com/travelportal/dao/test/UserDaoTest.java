package com.travelportal.dao.test;

import static org.junit.Assert.assertEquals;

import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.travelportal.dao.impl.UserDao;
import com.travelportal.model.UserModel;
import com.travelportal.util.test.HibernateUtil;

/**
 * 
 * Test Class for the UserDao class
 * 
 * @author naveen.kumar
 * 
 */
public class UserDaoTest {

    protected static UserDao userDao;
    protected static UserModel userModel;
    protected static SessionFactory sessionFactory;

    /**
     * 
     * Work to be done before class load
     * 
     * @throws Exception
     */
    @BeforeClass
    public static void setUp() throws Exception {
        userDao = new UserDao();
        userModel = new UserModel();
        sessionFactory = HibernateUtil.getSessionFactory();
        userDao.setSessionFactory(sessionFactory);
    }

    /**
     * 
     * Work to be done after class has finished execution
     * 
     * @throws Exception
     */
    @AfterClass
    public static void tearDown() throws Exception {
        userDao = null;
        userModel = null;
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
     * 
     * Test case for insertUser() method of UserDao
     * 
     * @throws Exception
     */
    public void testInsertUserPass() throws Exception {
        userModel.setFirstname("BCD");
        userModel.setLastname("BCD");
        userModel.setMobileno("1234567890");
        userModel.setUsername("abc@abc.com");
        userModel.setPassword("12345");
        userModel.setRole("NORMAL");
        assertEquals(true, userDao.insertUser(userModel));
    }

    /**
     * 
     * Test case for getUser() method of UserDao
     * 
     * @throws Exception
     */
    public void testGetUserPass() throws Exception {
        userModel.setUsername("naveen.kumar@impetus.co.in");
        userModel.setPassword("12345");
        assertEquals(true, userDao.getUser(userModel) != null);
    }

    /**
     * 
     * Test case for getPassword() method of UserDao
     * 
     * @throws Exception
     */
    public void testGetPasswordPass() throws Exception {
        assertEquals("tOiNqo9cBIEbwuJhBnqUgw==",
                userDao.getPassword("naveen.kumar@impetus.co.in").getPassword());
    }

    /**
     * 
     * Test case for updateUser() method of UserDao
     * 
     * @throws Exception
     */
    public void testUpdateUserPass() throws Exception {
        userModel.setUsername("naveen.kumar@impetus.co.in");
        userModel.setPassword("tOiNqo9cBIEbwuJhBnqUgw==");
        assertEquals(true, userDao.updateUser(userModel));
    }

    /**
     * 
     * Success Test Case for getHistory() method of UserDao
     * 
     * @throws Exception
     */
    @Test
    public void testGetHistoryPass() throws Exception {
        userModel.setUsername("naveen.kumar@impetus.co.in");
        assertEquals(true, userDao.getHistory(userModel).size() > 0);
    }

    /**
     * 
     * Success Test Case for cancelTicket() method of UserDao
     * 
     * @throws Exception
     */
    @Test
    public void testCancelTicketPass() throws Exception {
        assertEquals(true, userDao.cancelTicket(1));
    }

    /**
     * 
     * Success Test Case for getTicketDetails() method of UserDao
     * 
     * @throws Exception
     */
    @Test
    public void testGetTicketDetailsPass() throws Exception {
        assertEquals(true, userDao.getTicketDetails(1).size() > 0);
    }

    /**
     * 
     * Success Test Case for bookHistory() method of UserDao
     * 
     * @throws Exception
     */
    @Test
    public void testBookHistoryPass() throws Exception {
        assertEquals(true, userDao.getHistory("naveen.kumar@impetus.co.in")
                .size() >= 0);
    }

}
