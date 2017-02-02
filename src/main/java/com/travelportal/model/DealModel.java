package com.travelportal.model;

import java.util.Date;

/**
 * 
 * Model class to manage the deals
 * 
 * @author naveen.kumar
 * 
 */
public class DealModel implements java.io.Serializable {
    /**
     * 
     * Attributes used to manage deals
     * 
     */
    private static final long serialVersionUID = 1L;
    private String dealId;
    private Date startDate;
    private Date endDate;
    private String details;

    /**
     * 
     * Default Constructor
     * 
     */
    public DealModel() {
        super();
    }

    public String getDealId() {
        return dealId;
    }

    public void setDealId(String dealId) {
        this.dealId = dealId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

}
