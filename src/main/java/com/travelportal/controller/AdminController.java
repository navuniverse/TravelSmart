package com.travelportal.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.travelportal.exception.ServiceLayerException;
import com.travelportal.model.ConnectorFsrModel;
import com.travelportal.model.DealModel;
import com.travelportal.model.FlightModel;
import com.travelportal.service.IAdminService;
import com.travelportal.service.IDealService;
import com.travelportal.service.IPDFService;

/**
 * 
 * Controller class to manage methods and mapping used for admin functionalities
 * 
 * @author naveen.kumar
 * 
 */
@Controller
public class AdminController {
    public static final String DEALS = "deals";
    public static final String SCHEDULE = "schedule";
    public static final String FLIGHTID = "flightId";
    public static final String UPDATEFLIGHT = "updateflight";
    public static final String DELETEFLIGHT = "deleteflight";
    public static final String ADDFLIGHT = "addflight";
    private Logger logger = Logger.getLogger(AdminController.class);
    private static final int BUFFERSIZE = 4096;
    public static final String COMMAND = "command";
    public static final String MESSAGE = "message";

    private IAdminService adminService;
    private IDealService dealService;
    private IPDFService pdfService;
    private BaseController baseController;

    public void setBaseController(BaseController baseController) {
        this.baseController = baseController;
    }

    public void setAdminService(IAdminService adminService) {
        this.adminService = adminService;
    }

    public void setDealService(IDealService dealService) {
        this.dealService = dealService;
    }

    public void setPdfService(IPDFService pdfService) {
        this.pdfService = pdfService;
    }

    /**
     * 
     * Method to add a new flight
     * 
     * @param model
     *            {@link Model}
     * @param flightModel
     *            {@link FlightModel}
     * @return "addflight" page
     */
    @RequestMapping(value = "/actionAdd", method = RequestMethod.POST)
    public String addFlight(FlightModel flightModel, Model model) {
        try {
            logger.isInfoEnabled();
            logger.info(String.format("ADDING NEW FLIGHT %s",
                    flightModel.getFlightId()));
            adminService.addFlight(flightModel);
            model.addAttribute(MESSAGE, "Flight Successfully Added!!!");
        } catch (ServiceLayerException ex) {
            logger.error(
                    String.format("Flight %s not added",
                            flightModel.getFlightId()), ex);
            new ExceptionController("Adding new flight failed.", ex, model);
        }
        return ADDFLIGHT;
    }

    /**
     * 
     * Method to delete a flight
     * 
     * @param model
     *            {@link Model}
     * @param flightModel
     *            {@link FlightModel}
     * @return "deleteflight" page
     */
    @RequestMapping(value = "/actionDelete", method = RequestMethod.POST)
    public String deleteFlight(FlightModel flightModel, Model model) {
        try {
            logger.isInfoEnabled();
            logger.info(String.format("DELETING FLIGHT %s",
                    flightModel.getFlightId()));
            adminService.deleteFlight(flightModel);
            model.addAttribute(MESSAGE, "Flight Successfully Deleted!!!");
        } catch (ServiceLayerException ex) {
            logger.error(
                    String.format("Flight %s not deleted",
                            flightModel.getFlightId()), ex);
            new ExceptionController("Deleting flight failed.", ex, model);
        }
        return DELETEFLIGHT;
    }

    /**
     * 
     * Method to update a flight
     * 
     * @param model
     *            {@link Model}
     * @param flightModel
     *            {@link FlightModel}
     * @return "updateflight" page
     */
    @RequestMapping(value = "/actionUpdate", method = RequestMethod.POST)
    public String updateFlight(FlightModel flightModel, Model model) {
        logger.isInfoEnabled();
        logger.info(String.format("UPDATING FLIGHT %s",
                flightModel.getFlightId()));
        try {
            adminService.updateFlight(flightModel);
            model.addAttribute(MESSAGE, "Flight Successfully Updated!!!");
        } catch (ServiceLayerException ex) {
            logger.error(
                    String.format("Flight %s not updated",
                            flightModel.getFlightId()), ex);
            new ExceptionController("Updating flight failed.", ex, model);
        }
        return UPDATEFLIGHT;
    }

