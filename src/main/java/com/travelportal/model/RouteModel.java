package com.travelportal.model;

/**
 * 
 * Model class to manage the flight routes
 * 
 * @author naveen.kumar
 * 
 */
public class RouteModel implements java.io.Serializable {
    /**
     * 
     * Attributes used to manage the routes
     * 
     */
    private static final long serialVersionUID = 1L;
    private String routeId;
    private String source;
    private String destination;
    private String via;

    /**
     * 
     * Default Constructor
     * 
     */
    public RouteModel() {
        super();
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
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

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

}
