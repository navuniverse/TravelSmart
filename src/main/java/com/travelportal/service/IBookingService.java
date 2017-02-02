package com.travelportal.service;

import com.travelportal.dao.IBookingDao;
import com.travelportal.exception.ServiceLayerException;
import com.travelportal.model.ConnectorFsrModel;
import com.travelportal.model.PaymentModel;
import com.travelportal.model.TicketModel;

/**
 * 
 * Service interface for booking process
 * 
 * @author naveen.kumar
 * 
 */
public interface IBookingService {

    String D3 = "d3";
    String D2 = "d2";
    String D1 = "d1";
    int DISCOUNT1 = 500;
    int DISCOUNT2 = 1000;
    int DISCOUNT3 = 1000;

    /**
     * 
     * Setter of bookingDao object
     * 
     * @param bookingDao
     *            {@link IBookingDao}
     */
    void setBookingDao(IBookingDao bookingDao);

    /**
     * 
     * Service method to get the details of selected flight
     * 
     * @param connectorId
     *            {@link String}
     * @return bookingDao.getFlight(connectorId) {@link ConnectorFsrModel}
     * @throws ServiceLayerException
     * 
     */
    ConnectorFsrModel getFlightDetails(String connectorId)
            throws ServiceLayerException;

    /**
     * 
     * Service method to book a flight
     * 
     * @param fsrModel
     *            {@link ConnectorFsrModel}
     * @param fromCity
     *            {@link String}
     * @param toCity
     *            {@link String}
     * @param connectorId
     *            {@link String}
     * @param passenger
     *            {@link Integer}
     * @return connectorFsrModel {@link ConnectorFsrModel}
     * @throws ServiceLayerException
     */
    ConnectorFsrModel bookFlight(ConnectorFsrModel fsrModel, String fromCity,
            String toCity, String connectorId, int passenger)
            throws ServiceLayerException;

    /**
     * 
     * Service method to generate ticket
     * 
     * @param ticketModel
     *            {@link TicketModel}
     * @return bookingDao.passdata(ticketModel) {@link Integer}
     * @throws ServiceLayerException
     */
    int generateTicket(TicketModel ticketModel) throws ServiceLayerException;

    /**
     * 
     * Service method to generate ticket
     * 
     * @param model2
     *            {@link TicketModel}
     * @param ticketModel
     *            {@link TicketModel}
     * @throws ServiceLayerException
     */
    void insertTravellers(TicketModel model2, TicketModel ticketModel)
            throws ServiceLayerException;

    /**
     * 
     * Service method to insert payment details
     * 
     * @param paymentModel
     *            {@link PaymentModel}
     * @param ticketModel
     *            {@link TicketModel}
     * @param cardNum
     *            {@link Integer}
     * @throws ServiceLayerException
     */
    void payment(PaymentModel paymentModel, TicketModel ticketModel, int cardNum)
            throws ServiceLayerException;

    /**
     * 
     * Service method to apply deal on ticket
     * 
     * @param fsrModel
     *            {@link ConnectorFsrModel}
     * @param fare
     *            {@link Integer}
     * @param passenger
     *            {@link Integer}
     * @param ticketModel
     *            {@link TicketModel}
     */
    void applyDeal(ConnectorFsrModel fsrModel, int fare, int passenger,
            TicketModel ticketModel);

}