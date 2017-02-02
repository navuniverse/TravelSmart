package com.travelportal.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
 * Model class to manage the tickets booked by users
 * 
 * @author naveen.kumar
 * 
 */
public class TicketModel implements java.io.Serializable {
    /**
     * 
     * Attributes used in ticket generation
     * 
     */
    private static final long serialVersionUID = 1L;
    private int ticketId;
    private int noOfPassenger;
    private int totalPrice;
    private Date travelDate;
    private String source;
    private String destination;
    private String status;
    private UserModel userId;
    private String connectorId;
    private List<TravellerModel> travellers = new ArrayList<TravellerModel>();

    /**
     * 
     * Default Constructor
     * 
     */
    public TicketModel() {
        super();
    }

    public String getConnectorId() {
        return connectorId;
    }

    public void setConnectorId(String connectorId2) {
        this.connectorId = connectorId2;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public int getNoOfPassenger() {
        return noOfPassenger;
    }

    public void setNoOfPassenger(int noOfPassenger) {
        this.noOfPassenger = noOfPassenger;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getTravelDate() {
        return travelDate;
    }

    public void setTravelDate(Date travelDate) {
        this.travelDate = travelDate;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UserModel getUserId() {
        return userId;
    }

    public void setUserId(UserModel userId) {
        this.userId = userId;
    }

    public List<TravellerModel> getTravellers() {
        return travellers;
    }

    public void setTravellers(List<TravellerModel> travellers) {
        this.travellers = travellers;
    }
}
