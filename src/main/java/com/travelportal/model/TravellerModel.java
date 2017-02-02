package com.travelportal.model;

/**
 * 
 * Model class to manage the traveller data insert during ticket booking
 * 
 * @author naveen.kumar
 * 
 */
public class TravellerModel implements java.io.Serializable {
    /**
     * 
     * Attributes used for travellers
     * 
     */
    private static final long serialVersionUID = 1L;
    private int travellerId;
    private String firstname;
    private String lastname;
    private String sex;
    private int age;
    private TicketModel ticketId;

    /**
     * 
     * Default Constructor
     * 
     */
    public TravellerModel() {
        super();
    }

    public int getTravellerId() {
        return travellerId;
    }

    public void setTravellerId(int travellerId) {
        this.travellerId = travellerId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public TicketModel getTicketId() {
        return ticketId;
    }

    public void setTicketId(TicketModel ticketId) {
        this.ticketId = ticketId;
    }

}
