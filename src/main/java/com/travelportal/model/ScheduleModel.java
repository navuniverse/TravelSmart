package com.travelportal.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 * Model class to manage the schedule of a flight on daily basis including
 * fare,date and time
 * 
 * @author naveen.kumar
 * 
 */
public class ScheduleModel implements java.io.Serializable {
    /**
     * 
     * Attributes used to manage the flight schedule
     * 
     */
    private static final long serialVersionUID = 1L;
    private String scheduleId;
    private String dealId;
    private Date flightDate;
    private String sourceDepartureTime;
    private String viaArrivalTime;
    private String viaDepartureTime;
    private String destinationArrivalTime;
    private int fareSourceDestination;
    private int fareSourceVia;
    private int fareViaDestination;
    private String duration;

    private Set<ConnectorFsrModel> connectorFsrModels = new HashSet<ConnectorFsrModel>(
            0);

    /**
     * 
     * Default Constructor
     * 
     */
    public ScheduleModel() {
        super();
    }

    public String getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getDealId() {
        return dealId;
    }

    public void setDealId(String dealId) {
        this.dealId = dealId;
    }

    public Date getFlightDate() {
        return flightDate;
    }

    public void setFlightDate(Date flightDate) {
        this.flightDate = flightDate;
    }

    public String getSourceDepartureTime() {
        return sourceDepartureTime;
    }

    public void setSourceDepartureTime(String sourceDepartureTime) {
        this.sourceDepartureTime = sourceDepartureTime;
    }

    public String getViaArrivalTime() {
        return viaArrivalTime;
    }

    public void setViaArrivalTime(String viaArrivalTime) {
        this.viaArrivalTime = viaArrivalTime;
    }

    public String getViaDepartureTime() {
        return viaDepartureTime;
    }

    public void setViaDepartureTime(String viaDepartureTime) {
        this.viaDepartureTime = viaDepartureTime;
    }

    public String getDestinationArrivalTime() {
        return destinationArrivalTime;
    }

    public void setDestinationArrivalTime(String destinationArrivalTime) {
        this.destinationArrivalTime = destinationArrivalTime;
    }

    public int getFareSourceDestination() {
        return fareSourceDestination;
    }

    public void setFareSourceDestination(int fareSourceDestination) {
        this.fareSourceDestination = fareSourceDestination;
    }

    public int getFareSourceVia() {
        return fareSourceVia;
    }

    public void setFareSourceVia(int fareSourceVia) {
        this.fareSourceVia = fareSourceVia;
    }

    public int getFareViaDestination() {
        return fareViaDestination;
    }

    public void setFareViaDestination(int fareViaDestination) {
        this.fareViaDestination = fareViaDestination;
    }

    public Set<ConnectorFsrModel> getConnectorFsrModels() {
        return connectorFsrModels;
    }

    public void setConnectorFsrModels(Set<ConnectorFsrModel> connectorFsrModels) {
        this.connectorFsrModels = connectorFsrModels;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

}
