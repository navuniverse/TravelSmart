/**
 * 
 */
package com.travelportal.controller.test;

import static org.junit.Assert.assertEquals;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.ui.Model;

import com.travelportal.controller.BaseController;
import com.travelportal.controller.SearchController;
import com.travelportal.dao.impl.SearchDao;
import com.travelportal.service.impl.SearchService;
import com.travelportal.util.test.HibernateUtil;

/**
 * 
 * Test class of {@link SearchController}
 * 
 * @author naveen.kumar
 * 
 */
public class TestSearchController {

    protected static SearchController searchController;
    protected static SearchService searchService;
    protected static SearchDao searchDao;
    protected static BaseController baseController;
    protected static SessionFactory sessionFactory;
    protected static MockHttpServletRequest request;
    protected static Model model;

    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        searchController = new SearchController();
        searchService = new SearchService();
        searchDao = new SearchDao();
        baseController = new BaseController();
        sessionFactory = HibernateUtil.getSessionFactory();
        request = new MockHttpServletRequest();
        searchController.setBaseController(baseController);
        searchController.setSearchService(searchService);
        searchService.setSearchDao(searchDao);
        searchDao.setSessionFactory(sessionFactory);
        model = new Model() {

            public Model mergeAttributes(Map<String, ?> arg0) {
                return null;
            }

            public boolean containsAttribute(String arg0) {
                return false;
            }

            public Map<String, Object> asMap() {
                return null;
            }

            public Model addAttribute(String arg0, Object arg1) {
                return null;
            }

            public Model addAttribute(Object arg0) {
                return null;
            }

            public Model addAllAttributes(Map<String, ?> arg0) {
                return null;
            }

            public Model addAllAttributes(Collection<?> arg0) {
                return null;
            }
        };
    }

    /**
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        searchController = null;
        searchDao = null;
        searchService = null;
        sessionFactory = null;
        request = null;
        model = null;
        baseController = null;
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
     * {@link com.travelportal.controller.SearchController#setSearchService(com.travelportal.service.ISearchService)}
     * .
     */
    @Test
    public void testSetSearchService() {
        searchController.setSearchService(searchService);
        assertEquals(true, true);
    }

    /**
     * Test method for
     * {@link com.travelportal.controller.SearchController#setBaseController(com.travelportal.controller.BaseController)}
     * .
     */
    @Test
    public void testSetBaseController() {
        searchController.setBaseController(baseController);
        assertEquals(true, true);
    }

    /**
     * Test method for
     * {@link com.travelportal.controller.SearchController#search(java.lang.String, java.lang.String, java.util.Date, org.springframework.ui.Model, int, int, int, javax.servlet.http.HttpServletRequest)}
     * .
     */
    @SuppressWarnings("deprecation")
    @Test
    public void testSearch() {
        assertEquals("searchresult", searchController.search("DELHI", "MUMBAI",
                new Date("12/01/2013"), model, 1, 0, 0, request));
    }

}
