/**
 * 
 */
package com.travelportal.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.travelportal.dao.ISearchDao;
import com.travelportal.exception.DaoException;
import com.travelportal.exception.ServiceLayerException;
import com.travelportal.model.ConnectorFsrModel;
import com.travelportal.service.ISearchService;

/**
 * 
 * Service class to search a flight
 * 
 * @author naveen.kumar
 * 
 */
public class SearchService implements ISearchService {

    private static Logger logger = Logger.getLogger(SearchService.class);
    private ISearchDao searchDao;

    public void setSearchDao(ISearchDao searchDao) {
        this.searchDao = searchDao;
    }

    /**
     * 
     * Service method to search a flight
     * 
     * @param fromCity
     *            {@link String}
     * @param toCity
     *            {@link String}
     * @param travelDate
     *            {@link Date}
     * @throws HibernateException
     * @return connectors {@link ConnectorFsrModel}
     * @throws ServiceLayerException
     */
    public List<ConnectorFsrModel> search(String fromCity, String toCity,
            Date travelDate) throws ServiceLayerException {
        try {
            List<ConnectorFsrModel> connectors = (List<ConnectorFsrModel>) searchDao
                    .searchFlight(fromCity, toCity, travelDate);
            durationCalculator(connectors, fromCity, toCity);
            return connectors;
        } catch (DaoException ex) {
            logger.error(String.format("NO FLIGHT FOUND FROM %s TO %s On %s",
                    fromCity, toCity, travelDate), ex);
            throw new ServiceLayerException("No flight found");
        }

    }

    /**
     * 
     * Method to calculate the flight duration
     * 
     * @param connectors
     *            {@link ConnectorFsrModel}
     * @param fromCity
     *            {@link String}
     * @param toCity
     *            {@link String}
     */
    public void durationCalculator(List<ConnectorFsrModel> connectors,
            String fromCity, String toCity) {
        logger.isInfoEnabled();
        logger.info("CALCULATING FLIGHT DURATION");
        try {
            for (int i = 0; i < connectors.size(); i++) {
                String time1 = connectors.get(i).getScheduleId()
                        .getSourceDepartureTime()
                        + MS;
                String time2 = connectors.get(i).getScheduleId()
                        .getDestinationArrivalTime()
                        + MS;
                String time3 = connectors.get(i).getScheduleId()
                        .getViaArrivalTime()
                        + MS;
                String time4 = connectors.get(i).getScheduleId()
                        .getViaDepartureTime()
                        + MS;
                SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
                Date date1 = format.parse(time1);
                Date date2 = format.parse(time2);
                Date date3 = format.parse(time3);
                Date date4 = format.parse(time4);

                if (connectors.get(i).getRouteId().getSource()
                        .equalsIgnoreCase(fromCity)
                        && connectors.get(i).getRouteId().getDestination()
                                .equalsIgnoreCase(toCity)) {
                    long difference = (date2.getTime() - date1.getTime())
                            / SIXTYTHOUSAND;
                    int hour = (int) (difference / SIXTY);
                    int minute = (int) (difference % SIXTY);
                    String durationSourceDest = hour + HOUR_S + minute
                            + MINUTE_S;
                    connectors.get(i).getScheduleId()
                            .setDuration(durationSourceDest);
                } else if (connectors.get(i).getRouteId().getSource()
                        .equalsIgnoreCase(fromCity)
                        && connectors.get(i).getRouteId().getVia()
                                .equalsIgnoreCase(toCity)) {
                    long difference = (date3.getTime() - date1.getTime())
                            / SIXTYTHOUSAND;
                    int hour = (int) (difference / SIXTY);
                    int minute = (int) (difference % SIXTY);
                    String durationSourceVia = hour + HOUR_S + minute
                            + MINUTE_S;
                    connectors.get(i).getScheduleId()
                            .setDuration(durationSourceVia);
                } else {
                    long difference = (date2.getTime() - date4.getTime())
                            / SIXTYTHOUSAND;
                    int hour = (int) (difference / SIXTY);
                    int minute = (int) (difference % SIXTY);
                    String durationViaDest = hour + HOUR_S + minute + MINUTE_S;
                    connectors.get(i).getScheduleId()
                            .setDuration(durationViaDest);
                }
            }
        } catch (Exception ex) {
            logger.error("ERROR IN CALCULATING FLIGHT DURATION", ex);
        }
    }
}