    /**
     * 
     * Method to find schedule of flight providing flighId
     * 
     * @param flightId
     *            {@link String}
     * @param request
     *            {@link HttpServletRequest}
     * @return "schedule" page
     */
    @RequestMapping(value = "/actionSchedule", method = RequestMethod.POST)
    public String showSchedule(String flightId, HttpServletRequest request,
            Model model) {
        logger.isInfoEnabled();
        logger.info(String.format("RETRIEVING SCHEDULE OF FLIGHT %s", flightId));
        try {
            List<ConnectorFsrModel> fsrModels = (List<ConnectorFsrModel>) adminService
                    .getSchedule(flightId);
            baseController.setSessionAttribute(FLIGHTID, flightId, request);
            baseController.setSessionAttribute(SCHEDULE, fsrModels, request);
        } catch (ServiceLayerException ex) {
            logger.error(
                    String.format("SCHEDULE OF FLIGHT %s NOT FOUND", flightId),
                    ex);
            new ExceptionController("Flight Schedule not found", ex, model);
        }
        return SCHEDULE;
    }

    /**
     * 
     * Controller method to display the top deals
     * 
     * @param request
     *            {@link HttpServletRequest}
     * @return "deals" page
     */
    @RequestMapping(value = "/actionDeals", method = RequestMethod.POST)
    public String showDeals(HttpServletRequest request, Model model) {
        logger.isInfoEnabled();
        logger.info("RETRIEVING DEALS");
        try {
            List<DealModel> dealModels = (List<DealModel>) dealService
                    .getDeals();
            baseController.setSessionAttribute(DEALS, dealModels, request);
        } catch (ServiceLayerException ex) {
            logger.error("NO DEAL FOUND", ex);
            new ExceptionController("No Deal Found", ex, model);
        }
        return DEALS;
    }

    /**
     * 
     * Method to generate the PDF of flight Schedule using flightId and calling
     * util method createSchedulePDF(flightId)
     * 
     * @param request
     *            {@link HttpServletRequest}
     * @param response
     *            {@link HttpServletResponse}
     * @return "schedule" page and pdf in new tab
     */
    @RequestMapping(value = "/createSchedulePDF", method = RequestMethod.GET)
    public String createSchedulePDF(HttpServletRequest request,
            HttpServletResponse response) {
        try {
            HttpSession session = request.getSession();
            String flightId = (String) baseController.getSessionAttribute(
                    FLIGHTID, request);
            pdfService.createSchedulePDF(flightId);

            ServletContext context = (ServletContext) session
                    .getServletContext();

            // construct the complete absolute path of the file
            String fullPath = "D:\\Travel_Portal\\Schedule\\Schedule_"
                    + flightId + ".pdf";
            File downloadFile = new File(fullPath);
            FileInputStream inputStream = null;
            inputStream = new FileInputStream(downloadFile);

            // set content attributes for the response
            response.setContentType(pdfService.getMime(context, fullPath));
            response.setContentLength((int) downloadFile.length());

            // set headers for the response
            String headerKey = "Content-Disposition";
            String headerValue = String.format("filename=\"%s\"",
                    downloadFile.getName());
            response.setHeader(headerKey, headerValue);

            // get output stream of the response
            OutputStream outStream = null;
            outStream = response.getOutputStream();

            byte[] buffer = new byte[BUFFERSIZE];
            int bytesRead = -1;

            // write bytes read from the input stream into the output stream
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, bytesRead);
            }

            inputStream.close();
            outStream.close();
            logger.isInfoEnabled();
            logger.info(String
                    .format("SCHEDULE PDF GENERATION AND DOWNLOAD SUCCESSFUL OF FLIGHT %s",
                            flightId));
            return SCHEDULE;
        } catch (Exception e) {
            logger.error("SCHEDULE PDF GENERATION OR DOWNLOAD FAIL", e);
            return null;
        }
    }
}