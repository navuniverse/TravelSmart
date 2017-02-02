/**
 * 
 */
package com.travelportal.controller.test;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;

import com.travelportal.controller.BaseController;

/**
 * 
 * Test class of {@link BaseController}
 * 
 * @author naveen.kumar
 * 
 */
public class TestBaseController {

    protected static BaseController baseController;
    protected static MockHttpServletRequest request;
    protected static MockHttpSession session;

    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        baseController = new BaseController();
        request = new MockHttpServletRequest();
        session = new MockHttpSession();
    }

    /**
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        baseController = null;
        request = null;
        session = null;
    }

    /**
     * Test method for
     * {@link com.travelportal.controller.BaseController#setSessionAttribute(java.lang.String, java.lang.Object, javax.servlet.http.HttpServletRequest)}
     * .
     */
    @Test
    public void testSetSessionAttribute() {
        baseController.setSessionAttribute("name", "naveen", request);
        assertEquals(true, true);
    }

    /**
     * Test method for
     * {@link com.travelportal.controller.BaseController#getSessionAttribute(java.lang.String, javax.servlet.http.HttpServletRequest)}
     * .
     */
    @Test
    public void testGetSessionAttribute() {
        baseController.setSessionAttribute("age", 21, request);
        assertEquals(21, baseController.getSessionAttribute("age", request));
    }

    /**
     * Test method for
     * {@link com.travelportal.controller.BaseController#removeSessionAttribute(java.lang.String, javax.servlet.http.HttpServletRequest)}
     * .
     */
    @Test
    public void testRemoveSessionAttribute() {
        baseController.removeSessionAttribute("age", request);
        assertEquals(true, true);
    }

    /**
     * Test method for
     * {@link com.travelportal.controller.BaseController#invalidateSession(javax.servlet.http.HttpServletRequest)}
     * .
     */
    @Test
    public void testInvalidateSession() {
        baseController.invalidateSession(request);
        assertEquals(true, true);
    }

}
