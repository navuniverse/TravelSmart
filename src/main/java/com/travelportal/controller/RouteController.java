package com.travelportal.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.travelportal.exception.ServiceLayerException;
import com.travelportal.service.IRouteService;

/**
 * 
 * Class to dynamically fetch the list of source and destination from DB
 * 
 * @author naveen.kumar
 * 
 */
@Controller
public class RouteController {

    private static Logger logger = Logger.getLogger(RouteController.class);

    private IRouteService routeService;

    public void setRouteService(IRouteService routeService) {
        this.routeService = routeService;
    }

    /**
     * 
     * Method to fetch list of source cities dynamically from DB
     * 
     * @return routeService .getDestinationCities(fromCity)
     */
    @RequestMapping(value = "/findSource", method = RequestMethod.GET)
    @ResponseBody
    public List<String> getSourceList() {
        logger.isInfoEnabled();
        logger.info("FETCHING LIST OF SOURCE CITIES");
        try {
            return routeService.getSourceCities();
        } catch (ServiceLayerException ex) {
            logger.error("NO SOURCE CITY AVAILABLE", ex);
            return null;
        }
    }

    /**
     * 
     * Method to fetch list of destination cities dynamically from DB
     * 
     * @param fromCity
     *            {@link String}
     * @return routeService .getDestinationCities(fromCity)
     */
    @RequestMapping(value = "/findDest", method = RequestMethod.GET)
    @ResponseBody
    public List<String> getDestinationList(
            @RequestParam("fromCity") String fromCity) {
        logger.isInfoEnabled();
        logger.info("FETCHING LIST OF DESTINATION CITIES");
        try {
            return routeService.getDestinationCities(fromCity);
        } catch (ServiceLayerException ex) {
            logger.error(String.format(
                    "NO DESTINATION CITY CORRESPONDING TO %s", fromCity), ex);
            return null;
        }
    }
}