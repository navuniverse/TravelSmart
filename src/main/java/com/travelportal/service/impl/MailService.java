package com.travelportal.service.impl;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

import com.travelportal.model.UserModel;
import com.travelportal.service.IMailService;
import com.travelportal.service.IPasswordService;

/**
 * 
 * Service class to perform the mailing functionalities
 * 
 * @author naveen.kumar
 * 
 */
public class MailService implements IMailService {

    private static Logger logger = Logger.getLogger(MailService.class);

    private IPasswordService passwordService;

    public void setPasswordService(IPasswordService passwordService) {
        this.passwordService = passwordService;
    }

    private static Properties properties = new Properties();

    public static Properties getProperties() {
        return properties;
    }

    public static void setProperties(Properties properties) {
        MailService.properties = properties;
    }

    // Static block to set the SMTP properties
    static {
        properties.put("mail.smtp.host", "localhost");
        properties.put("mail.smtp.port", "8091");
    }

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
    public String sendMail(String from, String to, String subject,
            String firstName, String body) {
        try {
            Session session = Session.getDefaultInstance(properties);

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText("Hello " + firstName + ",\n\n" + body
                    + "\n\nBest Regards,\nTravelSmart Team");
            Transport.send(message);
            logger.isInfoEnabled();
            logger.info(String.format("MAIL SUCCESSFULLY SENT TO %s", to));
            return SUCCESS;
        } catch (Exception e) {
            logger.error(String.format("MAIL COULD NOT BE SENT TO %s", to), e);
            return ERROR;
        }
    }

    /**
     * 
     * Prepares welcome message for a new registration
     * 
     * @param userModel
     *            {@link UserModel}
     * @return welcomeMessage {@link String}
     */
    public String welcomeMessage(UserModel userModel) {
        String welcomeMessage = "Congratulations!!!!! \n You have successfully created a TravalSmart account. You can now use your account for flight booking and get exciting deals.\n\n Your TravelSmart login credentials are:\n Login ID: "
                + userModel.getUsername()
                + "\n Password: "
                + userModel.getPassword()
                + "\n\n Thanks for your interest in TravelSmart.";
        return welcomeMessage;
    }

    /**
     * 
     * Prepares mail body in case of forget password
     * 
     * @param userModel
     *            {@link UserModel}
     * @return forgetPasswordMessage {@link String}
     */
    public String forgetPasswordMessage(UserModel userModel) {
        String forgetPasswordMessage = "Your TravelSmart Login Credentials are: \n User ID: "
                + userModel.getUsername()
                + "\n Password: "
                + userModel.getPassword()
                + "\n\n Thank you for using TravelSmart.";
        userModel.setPassword(passwordService.encryptPassword(userModel
                .getPassword()));
        return forgetPasswordMessage;
    }

    /**
     * 
     * Prepares mail body in case of user contact admin
     * 
     */
    public String contactMessage() {
        return "You recently contacted to TravelSmart. We will reach you shortly. \n Thanks for using TravelSmart. \n Have a nice day.";
    }
}
