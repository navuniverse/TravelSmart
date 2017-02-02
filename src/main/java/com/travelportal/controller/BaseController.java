/**
 * 
 */
package com.travelportal.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author naveen.kumar
 * 
 */
public class BaseController {

    /**
     * 
     * Method to set attribute in session
     * 
     * @param key
     *            {@link String}
     * @param value
     *            {@link Object}
     * @param request
     *            {@link HttpServletRequest}
     */
    public void setSessionAttribute(String key, Object value,
            HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute(key, value);
    }

    /**
     * 
     * Method to get value from session
     * 
     * @param key
     *            {@link String}
     * @param request
     *            {@link HttpServletRequest}
     * @return session value corresponding to Key
     */
    public Object getSessionAttribute(String key, HttpServletRequest request) {
        HttpSession session = request.getSession();
        return session.getAttribute(key);
    }

    /**
     * 
     * Method to remove attribute from session
     * 
     * @param key
     *            {@link String}
     * @param request
     *            {@link HttpServletRequest}
     */
    public void removeSessionAttribute(String key, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute(key);
    }

    /**
     * 
     * Method to invalidate/end the session
     * 
     * @param request
     *            {@link HttpServletRequest}
     */
    public void invalidateSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
    }
}
