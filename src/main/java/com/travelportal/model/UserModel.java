package com.travelportal.model;

import java.util.Date;

/**
 * 
 * Model class to manage the registered users
 * 
 * @author naveen.kumar
 * 
 */
public class UserModel implements java.io.Serializable {
    /**
     * 
     * Attributes used for user profile during registration
     * 
     */
    private static final long serialVersionUID = 1L;
    private String firstname;
    private String lastname;
    private String username;
    private String mobileno;
    private String password;
    private Date dob;
    private String role;

    /**
     * 
     * Default Constructor
     * 
     */
    public UserModel() {
        super();
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}