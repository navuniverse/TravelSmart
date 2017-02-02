/**
 * 
 */
package com.travelportal.controller.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.ui.Model;

import com.travelportal.controller.BaseController;
import com.travelportal.controller.BookingController;
import com.travelportal.dao.impl.BookingDao;
import com.travelportal.exception.ServiceLayerException;
import com.travelportal.model.ConnectorFsrModel;
import com.travelportal.model.PaymentModel;
import com.travelportal.model.TicketModel;
import com.travelportal.model.TravellerModel;
import com.travelportal.model.UserModel;
import com.travelportal.service.impl.BookingService;
import com.travelportal.service.impl.PDFService;
import com.travelportal.util.test.HibernateUtil;

/**
 * 
 * Test class of {@link BookingController}
 * 
 * @author naveen.kumar
 * 
 */
public class TestBookingController {

    protected static BookingController bookingController;
    protected static BookingService bookingService;
    protected static BookingDao bookingDao;
    protected static SessionFactory sessionFactory;
    protected static PDFService pdfService;
    protected static BaseController baseController;
    protected static TicketModel ticketModel;
    protected static TicketModel model2;
    protected static TravellerModel travellerModel;
    protected static List<TravellerModel> travellers;
    protected static UserModel userId;
    protected static PaymentModel paymentModel;
    protected static MockHttpServletRequest request;
    protected static MockHttpServletResponse response;
    protected static Model model;

    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        bookingController = new BookingController();
        bookingService = new BookingService();
        bookingDao = new BookingDao();
        bookingService.setBookingDao(bookingDao);
        sessionFactory = HibernateUtil.getSessionFactory();
        pdfService = new PDFService();
        baseController = new BaseController();
        ticketModel = new TicketModel();
        model2 = new TicketModel();
        travellerModel = new TravellerModel();
        travellers = new ArrayList<TravellerModel>();
        userId = new UserModel();
        paymentModel = new PaymentModel();
        bookingDao.setSessionFactory(sessionFactory);
        bookingController.setBaseController(baseController);
        bookingController.setBookingService(bookingService);
        bookingController.setPdfService(pdfService);
        bookingController.setTicketModel(ticketModel);
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
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
        bookingController = null;
        bookingDao = null;
        bookingService = null;
        sessionFactory = null;
        pdfService = null;
        baseController = null;
        ticketModel = null;
        model2 = null;
        userId = null;
        paymentModel = null;
        request = null;
        response = null;
        model = null;
    }

    /**
     * Test method for
     * {@link com.travelportal.controller.BookingController#setBaseController(com.travelportal.controller.BaseController)}
     * .
     */
    @Test
    public void testSetBaseController() {
        bookingController.setBaseController(baseController);
        assertEquals(true, true);
    }

    /**
     * Test method for
     * {@link com.travelportal.controller.BookingController#setBookingService(com.travelportal.service.IBookingService)}
     * .
     */
    @Test
    public void testSetBookingService() {
        bookingController.setBookingService(bookingService);
        assertEquals(true, true);
    }

    /**
     * Test method for
     * {@link com.travelportal.controller.BookingController#setPdfService(com.travelportal.service.IPDFService)}
     * .
     */
    @Test
    public void testSetPdfService() {
        bookingController.setPdfService(pdfService);
        assertEquals(true, true);
    }

    /**
     * Test method for
     * {@link com.travelportal.controller.BookingController#setTicketModel(com.travelportal.model.TicketModel)}
     * .
     */
    @Test
    public void testSetTicketModel() {
        bookingController.setTicketModel(ticketModel);
        assertEquals(true, true);
    }

    /**
     * Test method for
     * {@link com.travelportal.controller.BookingController#bookpage()}.
     */
    @Test
    public void testBookpage() {
        assertEquals("book", bookingController.bookpage());
    }

    /**
     * Test method for
     * {@link com.travelportal.controller.BookingController#bookingPageData(java.lang.String, javax.servlet.http.HttpServletRequest, org.springframework.ui.Model)}
     * .
     * 
     * @throws ServiceLayerException
     */
    @Test
    public void testBookingPageData() {
        assertEquals("redirect:/book",
                bookingController.bookingPageData("1001", request, model));
    }

    /**
     * Test method for
     * {@link com.travelportal.controller.BookingController#book(com.travelportal.model.TicketModel, javax.servlet.http.HttpServletRequest, org.springframework.ui.Model)}
     * .
     * 
     * @throws ServiceLayerException
     */
    @SuppressWarnings("deprecation")
    @Test
    public void testBook() throws ServiceLayerException {
        travellerModel.setFirstname("Naveen");
        travellerModel.setLastname("Kumar");
        travellerModel.setSex("Male");
        travellerModel.setAge(21);
        travellers.add(travellerModel);
        model2.setTravellers(travellers);
        baseController.setSessionAttribute("model2", model2, request);
        ConnectorFsrModel connectorFsrModel = bookingService
                .getFlightDetails("1001");
        baseController
                .setSessionAttribute("flight", connectorFsrModel, request);
        baseController.setSessionAttribute("connectorId", "1001", request);
        baseController.setSessionAttribute("adult", 1, request);
        baseController.setSessionAttribute("children", 0, request);
        baseController.setSessionAttribute("infant", 0, request);
        baseController.setSessionAttribute("fromCity", "DELHI", request);
        baseController.setSessionAttribute("toCity", "MUMBAI", request);
        ticketModel.setConnectorId("1001");
        userId.setUsername("naveen.kumar@impetus.co.in");
        userId.setPassword("tOiNqo9cBIEbwuJhBnqUgw==");
        userId.setFirstname("Naveen");
        userId.setLastname("Kumar");
        userId.setMobileno("9716554117");
        userId.setRole("NORMAL");
        userId.setDob(new Date("08/07/1992"));
        baseController.setSessionAttribute("user1", userId, request);
        baseController.setSessionAttribute("travelDate1",
                new Date("12/01/2013"), request);
        ticketModel.setDestination("MUMBAI");
        ticketModel.setNoOfPassenger(1);
        ticketModel.setSource("DELHI");
        ticketModel.setStatus("BOOKED");
        ticketModel.setTotalPrice(5000);
        ticketModel.setTravelDate(new Date("12/01/2013"));
        ticketModel.setUserId(userId);
        baseController.setSessionAttribute("ticketdata", ticketModel, request);
        assertEquals("payment", bookingController.book(model2, request, model));
    }

    /**
     * Test method for
     * {@link com.travelportal.controller.BookingController#payment(com.travelportal.model.PaymentModel, int, javax.servlet.http.HttpServletRequest, org.springframework.ui.Model)}
     * .
     * 
     * @throws ServiceLayerException
     */
    @SuppressWarnings("deprecation")
    @Test
    public void testPayment() throws ServiceLayerException {
        baseController.setSessionAttribute("adult", 1, request);
        baseController.setSessionAttribute("children", 0, request);
        baseController.setSessionAttribute("connectorId", "1001", request);
        ConnectorFsrModel connectorFsrModel = bookingService
                .getFlightDetails("1001");
        baseController
                .setSessionAttribute("flight", connectorFsrModel, request);
        baseController.setSessionAttribute("fromCity", "DELHI", request);
        baseController.setSessionAttribute("toCity", "MUMBAI", request);
        ticketModel.setDestination("MUMBAI");
        ticketModel.setNoOfPassenger(1);
        ticketModel.setSource("DELHI");
        ticketModel.setStatus("BOOKED");
        ticketModel.setTotalPrice(5000);
        ticketModel.setTravelDate(new Date("12/01/2013"));
        ticketModel.setUserId(userId);
        baseController.setSessionAttribute("ticketdata", ticketModel, request);
        travellerModel.setFirstname("Naveen");
        travellerModel.setLastname("Kumar");
        travellerModel.setSex("Male");
        travellerModel.setAge(21);
        travellers.add(travellerModel);
        model2.setTravellers(travellers);
        baseController.setSessionAttribute("model2", model2, request);
        paymentModel.setCardExpiry(new Date("01/15/2014"));
        paymentModel.setCardName("Naveen Kumar");
        paymentModel.setCardType("CREDIT");
        paymentModel.setTotalPrice(5000);
        assertEquals("ticket",
                bookingController.payment(paymentModel, 2345, request, model));
    }

    /**
     * Test method for
     * {@link com.travelportal.controller.BookingController#ticket()}.
     */
    @Test
    public void testTicket() {
        assertEquals("ticket", bookingController.ticket());
    }

    /**
     * Test method for
     * {@link com.travelportal.controller.BookingController#ticketPDF(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)}
     * .
     * 
     * @throws ServiceLayerException
     */
    @Test
    public void testTicketPDF() throws ServiceLayerException {
        ConnectorFsrModel connectorFsrModel = bookingService
                .getFlightDetails("1001");
        baseController
                .setSessionAttribute("flight", connectorFsrModel, request);
        baseController.setSessionAttribute("currentTicketId", 2, request);
        assertEquals("welcome", bookingController.ticketPDF(request, response));
    }

}
