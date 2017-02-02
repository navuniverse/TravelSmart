package com.travelportal.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.travelportal.exception.ServiceLayerException;
import com.travelportal.model.ConnectorFsrModel;
import com.travelportal.service.ISearchService;

/**
 * 
 * Controller class to manage the methods and mappings used in searching flight
 * 
 * @author naveen.kumar
 * 
 */
@Controller
public class SearchController {

    public static final int I_1900 = 1900;
    public static final String SEARCHRESULT = "searchresult";
    public static final String SEARCH = "search";
    public static final String MESSAGE = "message";
    public static final String INFANT = "infant";
    public static final String CHILDREN = "children";
    public static final String ADULT = "adult";
    public static final String TRAVELDATE1 = "travelDate1";
    public static final String TRAVELDATE = "travelDate";
    public static final String TOCITY = "toCity";
    public static final String FROMCITY = "fromCity";
    public static final String RESULT = "result";

    private Logger logger = Logger.getLogger(SearchController.class);

    private ISearchService searchService;
    private BaseController baseController;

    public void setSearchService(ISearchService searchService) {
        this.searchService = searchService;
    }

    public void setBaseController(BaseController baseController) {
        this.baseController = baseController;
    }

    /**
     * 
     * Method to search the flight based on search criteria entered by user
     * 
     * @param fromCity
     *            {@link String}
     * @param toCity
     *            {@link String}
     * @param travelDate
     *            {@link Date}
     * @param adult
     *            {@link Integer}
     * @param children
     *            {@link Integer}
     * @param infant
     *            {@link Integer}
     * @param model
     *            {@link Model}
     * @param request
     *            {@link HttpServletRequest}
     * @return "searchresult" or "search" page
     */
    @SuppressWarnings("deprecation")
    @RequestMapping(value = "/actionSearch", method = RequestMethod.POST)
    public String search(String fromCity, String toCity, Date travelDate,
            Model model, int adult, int children, int infant,
            HttpServletRequest request) {
        try {
            if (infant > adult) {
                new ExceptionController("Infant Can not be more than adult",
                        model);
            } else {
                logger.isInfoEnabled();
                logger.info(String.format(
                        "SEARCHING FLIGHT FROM %s TO %s ON %s", fromCity,
                        toCity, travelDate));
                List<ConnectorFsrModel> connectors = searchService.search(
                        fromCity, toCity, travelDate);

                // Setting search result and criteria in session
                baseController.setSessionAttribute(RESULT, connectors, request);
                baseController.setSessionAttribute(FROMCITY, fromCity, request);
                baseController.setSessionAttribute(TOCITY, toCity, request);
                baseController.setSessionAttribute(TRAVELDATE,
                        travelDate.getDate() + "/"
                                + (travelDate.getMonth() + 1) + "/"
                                + (travelDate.getYear() + I_1900), request);
                baseController.setSessionAttribute(TRAVELDATE1, travelDate,
                        request);
                baseController.setSessionAttribute(ADULT, adult, request);
                baseController.setSessionAttribute(CHILDREN, children, request);
                baseController.setSessionAttribute(INFANT, infant, request);
                return SEARCHRESULT;
            }
        } catch (ServiceLayerException e) {
            logger.error("ERROR IN SEARCHING FLIGHT", e);
            new ExceptionController("Error while searching flight", e, model);
        }
        return SEARCH;
    }
}