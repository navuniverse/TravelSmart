package com.travelportal.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.travelportal.exception.ServiceLayerException;
import com.travelportal.model.DealModel;
import com.travelportal.model.UserModel;
import com.travelportal.service.IDealService;

/**
 * 
 * Controller class to manage all the mapping to display view pages
 * 
 * @author naveen.kumar
 * 
 */
@Controller
public class MappingController {

    public static final String ERROR = "error";
    private IDealService dealService;
    private BaseController baseController;

    public void setBaseController(BaseController baseController) {
        this.baseController = baseController;
    }

    public void setDealService(IDealService dealService) {
        this.dealService = dealService;
    }

    public static final String XMLUPLOAD = "xmlupload";
    public static final String COMMAND = "command";
    public static final String CSVUPLOAD = "csvupload";
    public static final String ADMIN = "admin";
    public static final String START = "start";
    public static final String DEALS = "deals";
    public static final String USER = "user";
    private static Logger logger = Logger.getLogger(MappingController.class);

    /**
     * 
     * Mapping to display the start page of application
     * 
     * @return "start" page
     */
    @RequestMapping(value = "/start", method = RequestMethod.GET)
    public String start(HttpServletRequest request, Model model) {
        try {
            List<DealModel> deals = dealService.getDeals();
            baseController.setSessionAttribute(DEALS, deals, request);
            logger.isInfoEnabled();
            logger.info("START PAGE DISPLAYED");
        } catch (ServiceLayerException ex) {
            logger.error("NO DEAL FOUND", ex);
            new ExceptionController("No deal found", ex, model);
        }

        return START;
    }

    /**
     * 
     * Mapping to display the error page
     * 
     * @return "error" page
     */
    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String error() {
        return ERROR;
    }

    /**
     * 
     * Mapping to display the admin home page
     * 
     * @return "admin" page
     */
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String admin() {
        logger.isInfoEnabled();
        logger.info("ADMIN PAGE DISPLAYED");
        return ADMIN;
    }

    /**
     * 
     * Mapping to display the CSV File upload page
     * 
     * @param userModel
     *            {@link UserModel}
     * @param request
     *            {@link HttpServletRequest}
     * @return "csvupload" page
     */
    @RequestMapping(value = "/csvupload", method = RequestMethod.GET)
    public ModelAndView csvupload(UserModel userModel,
            HttpServletRequest request) {
        UserModel userModel2 = (UserModel) baseController.getSessionAttribute(
                USER, request);
        baseController.setSessionAttribute(USER, userModel2, request);
        logger.isInfoEnabled();
        logger.info("CSV UPLOAD PAGE DISPLAYED");
        return new ModelAndView(CSVUPLOAD, COMMAND, userModel);
    }

    /**
     * 
     * Mapping to display the XML File upload page
     * 
     * @param userModel
     *            {@link UserModel}
     * @param request
     *            {@link HttpServletRequest}
     * @return "xmlupload" page
     */
    @RequestMapping(value = "/xmlupload", method = RequestMethod.GET)
    public ModelAndView xmlupload(UserModel userModel,
            HttpServletRequest request) {
        UserModel userModel2 = (UserModel) baseController.getSessionAttribute(
                USER, request);
        baseController.setSessionAttribute(USER, userModel2, request);
        logger.isInfoEnabled();
        logger.info("XML UPLOAD PAGE DISPLAYED");
        return new ModelAndView(XMLUPLOAD, COMMAND, userModel);
    }

}
