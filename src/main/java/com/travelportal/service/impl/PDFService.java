package com.travelportal.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.travelportal.dao.IAdminDao;
import com.travelportal.dao.IBookingDao;
import com.travelportal.dao.IUserDao;
import com.travelportal.model.ConnectorFsrModel;
import com.travelportal.model.TicketModel;
import com.travelportal.model.TravellerModel;
import com.travelportal.service.IPDFService;

/**
 * 
 * Service Class to create the PDF files
 * 
 * @author naveen.kumar
 * 
 */
public class PDFService implements IPDFService {

    private Logger logger = Logger.getLogger(PDFService.class);
    private IUserDao userDao;
    private IAdminDao adminDao;
    private IBookingDao bookingDao;

    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }

    public void setAdminDao(IAdminDao adminDao) {
        this.adminDao = adminDao;
    }

    public void setBookingDao(IBookingDao bookingDao) {
        this.bookingDao = bookingDao;
    }

    /**
     * 
     * Method creating the PDF file of travel history corresponding to a user id
     * and saving the PDF to a folder specified
     * 
     * @param email
     *            {@link String}
     * @return new File("D:\\Travel_Portal\\Booking\\History_" + email + EXTN)
     */
    public File createUserPDF(String email) {

        try {
            List<TicketModel> tickets = userDao.getHistory(email);
            Document document = new Document();

            OutputStream historyPdf = new FileOutputStream(new File(
                    "D:\\Travel_Portal\\Booking\\History_" + email + EXTN));
            PdfPTable pTable = new PdfPTable(I_4);
            pTable.setWidthPercentage(F_75_0F);
            pTable.setWidths(new int[] { 1, 1, 1, 1 });

            Paragraph paragraph = null;

            for (TicketModel ticket : tickets) {
                ticket.setTravellers(userDao.getTicketDetails(ticket
                        .getTicketId()));
                if (ticket.getTravellers().size() > 0) {
                    paragraph = new Paragraph();
                    paragraph.clear();
                    paragraph = new Paragraph("Ticket Id:"
                            + ticket.getTicketId() + "\t Provider:"
                            + ticket.getConnectorId() + "\n"
                            + ticket.getSource() + ARROW
                            + ticket.getDestination() + "\nBooking Date:"
                            + ticket.getTravelDate() + "\t Status:"
                            + ticket.getStatus());
                    PdfPCell cell = new PdfPCell();

                    cell.addElement(paragraph);
                    cell.setBackgroundColor(new BaseColor(I_10, I_161, I_80));
                    cell.setColspan(I_4);
                    pTable.addCell(cell);

                    for (TravellerModel ticketDetail : ticket.getTravellers()) {
                        pTable.addCell(ticketDetail.getFirstname());
                        pTable.addCell(ticketDetail.getLastname());
                        pTable.addCell(ticketDetail.getSex());
                        pTable.addCell(new String("" + ticketDetail.getAge()));

                    }
                }
            }
            paragraph = null;
            paragraph = new Paragraph("Showing Booking Detaits for user:"
                    + email + "\n\n");
            paragraph.setAlignment(Element.ALIGN_CENTER);
            logger.isInfoEnabled();
            logger.info(String.format(
                    "GENERATING USER HISTORY PDF FILE FOR %s", email));
            PdfWriter.getInstance(document, historyPdf);
            document.open();
            document.addTitle("History :" + email);

            // Add tables/field
            document.add(paragraph);
            document.add(pTable);

            document.close();
            historyPdf.close();

            return new File("D:\\Travel_Portal\\Booking\\History_" + email
                    + EXTN);

        } catch (Exception ex) {
            logger.error("USER HISTORY PDF GENERATION FAILED", ex);
            return null;
        }
    }

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
    public File createSchedulePDF(String flightId) {

        try {
            List<ConnectorFsrModel> fsrModels = adminDao.getSchedule(flightId);
            Document document = new Document();

            OutputStream schedulePdf = new FileOutputStream(new File(
                    "D:\\Travel_Portal\\Schedule\\Schedule_" + flightId + EXTN));
            PdfPTable pTable = new PdfPTable(I_4);
            pTable.setWidthPercentage(F_85_0F);
            pTable.setWidths(new int[] { I_2, I_2, I_4, I_3 });

            pTable.addCell(SCHEDULE_ID);
            pTable.addCell(DATE);
            pTable.addCell(ROUTE);
            pTable.addCell(TIME);

            for (ConnectorFsrModel model : fsrModels) {
                pTable.addCell(model.getScheduleId().getScheduleId());
                pTable.addCell(new String(""
                        + model.getScheduleId().getFlightDate()));
                if (model.getRouteId().getVia() == null) {
                    pTable.addCell(model.getRouteId().getSource() + ARROW
                            + model.getRouteId().getDestination());
                    pTable.addCell(model.getScheduleId()
                            .getSourceDepartureTime()
                            + ARROW
                            + model.getScheduleId().getDestinationArrivalTime());
                } else {
                    pTable.addCell(model.getRouteId().getSource() + ARROW
                            + model.getRouteId().getVia() + ARROW
                            + model.getRouteId().getDestination());
                    pTable.addCell(model.getScheduleId()
                            .getSourceDepartureTime()
                            + ARROW
                            + model.getScheduleId().getViaArrivalTime()
                            + ARROW
                            + model.getScheduleId().getViaDepartureTime()
                            + ARROW
                            + model.getScheduleId().getDestinationArrivalTime());
                }
            }

            Paragraph paragraph = new Paragraph("Showing Schedule for flight: "
                    + flightId + "\n\n");
            paragraph.setAlignment(Element.ALIGN_CENTER);
            logger.isInfoEnabled();
            logger.info(String.format(
                    "GENERATING FLIGHT SCHEDULE PDF FILE FOR %s", flightId));
            PdfWriter.getInstance(document, schedulePdf);
            document.open();
            document.addTitle("Schedule: " + flightId);

            // Add tables/field
            document.add(paragraph);
            document.add(pTable);

            document.close();
            schedulePdf.close();

            return new File("D:\\Travel_Portal\\Schedule\\Schedule_" + flightId
                    + EXTN);

        } catch (Exception ex) {
            logger.error(String.format(
                    "ERROR WHILE GENERATING FLIGHT SCHEDULE PDF FILE FOR %s",
                    flightId), ex);
            return null;
        }

    }

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
    public File createTicketPDF(int ticketId, ConnectorFsrModel fsrModel) {
        try {
            TicketModel ticketModel = bookingDao.ticket(ticketId);
            ticketModel.setTravellers(userDao.getTicketDetails(ticketModel
                    .getTicketId()));
            Document document = new Document();

            OutputStream ticketPdf = new FileOutputStream(new File(
                    "D:\\Travel_Portal\\Ticket\\Ticket_" + ticketId + EXTN));
            PdfPTable pTable = new PdfPTable(I_4);
            pTable.setWidthPercentage(F_85_0F);
            pTable.setWidths(new int[] { 1, 1, 1, 1 });

            Paragraph paragraph = new Paragraph();
            paragraph.clear();
            paragraph = new Paragraph(" Ticket Id:" + ticketModel.getTicketId()
                    + "\t Flight ID: " + fsrModel.getFlightId().getFlightId()
                    + "\t Provider: " + fsrModel.getFlightId().getProvider()
                    + "\n Source: " + ticketModel.getSource()
                    + "\t Destination: " + ticketModel.getDestination()
                    + "\n Flight Route & Timings: "
                    + fsrModel.getRouteId().getSource() + " "
                    + fsrModel.getScheduleId().getSourceDepartureTime() + ARROW
                    + fsrModel.getRouteId().getVia() + " ("
                    + fsrModel.getScheduleId().getViaArrivalTime() + "-"
                    + fsrModel.getScheduleId().getViaDepartureTime() + ")"
                    + ARROW + fsrModel.getRouteId().getDestination() + " "
                    + fsrModel.getScheduleId().getDestinationArrivalTime()
                    + "\n Travel Date:" + ticketModel.getTravelDate()
                    + "\n No of Passengers:" + ticketModel.getNoOfPassenger()
                    + "\t Total fare: Rs. " + ticketModel.getTotalPrice());

            PdfPCell cell = new PdfPCell();
            cell.addElement(paragraph);
            cell.setBackgroundColor(new BaseColor(I_10, I_161, I_80));
            cell.setColspan(I_4);
            pTable.addCell(cell);

            pTable.addCell(FIRST_NAME);
            pTable.addCell(LAST_NAME);
            pTable.addCell(SEX);
            pTable.addCell(AGE);

            for (TravellerModel ticketDetail : ticketModel.getTravellers()) {
                pTable.addCell(ticketDetail.getFirstname());
                pTable.addCell(ticketDetail.getLastname());
                pTable.addCell(ticketDetail.getSex());
                pTable.addCell(new String("" + ticketDetail.getAge()));
            }

            paragraph = null;
            paragraph = new Paragraph(
                    "Thank you for booking ticket with TravelSmart. \n\nFlight Ticket: "
                            + ticketId + "\n\n");
            paragraph.setAlignment(Element.ALIGN_CENTER);
            logger.isInfoEnabled();
            logger.info(String.format(
                    "GENERATING TICKET PDF FILE OF TICKET %s", ticketId));
            PdfWriter.getInstance(document, ticketPdf);
            document.open();
            document.addTitle("Ticket: " + ticketId);

            // Add tables/field
            document.add(paragraph);
            document.add(pTable);

            paragraph = null;
            paragraph = new Paragraph("Travel Smart, Travel Safe");
            paragraph.setAlignment(Element.ALIGN_CENTER);
            document.add(paragraph);
            document.close();
            ticketPdf.close();

            return new File("D:\\Travel_Portal\\Ticket\\Ticket_" + ticketId
                    + EXTN);

        } catch (Exception ex) {
            logger.error(String.format(
                    "TICKET PDF GENERATION FAILED OF TICKET %s", ticketId), ex);
            return null;
        }
    }

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
    public String getMime(ServletContext context, String fullPath) {
        String mimeType = context.getMimeType(fullPath);
        if (mimeType == null) {
            // set to binary type if MIME mapping not found
            mimeType = "application/octet-stream";
        }
        return mimeType;
    }
}