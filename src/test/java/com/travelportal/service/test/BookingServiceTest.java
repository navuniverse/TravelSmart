/**
 * 
 */
package com.travelportal.service.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.travelportal.dao.impl.BookingDao;
import com.travelportal.exception.ServiceLayerException;
import com.travelportal.model.ConnectorFsrModel;
import com.travelportal.model.PaymentModel;
import com.travelportal.model.RouteModel;
import com.travelportal.model.ScheduleModel;
import com.travelportal.model.TicketModel;
import com.travelportal.model.TravellerModel;
import com.travelportal.model.UserModel;
import com.travelportal.service.impl.BookingService;
import com.travelportal.util.test.HibernateUtil;

/**
 * @author Naveen
 * 
 */
public class BookingServiceTest {

    protected static BookingService bookingService;
    protected static ConnectorFsrModel connectorFsrModel;
    protected static TicketModel ticketModel;
    protected static TicketModel model2;
    protected static PaymentModel paymentModel;
    protected static UserModel userModel;
    protected static RouteModel routeModel;
    protected static List<TravellerModel> travellerModel;
    protected static ScheduleModel scheduleModel;
    protected static BookingDao bookingDao;
    protected static SessionFactory sessionFactory;

    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        bookingService = new BookingService();
        connectorFsrModel = new ConnectorFsrModel();
        ticketModel = new TicketModel();
        paymentModel = new PaymentModel();
        bookingDao = new BookingDao();
        bookingService.setBookingDao(bookingDao);
        model2 = new TicketModel();
        userModel = new UserModel();
        userModel.setUsername("naveen.kumar@impetus.co.in");
        travellerModel = new ArrayList<TravellerModel>();
        routeModel = new RouteModel();
        scheduleModel = new ScheduleModel();
        sessionFactory = HibernateUtil.getSessionFactory();
        bookingDao.setSessionFactory(sessionFactory);
    }

    /**
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        bookingDao = null;
        bookingService = null;
        connectorFsrModel = null;
        ticketModel = null;
        paymentModel = null;
        model2 = null;
        userModel = null;
        travellerModel = null;
        routeModel = null;
        scheduleModel = null;
        sessionFactory = null;
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
     * {@link com.travelportal.service.impl.BookingService#getFlightDetails(java.lang.String)}
     * .
     * 
     * @throws ServiceLayerException
     */
    @Test
    public void testGetFlightDetails() throws ServiceLayerException {
        assertEquals("1001", bookingService.getFlightDetails("1001")
                .getConnectorId());
    }

    /**
     * Test method for
     * {@link com.travelportal.service.impl.BookingService#bookFlight(com.travelportal.model.ConnectorFsrModel, java.lang.String, java.lang.String, java.lang.String, int)}
     * .
     * 
     * @throws ServiceLayerException
     */
    @Test
    public void testBookFlight() throws ServiceLayerException {
        connectorFsrModel.setConnectorId("1001");
        routeModel.setDestination("MUMBAI");
        routeModel.setRouteId("1");
        routeModel.setSource("DELHI");
        routeModel.setVia("JAIPUR");
        connectorFsrModel.setRouteId(routeModel);
        assertEquals(
                "1001",
                bookingService.bookFlight(connectorFsrModel, "DELHI", "MUMBAI",
                        "1001", 1).getConnectorId());
    }

    /**
     * Test method for
     * {@link com.travelportal.service.impl.BookingService#generateTicket(com.travelportal.model.TicketModel)}
     * .
     * 
     * @throws ServiceLayerException
     */
    @SuppressWarnings("deprecation")
    @Test
    public void testGenerateTicket() throws ServiceLayerException {
        userModel.setUsername("naveen.kumar@impetus.co.in");
        userModel.setPassword("tOiNqo9cBIEbwuJhBnqUgw==");
        userModel.setFirstname("Naveen");
        userModel.setLastname("Kumar");
        userModel.setMobileno("9716554117");
        userModel.setRole("NORMAL");
        userModel.setDob(new Date("08/07/1992"));
        ticketModel.setConnectorId("1001");
        ticketModel.setDestination("MUMBAI");
        ticketModel.setNoOfPassenger(1);
        ticketModel.setSource("DELHI");
        ticketModel.setStatus("BOOKED");
        ticketModel.setTotalPrice(3500);
        ticketModel.setTravelDate(new Date("12/01/2013"));
        ticketModel.setUserId(userModel);
        assertEquals(true, bookingService.generateTicket(ticketModel) != 0);
    }

    /**
     * Test method for
     * {@link com.travelportal.service.impl.BookingService#insertTravellers(com.travelportal.model.TicketModel, com.travelportal.model.TicketModel)}
     * .
     * 
     * @throws ServiceLayerException
     */
    @Test
    public void testInsertTravellers() throws ServiceLayerException {
        TravellerModel model = new TravellerModel();
        model.setFirstname("Naveen");
        model.setLastname("Kumar");
        model.setSex("Male");
        model.setAge(21);
        model.setTicketId(ticketModel);
        travellerModel.add(model);
        model2.setTravellers(travellerModel);
        bookingService.insertTravellers(model2, ticketModel);
        assertEquals(true, true);
    }

    /**
     * Test method for
     * {@link com.travelportal.service.impl.BookingService#payment(com.travelportal.model.PaymentModel, com.travelportal.model.TicketModel, int)}
     * .
     * 
     * @throws ServiceLayerException
     */
    @SuppressWarnings("deprecation")
    @Test
    public void testPayment() throws ServiceLayerException {
        paymentModel.setTicketId(15);
        paymentModel.setCardName("Naveen");
        paymentModel.setCardName("999999999999");
        paymentModel.setCardType("CREDIT");
        paymentModel.setCardExpiry(new Date("12/31/2015"));
        paymentModel.setTotalPrice(3500);
        bookingService.payment(paymentModel, ticketModel, 1234);
        assertEquals(true, true);
    }

    /**
     * Test method for
     * {@link com.travelportal.service.impl.BookingService#applyDeal(com.travelportal.model.ConnectorFsrModel, Integer, Integer, com.travelportal.model.TicketModel)}
     * .
     */
    @Test
    public void testApplyDeal() {
        connectorFsrModel.setConnectorId("1001");
        scheduleModel.setDealId("d1");
        connectorFsrModel.setScheduleId(scheduleModel);
        bookingService.applyDeal(connectorFsrModel, 3500, 1, ticketModel);
        assertEquals(true, true);
    }

}
