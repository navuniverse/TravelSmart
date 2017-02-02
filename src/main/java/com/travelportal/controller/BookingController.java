package com.travelportal.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.travelportal.exception.ServiceLayerException;
import com.travelportal.model.ConnectorFsrModel;
import com.travelportal.model.PaymentModel;
import com.travelportal.model.TicketModel;
import com.travelportal.model.UserModel;
import com.travelportal.service.IBookingService;
import com.travelportal.service.IPDFService;

/**
 * 
 * Controller class to manage methods and mappings used for flight booking
 * 
 * @author naveen.kumar
 * 
 */
@Controller
public class BookingController {
    public static final String REDIRECT_ERROR = "redirect:/error";
    public static final String REDIRECT_WELCOME = "redirect:/welcome";
    public static final String PAYMENT = "payment";
    public static final String REDIRECT_BOOK = "redirect:/book";
    public static final String WELCOME = "welcome";
    public static final String TICKET = "ticket";
    public static final String CURRENTTICKETID = "currentTicketId";
    public static final String BOOKED = "BOOKED";
    public static final String TICKETDATA = "ticketdata";
    public static final String USER = "user";
    public static final String TRAVELDATE1 = "travelDate1";
    public static final String TOCITY = "toCity";
    public static final String FROMCITY = "fromCity";
    public static final String TOTAL = "total";
    public static final String TOTALPASS = "totalpass";
    public static final String INFANT = "infant";
    public static final String CHILDREN = "children";
    public static final String ADULT = "adult";
    public static final String TRAVELLERS = "travellers";
    public static final String MODEL2 = "model2";
    public static final String CONNECTORID = "connectorId";
    public static final String BOOK = "book";
    public static final String FARESOURCEDEST = "faresourcedest";
    public static final String FAREVIADEST = "fareviadest";
    public static final String FARESOURCEVIA = "faresourcevia";
    public static final String FLIGHT = "flight";
    public static final int BUFFERSIZE = 4096;
    public static final String USER1 = "user1";

    private Logger logger = Logger.getLogger(BookingController.class);

    private IBookingService bookingService;
    private IPDFService pdfService;
    private TicketModel ticketModel;
    private BaseController baseController;

    public void setBaseController(BaseController baseController) {
        this.baseController = baseController;
    }

    public void setBookingService(IBookingService bookingService) {
        this.bookingService = bookingService;
    }

    public void setPdfService(IPDFService pdfService) {
        this.pdfService = pdfService;
    }

    public TicketModel getTicketModel() {
        return ticketModel;
    }

    public void setTicketModel(TicketModel ticketModel) {

        this.ticketModel = ticketModel;
    }

    /**
     * 
     * Mapping to go on booking form page
     * 
     * @param model
     *            {@link Model}
     * @return booking page
     */
    @RequestMapping(value = "/book", method = RequestMethod.GET)
    public String bookpage() {
        return BOOK;
    }

    /**
     * 
     * Selecting a particular flight and displaying the booking form according
     * to search criteria
     * 
     * @param connectorId
     *            {@link String}
     * @param request
     *            {@link HttpServletRequest}
     * @return "redirect:/book"
     */
    @RequestMapping(value = "/searchresult/{connectorId}", method = RequestMethod.GET)
    public String bookingPageData(
            @PathVariable(CONNECTORID) String connectorId,
            HttpServletRequest request, Model model) {
        try {
            ConnectorFsrModel connectorFsrModel = bookingService
                    .getFlightDetails(connectorId);
            logger.isInfoEnabled();
            logger.info(String.format("SELECTING REQUESTED FLIGHT %s",
                    connectorFsrModel.getFlightId().getFlightId()));
            baseController.setSessionAttribute(CONNECTORID, connectorId,
                    request);
            baseController.setSessionAttribute(FLIGHT, connectorFsrModel,
                    request);
        } catch (ServiceLayerException ex) {
            logger.error(String.format("FLIGHT DEATILS NOT FOUND"), ex);
            new ExceptionController("Flight not found", ex, model);
        }
        return REDIRECT_BOOK;
    }

