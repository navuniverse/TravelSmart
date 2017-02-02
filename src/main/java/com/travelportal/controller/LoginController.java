package com.travelportal.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.travelportal.exception.ServiceLayerException;
import com.travelportal.model.DealModel;
import com.travelportal.model.UserModel;
import com.travelportal.service.IDealService;
import com.travelportal.service.ILoginService;
import com.travelportal.service.IPasswordService;

/**
 * 
 * Controller class to manage the login process of user and admin
 * 
 * @author naveen.kumar
 * 
 */
@Controller
public class LoginController {
    public static final String REDIRECT_START = "redirect:/start";
    public static final String COMMAND = "command";
    public static final String DOB = "dob";
    public static final String DEALS = "deals";
    public static final String FORGETPASSWORD = "forgetpassword";
    public static final String WELCOME = "welcome";
    public static final String START = "start";
    public static final String MESSAGE = "message";
    public static final String USER = "user";
    public static final String USER1 = "user1";
    private static Logger logger = Logger.getLogger(LoginController.class);

    private IPasswordService passwordService;
    private ILoginService loginService;
    private IDealService dealService;
    private BaseController baseController;

    public void setPasswordService(IPasswordService passwordService) {
        this.passwordService = passwordService;
    }

    public void setBaseController(BaseController baseController) {
        this.baseController = baseController;
    }

    public void setLoginService(ILoginService loginService) {
        this.loginService = loginService;
    }

    public void setDealService(IDealService dealService) {
        this.dealService = dealService;
    }

    /**
     * 
     * Mapping to display the forgetpassword page
     * 
     * @return "forgetpassword" page
     */
    @RequestMapping(value = "/forgetpassword", method = RequestMethod.GET)
    public String forgetpassword() {
        return FORGETPASSWORD;
    }

    /**
     * 
     * Method to logout from application. On logout, clears all the session data
     * and sends the control to welcome page
     * 
     * @param request
     *            {@link HttpServletRequest}
     * @param model
     *            {@link Model}
     * @return "redirect:/start"
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, Model model) {
        UserModel user = (UserModel) baseController.getSessionAttribute(USER,
                request);
        logger.isInfoEnabled();
        logger.info(String.format("%s USER LOGOUT", user.getUsername()));
        baseController.removeSessionAttribute(USER, request);
        baseController.invalidateSession(request);
        model.addAttribute(MESSAGE, "Logout Successfull!!!!");
        return REDIRECT_START;
    }

    /**
     * 
     * Mapping to display the welcome page after the user has logged in
     * 
     * @param userModel
     *            {@link UserModel}
     * @param request
     *            {@link HttpServletRequest}
     * @return "welcome" page
     */
    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public ModelAndView welcomePage(UserModel userModel,
            HttpServletRequest request) {
        UserModel user = (UserModel) baseController.getSessionAttribute(USER,
                request);
        baseController.setSessionAttribute(USER, user, request);
        return new ModelAndView(WELCOME, COMMAND, userModel);
    }

    /**
     * 
     * Method to user and admin login and redirects to home
     * 
     * @param userModel
     *            {@link UserModel}
     * @param model
     *            {@link Model}
     * @param request
     *            {@link HttpServletRequest}
     * @return "welcome" page
     */
    @RequestMapping(value = "/actionlogin", method = RequestMethod.POST)
    public String login(UserModel userModel, HttpServletRequest request,
            Model model) {
        try {
            List<DealModel> deals = dealService.getDeals();
            baseController.setSessionAttribute(DEALS, deals, request);

            UserModel user = loginService.userLogin(userModel);
            logger.isInfoEnabled();
            logger.info(String.format("%s  USER LOGGED IN",
                    userModel.getUsername()));
            baseController.setSessionAttribute(USER, user, request);
            baseController.setSessionAttribute(DOB, user.getDob(), request);
            user.setPassword(passwordService.encryptPassword(user.getPassword()));
            baseController.setSessionAttribute(USER1, user, request);
        } catch (ServiceLayerException ex) {
            new ExceptionController("Invalid Credentials. Please try again",
                    ex, model);
            return START;
        }
        return WELCOME;

    }
}
