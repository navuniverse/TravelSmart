/**
 * 
 */
package com.travelportal.exception;

/**
 * 
 * Custom Exception class to handle exceptions of Service Layer
 * 
 * @author naveen.kumar
 * 
 */
public class ServiceLayerException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String serviceError;

    public String getServiceError() {
        return serviceError;
    }

    public void setServiceError(String serviceError) {
        this.serviceError = serviceError;
    }

    public ServiceLayerException(String serviceError) {
        super();
        this.serviceError = serviceError;
    }

}
