package com.travelportal.model;

/**
 * 
 * Model class to manage the flight details
 * 
 * @author naveen.kumar
 * 
 */
public class FlightModel implements java.io.Serializable {
    /**
     * 
     * Attributes used for flight information
     * 
     */
    private static final long serialVersionUID = 1L;
    private String flightId;
    private int capacity;
    private String provider;
    private String flightType;

    /**
     * 
     * Default Constructor
     * 
     */
    public FlightModel() {
        super();
    }

    public String getFlightId() {
        return this.flightId;
    }

    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getProvider() {
        return this.provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getFlightType() {
        return flightType;
    }

    public void setFlightType(String flightType) {
        this.flightType = flightType;
    }

}