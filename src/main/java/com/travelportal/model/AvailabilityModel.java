package com.travelportal.model;

/**
 * 
 * Model class to manage the availability of a flight according to schedule
 * 
 * @author naveen.kumar
 * 
 */
public class AvailabilityModel implements java.io.Serializable {
    /**
     * 
     * Attributes used to manage flight availability
     * 
     */
    private static final long serialVersionUID = 1L;
    private String availId;
    private String flightId;
    private int availSourceVia;
    private int availViaDestination;
    private int availSourceDestination;

    /**
     * 
     * Default Constructor
     * 
     */
    public AvailabilityModel() {
        super();
    }

    public String getAvailId() {
        return availId;
    }

    public void setAvailId(String availId) {
        this.availId = availId;
    }

    public String getFlightId() {
        return flightId;
    }

    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }

    public int getAvailSourceVia() {
        return availSourceVia;
    }

    public void setAvailSourceVia(int availSourceVia) {
        this.availSourceVia = availSourceVia;
    }

    public int getAvailViaDestination() {
        return availViaDestination;
    }

    public void setAvailViaDestination(int availViaDestination) {
        this.availViaDestination = availViaDestination;
    }

    public int getAvailSourceDestination() {
        return availSourceDestination;
    }

    public void setAvailSourceDestination(int availSourceDestination) {
        this.availSourceDestination = availSourceDestination;
    }

}
