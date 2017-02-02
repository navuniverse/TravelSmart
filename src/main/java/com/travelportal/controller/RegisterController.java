package com.travelportal.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.travelportal.exception.ServiceLayerException;
import com.travelportal.model.UserModel;
import com.travelportal.service.IMailService;
import com.travelportal.service.IRegistrationService;

/**
 * 
 * Controller class to manage the methods and mappings used for user
 * registration
 * 
 * @author naveen.kumar
 * 
 */
@Controller
public class RegisterController {

    public static final String WELCOME = "welcome";
    public static final String REGISTER = "register";
    public static final String START = "start";
    public static final String MESSAGE = "message";
    public static final String ADMINEMAIL = "admin@travelsmart.com";

    private static Logger logger = Logger.getLogger(RegisterController.class);
    private IRegistrationService registrationService;
    private IMailService mailService;

    public void setRegistrationService(IRegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    public void setMailService(IMailService mailService) {
        this.mailService = mailService;
    }

    /**
     * 
     * Method to register a new user for the portal
     * 
     * @param userModel
     *            - UserModel
     * @param model
     *            - org.springframework.ui.Model
     * @return "start" page
     */
    @RequestMapping(value = "/actionRegister", method = RequestMethod.POST)
    public String registration(UserModel userModel, Model model) {
        if(logger.isInfoEnabled()) {
			logger.info(String.format("%s IS REGISTERING AS NEW USER",
                userModel.getUsername()));
		}
		
        try {
            registrationService.register(userModel);
            if(logger.isInfoEnabled()) {
				logger.info(String.format("%s SUCCESSFULLY REGISTERED",
                    userModel.getUsername()));
			}
            model.addAttribute(MESSAGE,
                    "Registration Successfull!! Login Now!!");
            mailService.sendMail(ADMINEMAIL, userModel.getUsername(),
                    "Welcome to TravelSmart", userModel.getFirstname(),
                    mailService.welcomeMessage(userModel));

        } catch (ServiceLayerException ex) {
            new ExceptionController(
                    "User is already registered. Please Login to your account.",
                    ex, model);
        }
        return START;
    }
}