    /**
     * 
     * Method to take the travellers data and book the flight. Redirect control
     * to payment page
     * 
     * @param model2
     *            {@link TicketModel}
     * @param request
     *            {@link HttpServletRequest}
     * @param model
     *            {@link Model}
     * @return "payment" page
     */
    @RequestMapping(value = "/actionBook", method = RequestMethod.POST)
    public String book(TicketModel model2, HttpServletRequest request,
            Model model) {
        logger.isInfoEnabled();
        logger.info("BOOKING PROCESS STARTED");

        // Setting and getting data from session
        baseController.setSessionAttribute(MODEL2, model2, request);
        baseController.setSessionAttribute(TRAVELLERS, model2, request);

        int passenger = (Integer) baseController.getSessionAttribute(ADULT,
                request)
                + (Integer) baseController.getSessionAttribute(CHILDREN,
                        request);
        int total = passenger
                + (Integer) baseController.getSessionAttribute(INFANT, request);
        baseController.setSessionAttribute(TOTALPASS, total, request);
        baseController.setSessionAttribute(TOTAL, passenger, request);

        String connectorId = (String) baseController.getSessionAttribute(
                CONNECTORID, request);
        ConnectorFsrModel fsrModel = (ConnectorFsrModel) baseController
                .getSessionAttribute(FLIGHT, request);
        String fromCity = (String) baseController.getSessionAttribute(FROMCITY,
                request);
        String toCity = (String) baseController.getSessionAttribute(TOCITY,
                request);

        // calculating fare
        try {
            fareCalculator(fsrModel, fromCity, toCity, ticketModel, request);
        } catch (HibernateException ex) {
            new ExceptionController("Error while booking flight. Try again.",
                    ex, model);
            return REDIRECT_WELCOME;
        }

        ticketModel.setConnectorId(connectorId);
        ticketModel.setNoOfPassenger(total);
        ticketModel.setTravelDate((Date) baseController.getSessionAttribute(
                TRAVELDATE1, request));
        ticketModel.setSource(fromCity);
        ticketModel.setDestination(toCity);
        ticketModel.setStatus(BOOKED);
        ticketModel.setUserId((UserModel) baseController.getSessionAttribute(
                USER1, request));
        baseController.setSessionAttribute(TICKETDATA, ticketModel, request);

        return PAYMENT;
    }

    /**
     * 
     * Method to generate ticket after the payment
     * 
     * @param model
     *            {@link Model}
     * @param paymentModel
     *            {@link PaymentModel}
     * @param request
     *            {@link HttpServletRequest}
     * @return "ticket" page
     */
    @RequestMapping(value = "/actionPayment", method = RequestMethod.POST)
    public String payment(PaymentModel paymentModel, int cardNum,
            HttpServletRequest request, Model model) {
        ticketModel = null;
        // Setting and getting data from sessions
        int passenger = (Integer) baseController.getSessionAttribute(ADULT,
                request)
                + (Integer) baseController.getSessionAttribute(CHILDREN,
                        request);

        String connectorId = (String) baseController.getSessionAttribute(
                CONNECTORID, request);
        ConnectorFsrModel fsrModel = (ConnectorFsrModel) baseController
                .getSessionAttribute(FLIGHT, request);
        String fromCity = (String) baseController.getSessionAttribute(FROMCITY,
                request);
        String toCity = (String) baseController.getSessionAttribute(TOCITY,
                request);

        try {
            // Booking flight and updating seat availability
            ConnectorFsrModel connectorFsrModel = bookingService.bookFlight(
                    fsrModel, fromCity, toCity, connectorId, passenger);
            baseController.setSessionAttribute(FLIGHT, connectorFsrModel,
                    request);
        } catch (ServiceLayerException ex) {
            logger.error("FLIGHT NOT BOOKED", ex);
            new ExceptionController("Flight not booked", ex, model);
            return REDIRECT_ERROR;
        }

        try {
            ticketModel = (TicketModel) baseController.getSessionAttribute(
                    TICKETDATA, request);
            baseController.setSessionAttribute(CURRENTTICKETID,
                    bookingService.generateTicket(ticketModel), request);
            logger.isInfoEnabled();
            logger.info(String.format("TICKET %s GENERATED",
                    ticketModel.getTicketId()));
        } catch (ServiceLayerException ex) {
            logger.error("TICKET NOT GENERATED", ex);
            new ExceptionController(
                    "Error while generating ticket. Try again.", ex, model);
            return REDIRECT_ERROR;
        }

        try {
            TicketModel model2 = (TicketModel) baseController
                    .getSessionAttribute(MODEL2, request);
            bookingService.insertTravellers(model2, ticketModel);
            logger.isInfoEnabled();
            logger.info(String.format(
                    "TRAVELLERS CORRESPONDING TO TICKET ID %s INSERTED",
                    ticketModel.getTicketId()));
        } catch (ServiceLayerException ex) {
            logger.error(String.format(
                    "TRAVELLER DETAILS OF TICKET %s NOT INSERTED",
                    ticketModel.getTicketId()), ex);
            new ExceptionController("Traveller data not inserted", ex, model);
            return REDIRECT_ERROR;
        }

        try {
            bookingService.payment(paymentModel, ticketModel, cardNum);
            logger.isInfoEnabled();
            logger.info(String.format(
                    "INSERTING PAYMENT DATA FOR PAYMENT ID %s",
                    paymentModel.getPaymentId()));
        } catch (ServiceLayerException ex) {
            logger.error(String.format("PAYMENT UNSUCCESSFUL OF TICKET %s",
                    ticketModel.getTicketId()), ex);
            new ExceptionController("Payment and ticket booking failed", ex,
                    model);
            return REDIRECT_ERROR;
        }

        return TICKET;
    }

