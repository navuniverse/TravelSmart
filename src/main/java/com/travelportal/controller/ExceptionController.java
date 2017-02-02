/**
 * 
 */
package com.travelportal.controller;

import org.apache.log4j.Logger;
import org.springframework.ui.Model;

/**
 * 
 * Class to handle exceptions
 * 
 * @author naveen.kumar
 * 
 */
public class ExceptionController extends Exception {

    private static Logger logger = Logger.getLogger(ExceptionController.class);
    public static final String MESSAGE = "message";
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 
     * Default constructor
     * 
     */
    public ExceptionController() {
        super();

    }

    /**
     * 
     * Constructor to display the message and cause
     * 
     * @param message
     *            {@link String}
     * @param cause
     *            {@link Throwable}
     */
    public ExceptionController(String message, Throwable cause, Model model) {
        logger.error("Exception Handler", cause);
        model.addAttribute(MESSAGE, message);
    }

    /**
     * 
     * Constructor to display the message
     * 
     * @param message
     *            {@link String}
     * @param model
     *            {@link Model}
     */
    public ExceptionController(String message, Model model) {
        model.addAttribute(MESSAGE, message);
    }
}
