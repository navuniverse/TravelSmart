/**
 * 
 */
package com.travelportal.exception;

/**
 * 
 * Custom Exception class to handle exceptions in DAO
 * 
 * @author naveen.kumar
 * 
 */
public class DaoException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String errorMsg;

    public DaoException(String errorMsg) {
        super();
        this.errorMsg = errorMsg;
    }

    /**
     * @return the errorMsg
     */
    public String getErrorMsg() {
        return errorMsg;
    }

    /**
     * @param errorMsg
     *            the errorMsg to set
     */
    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

}
