package com.travelportal.model;

/**
 * 
 * Model class to connect tables: Schedule, Flight, Route, Availability and Deal
 * Created in order to reduce joins and making the primary keys available from
 * one class
 * 
 * @author naveen.kumar
 * 
 */
public class ConnectorFsrModel implements java.io.Serializable {
    /**
     * 
     * Attributes used to connect all the model classes
     * 
     */
    private static final long serialVersionUID = 1L;
    private String connectorId;
    private ScheduleModel scheduleId;
    private FlightModel flightId;
    private RouteModel routeId;
    private AvailabilityModel availId;
    private DealModel dealId;

    /**
     * 
     * Default Constructor
     * 
     */
    public ConnectorFsrModel() {
        super();
    }

    public String getConnectorId() {
        return connectorId;
    }

    public void setConnectorId(String connectorId) {
        this.connectorId = connectorId;
    }

    public ScheduleModel getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(ScheduleModel scheduleId) {
        this.scheduleId = scheduleId;
    }

    public FlightModel getFlightId() {
        return flightId;
    }

    public void setFlightId(FlightModel flightId) {
        this.flightId = flightId;
    }

    public RouteModel getRouteId() {
        return routeId;
    }

    public void setRouteId(RouteModel routeId) {
        this.routeId = routeId;
    }

    public AvailabilityModel getAvailId() {
        return availId;
    }

    public void setAvailId(AvailabilityModel availId) {
        this.availId = availId;
    }

    public DealModel getDealId() {
        return dealId;
    }

    public void setDealId(DealModel dealId) {
        this.dealId = dealId;
    }

}
