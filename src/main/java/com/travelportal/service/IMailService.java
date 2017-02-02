package com.travelportal.service;

import com.travelportal.model.UserModel;

/**
 * 
 * Service interface to perform the mailing functionalities
 * 
 * @author naveen.kumar
 * 
 */
public interface IMailService {

    String ERROR = "error";
    String SUCCESS = "success";

    /**
     * 
     * Method to send a mail by providing the email id of receiver, subject,
     * first name of receiver and email body
     * 
     * @param from
     *            {@link String}
     * @param to
     *            {@link String}
     * @param subject
     *            {@link String}
     * @param firstName
     *            {@link String}
     * @param body
     *            {@link String}
     * @return "success" {@link String}
     */
    String sendMail(String from, String to, String subject, String firstName,
            String body);

    /**
     * 
     * Prepares welcome message for a new registration
     * 
     * @param userModel
     *            {@link UserModel}
     * @return welcomeMessage {@link String}
     */
    String welcomeMessage(UserModel userModel);

    /**
     * 
     * Prepares mail body in case of forget password
     * 
     * @param userModel
     *            {@link UserModel}
     * @return forgetPasswordMessage {@link String}
     */
    String forgetPasswordMessage(UserModel userModel);

    /**
     * 
     * Prepares mail body in case of user contact admin
     * 
     */
    String contactMessage();

}