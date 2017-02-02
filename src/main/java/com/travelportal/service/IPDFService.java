package com.travelportal.service;

import java.io.File;

import javax.servlet.ServletContext;

import com.travelportal.dao.IAdminDao;
import com.travelportal.dao.IBookingDao;
import com.travelportal.dao.IUserDao;
import com.travelportal.model.ConnectorFsrModel;

/**
 * 
 * Service interface to create the PDF files
 * 
 * @author naveen.kumar
 * 
 */
public interface IPDFService {

    float F_85_0F = 85.0f;
    float F_75_0F = 75.0f;
    String AGE = "Age";
    String SEX = "Sex";
    String LAST_NAME = "Last Name";
    String FIRST_NAME = "First Name";
    String TIME = "Time";
    String ROUTE = "Route";
    String DATE = "Date";
    String SCHEDULE_ID = "Schedule ID";
    int I_3 = 3;
    int I_2 = 2;
    int I_80 = 80;
    int I_161 = 161;
    int I_10 = 10;
    int I_4 = 4;
    String ARROW = "-->";
    String EXTN = ".pdf";

    /**
     * 
     * Setter of userDao object
     * 
     * @param userDao
     *            {@link IUserDao}
     */
    void setUserDao(IUserDao userDao);

    /**
     * 
     * Setter of adminDao object
     * 
     * @param adminDao
     *            {@link IAdminDao}
     */
    void setAdminDao(IAdminDao adminDao);

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
     * Method creating the PDF file of travel history corresponding to a user id
     * and saving the PDF to a folder specified
     * 
     * @param email
     *            {@link String}
     * @return new File("D:\\Travel_Portal\\Booking\\History_" + email + EXTN)
     */
    File createUserPDF(String email);

    /**
     * 
     * Method creating the PDF of flight schedule corresponding to a flightId
     * and saving the PDF at a path specified
     * 
     * @param flightId
     *            {@link String}
     * @return new File("D:\\Travel_Portal\\Schedule\\Schedule_" + flightId+
     *         EXTN)
     */
    File createSchedulePDF(String flightId);

    /**
     * 
     * Method creating the PDF of ticket booked by a user and saving the PDF at
     * a path specified
     * 
     * @param ticketId
     *            {@link Integer}
     * @param fsrModel
     *            {@link ConnectorFsrModel}
     * @return new File("D:\\Travel_Portal\\Ticket\\Ticket_" + ticketId + EXTN)
     */
    File createTicketPDF(int ticketId, ConnectorFsrModel fsrModel);

    /**
     * 
     * Method to get the mime type of pdf file for download
     * 
     * @param context
     *            {@link ServletContext}
     * @param fullPath
     *            {@link String}
     * @return mimeType {@link String}
     */
    String getMime(ServletContext context, String fullPath);
}