    /**
     * 
     * Mapping to display the booked ticket
     * 
     * @return "ticket" page
     */
    @RequestMapping(value = "/ticket", method = RequestMethod.POST)
    public String ticket() {
        return TICKET;

    }

    /**
     * 
     * Method to generate the PDF of ticket using ticketId and calling util
     * method createTicketPDF(ticketId)
     * 
     * @param request
     *            {@link HttpServletRequest}
     * @param response
     *            {@link HttpServletResponse}
     * @return "ticket" page and pdf in new tab
     */
    @RequestMapping(value = "/createTicketPDF", method = RequestMethod.GET)
    public String ticketPDF(HttpServletRequest request,
            HttpServletResponse response) {
        try {
            HttpSession session = request.getSession();
            int currentTicketId = (Integer) baseController.getSessionAttribute(
                    CURRENTTICKETID, request);
            ConnectorFsrModel fsrModel = (ConnectorFsrModel) baseController
                    .getSessionAttribute(FLIGHT, request);
            pdfService.createTicketPDF(currentTicketId, fsrModel);

            ServletContext context = (ServletContext) session
                    .getServletContext();

            // construct the complete absolute path of the file
            String fullPath = "D:\\Travel_Portal\\Ticket\\Ticket_"
                    + currentTicketId + ".pdf";

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
                    .format("TICKET PDF GENERATION AND DOWNLOAD SUCCESSFUL FOR TICKET ID %s",
                            currentTicketId));
            return WELCOME;
        } catch (Exception e) {
            logger.error("TICKET PDF GENERATION OR DOWNLOAD FAIL", e);
            return WELCOME;
        }
    }

    /**
     * 
     * Method to calculate fare after applying deal
     * 
     * @param fsrModel
     *            {@link ConnectorFsrModel}
     * @param fromCity
     *            {@link String}
     * @param toCity
     *            {@link String}
     * @param ticketModel
     *            {@link TicketModel}
     * @param session
     *            {@link HttpSession}
     * 
     */
    public void fareCalculator(ConnectorFsrModel fsrModel, String fromCity,
            String toCity, TicketModel ticketModel, HttpServletRequest request) {
        int passenger = (Integer) baseController.getSessionAttribute(ADULT,
                request)
                + (Integer) baseController.getSessionAttribute(CHILDREN,
                        request);

        // Calculating total fare after applying deals if flight is searched
        // from its source to destination
        if (fsrModel.getRouteId().getSource().equals(fromCity)
                && fsrModel.getRouteId().getDestination().equals(toCity)) {
            baseController.setSessionAttribute(FARESOURCEDEST, fsrModel
                    .getScheduleId().getFareSourceDestination(), request);
            int fare = fsrModel.getScheduleId().getFareSourceDestination();

            // Applying deal
            bookingService.applyDeal(fsrModel, fare, passenger, ticketModel);
        }

        // Calculating total fare after applying deals if flight is searched
        // from its source to via
        else if (fsrModel.getRouteId().getSource().equals(fromCity)
                && fsrModel.getRouteId().getVia().equals(toCity)) {
            baseController.setSessionAttribute(FARESOURCEVIA, fsrModel
                    .getScheduleId().getFareSourceVia(), request);
            int fare = fsrModel.getScheduleId().getFareSourceVia();
            // Applying deal
            bookingService.applyDeal(fsrModel, fare, passenger, ticketModel);
        }

        // Calculating total fare after applying deals if flight is searched
        // from its via to destination
        else {
            baseController.setSessionAttribute(FAREVIADEST, fsrModel
                    .getScheduleId().getFareViaDestination(), request);
            int fare = fsrModel.getScheduleId().getFareViaDestination();
            // Applying deal
            bookingService.applyDeal(fsrModel, fare, passenger, ticketModel);

        }
    }
}