/**
 * 
 */
package com.travelportal.service.impl;

import org.apache.log4j.Logger;

import com.travelportal.dao.IBookingDao;
import com.travelportal.exception.DaoException;
import com.travelportal.exception.ServiceLayerException;
import com.travelportal.model.ConnectorFsrModel;
import com.travelportal.model.PaymentModel;
import com.travelportal.model.TicketModel;
import com.travelportal.model.TravellerModel;
import com.travelportal.service.IBookingService;

/**
 * 
 * Service class for booking process
 * 
 * @author naveen.kumar
 * 
 */
public class BookingService implements IBookingService {

    private static Logger logger = Logger.getLogger(BookingService.class);
    private IBookingDao bookingDao;

    public void setBookingDao(IBookingDao bookingDao) {
        this.bookingDao = bookingDao;
    }

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
    public ConnectorFsrModel getFlightDetails(String connectorId)
            throws ServiceLayerException {
        try {
            return bookingDao.getFlight(connectorId);
        } catch (DaoException ex) {
            logger.error(String.format(
                    "DETAILS OF FLIGHT WITH CONNECTOR ID %s NOT FOUND",
                    connectorId), ex);
            throw new ServiceLayerException("Flight details not found");
        }
    }

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
     * @throws HibernateException
     * @return connectorFsrModel {@link ConnectorFsrModel}
     * @throws ServiceLayerException
     */
    public ConnectorFsrModel bookFlight(ConnectorFsrModel fsrModel,
            String fromCity, String toCity, String connectorId, int passenger)
            throws ServiceLayerException {
        try {
            if (fsrModel.getRouteId().getSource().equals(fromCity)
                    && fsrModel.getRouteId().getDestination().equals(toCity)) {
                ConnectorFsrModel connectorFsrModel = bookingDao.bookFlight1(
                        connectorId, passenger);
                return connectorFsrModel;
            } else if (fsrModel.getRouteId().getSource().equals(fromCity)
                    && fsrModel.getRouteId().getVia().equals(toCity)) {
                ConnectorFsrModel connectorFsrModel = bookingDao.bookFlight2(
                        connectorId, passenger);
                return connectorFsrModel;
            } else {
                ConnectorFsrModel connectorFsrModel = bookingDao.bookFlight3(
                        connectorId, passenger);
                return connectorFsrModel;
            }
        } catch (DaoException ex) {
            logger.error(String.format(
                    "FLIGHT WITH CONNECTOR ID %s NOT BOOKED", connectorId), ex);
            throw new ServiceLayerException("Flight not booked");
        }

    }

    /**
     * 
     * Service method to generate ticket
     * 
     * @param ticketModel
     *            {@link TicketModel}
     * @throws HibernateException
     * @return bookingDao.passdata(ticketModel) {@link Integer}
     * @throws ServiceLayerException
     */
    public int generateTicket(TicketModel ticketModel)
            throws ServiceLayerException {
        try {
            return bookingDao.passdata(ticketModel);
        } catch (DaoException ex) {
            logger.error("TICKET NOT GENERATED", ex);
            throw new ServiceLayerException("Ticket not generated");
        }
    }

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
    public void insertTravellers(TicketModel model2, TicketModel ticketModel)
            throws ServiceLayerException {
        try {
            for (TravellerModel traveller : (model2.getTravellers())) {
                traveller.setTicketId(ticketModel);
                bookingDao.insertTraveller(traveller);
            }
        } catch (DaoException ex) {
            logger.error(String.format(
                    "TRAVELLER DETAILS OF TICKET %s NOT INSERTED",
                    ticketModel.getTicketId()), ex);
            throw new ServiceLayerException("Traveller Details not inserted");
        }
    }

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
    public void payment(PaymentModel paymentModel, TicketModel ticketModel,
            int cardNum) throws ServiceLayerException {
        paymentModel.setTicketId(ticketModel.getTicketId());
        paymentModel.setTotalPrice(ticketModel.getTotalPrice());
        paymentModel.setCardNo("************" + cardNum);
        try {
            bookingDao.payment(paymentModel);
        } catch (DaoException ex) {
            logger.error(String.format("PAYMENT UNSUCCESSFUL OF TICKET %s",
                    ticketModel.getTicketId()), ex);
            throw new ServiceLayerException("Payment Failed");
        }
    }

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
    public void applyDeal(ConnectorFsrModel fsrModel, int fare, int passenger,
            TicketModel ticketModel) {
        if (fsrModel.getScheduleId().getDealId().equals(D1)) {
            ticketModel.setTotalPrice((fare - DISCOUNT1) * passenger);
        } else if (fsrModel.getScheduleId().getDealId().equals(D2)) {
            ticketModel.setTotalPrice((fare - DISCOUNT2) * passenger);
        } else if (fsrModel.getScheduleId().getDealId().equals(D3)) {
            ticketModel.setTotalPrice((fare - DISCOUNT3) * passenger);
        } else {
            ticketModel.setTotalPrice(fare * passenger);
        }
    }
}
