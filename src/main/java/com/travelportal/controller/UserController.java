package com.travelportal.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.travelportal.exception.ServiceLayerException;
import com.travelportal.model.TicketModel;
import com.travelportal.model.UserModel;
import com.travelportal.service.IMailService;
import com.travelportal.service.IPDFService;
import com.travelportal.service.IUserService;

/**
 * 
 * Controller class to manage methods and mappings used for the user
 * functionalities
 * 
 * @author naveen.kumar
 * 
 */
@Controller
public class UserController {

    public static final String REGISTER = "register";
    public static final String PASSWORD_SENT = "Password has been sent to your E-mail Id!!! Check your E-mail Id and Login now.";
    public static final String CONTACT = "contact";
    public static final String FORGETPASSWORD = "forgetpassword";
    public static final String LOGIN = "login";
    public static final String UPDATEPROFILE = "updateprofile";
    public static final String REDIRECT_HISTORY = "redirect:/history";
    public static final String COMMAND = "command";
    public static final String HISTORY = "history";
    public static final String TICKET = "ticket";
    public static final String MESSAGE = "message";
    public static final String ADMINEMAIL = "admin@travelsmart.com";
    public static final int BUFFERSIZE = 4096;
    public static final String USER = "user";

    private Logger logger = Logger.getLogger(UserController.class);
    private IUserService userService;
    private IMailService mailService;
    private IPDFService pdfService;
    private BaseController baseController;

    public void setBaseController(BaseController baseController) {
        this.baseController = baseController;
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    public void setMailService(IMailService mailService) {
        this.mailService = mailService;
    }

    public void setPdfService(IPDFService pdfService) {
        this.pdfService = pdfService;
    }

    /**
     * 
     * Mapping and Method to display the user history of booked and cancelled
     * flights
     * 
     * @param ticketModel
     *            {@link TicketModel}
     * @param request
     *            {@link HttpServletRequest}
     * @return "history" page
     */
    @RequestMapping(value = "/history", method = RequestMethod.GET)
    public ModelAndView history(TicketModel ticketModel,
            HttpServletRequest request, Model model) {
        try {
            UserModel userModel2 = (UserModel) baseController
                    .getSessionAttribute(USER, request);
            List<TicketModel> ticketModels = userService
                    .getUserHistory(userModel2);
            for (int i = 0; i < ticketModels.size(); i++) {
                if (ticketModels.get(i).getTravelDate().compareTo(new Date()) < 0) {
                    ticketModels.get(i).setStatus("TRAVELLED");
                }
            }
            baseController.setSessionAttribute(USER, userModel2, request);
            baseController.setSessionAttribute(TICKET, ticketModels, request);
            return new ModelAndView(HISTORY, COMMAND, ticketModels);
        } catch (ServiceLayerException ex) {
            logger.error(String.format("%s USER HISTORY NOT FOUND", ticketModel
                    .getUserId().getUsername()), ex);
            new ExceptionController("User History not found", ex, model);
        }
        return new ModelAndView(HISTORY, COMMAND, null);

    }

    /**
     * 
     * Method to cancel a flight from user history Send the control back to
     * welcome page
     * 
     * @param model
     *            {@link Model}
     * @param ticketId
     *            {@link Integer}
     * @param ticketModel
     *            {@link TicketModel}
     * @return "redirect:/welcome" page
     */
    @RequestMapping(value = "/history/{ticketId}", method = RequestMethod.GET)
    public String cancelTicket(@PathVariable("ticketId") int ticketId,
            TicketModel ticketModel, Model model) {
        logger.isInfoEnabled();
        logger.info(String.format("CANCELLING THE TICKET %s", ticketId));
        try {
            userService.cancelTicket(ticketId);
            model.addAttribute(MESSAGE, "Ticket Successfully Cancelled!!!!");
        } catch (ServiceLayerException ex) {
            logger.error(
                    String.format("TICKET %s NOT FOUND TO CANCEL", ticketId),
                    ex);
            new ExceptionController("Ticket not found for cancellation", ex,
                    model);
        }
        return REDIRECT_HISTORY;
    }

    /**
     * 
     * Method to update the user profile
     * 
     * @param userModel
     *            {@link UserModel}
     * @param model
     *            {@link Model}
     * @param request
     *            {@link HttpServletRequest}
     * @return "updateprofile" page
     */
    @RequestMapping(value = "/actionProfile", method = RequestMethod.POST)
    public String profile(UserModel userModel, HttpServletRequest request,
            Model model) {
        try {
            logger.isInfoEnabled();
            logger.info(String.format("UPDATING PROFILE OF %s",
                    userModel.getUsername()));
            userService.updateUser(userModel);
            baseController.setSessionAttribute(USER, userModel, request);
            model.addAttribute(MESSAGE, "Profile Successfully Updated!!!");
        } catch (ServiceLayerException ex) {
            logger.error(String.format("%s USER NOT FOUND TO UPDATE PROFILE",
                    userModel.getUsername()), ex);
            new ExceptionController("User Profile not found", ex, model);
        }
        return UPDATEPROFILE;
    }

    /**
     * 
     * Method to retrieve the password of user in case of forget password
     * 
     * @param userId
     *            {@link String}
     * @param model
     *            {@link Model}
     * @return "login" or "register" page
     */
    @RequestMapping(value = "/actionPassword", method = RequestMethod.POST)
    public String forgetPassword(String userId, Model model) {
        try {
            logger.isInfoEnabled();
            logger.info(String.format("RECOVERING PASSWORD OF %s", userId));
            UserModel userModel = userService.getUserPassword(userId);
            mailService.sendMail(ADMINEMAIL, userId,
                    "TravelSmart Login Credentials", userModel.getFirstname(),
                    mailService.forgetPasswordMessage(userModel));
            model.addAttribute(MESSAGE, PASSWORD_SENT);
            return LOGIN;
        } catch (ServiceLayerException ex) {
            logger.error(String.format("%s USER PROFILE NOT FOUND", userId), ex);
            new ExceptionController(
                    "E-mail Id do not exist!!! Please register first.", ex,
                    model);
            return REGISTER;
        }

    }

    /**
     * 
     * Method to create the PDF of user history by calling method of
     * TravelHistoryPDF
     * 
     * @param userModel
     *            {@link UserModel}
     * @param request
     *            {@link HttpServletRequest}
     * @param response
     *            {@link HttpServletResponse}
     * @return "history" page and pdf of user history in new tab
     */
    @RequestMapping(value = "/createUserPDF", method = RequestMethod.GET)
    public String createHistoryPDF(UserModel userModel,
            HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession();
            UserModel userModel2 = (UserModel) baseController
                    .getSessionAttribute(USER, request);
            baseController.setSessionAttribute(USER, userModel2, request);
            pdfService.createUserPDF(userModel2.getUsername());

            ServletContext context = (ServletContext) session
                    .getServletContext();

            // construct the complete absolute path of the file
            String fullPath = "D:\\Travel_Portal\\Booking\\History_"
                    + userModel2.getUsername() + ".pdf";
            File downloadFile = new File(fullPath);
            FileInputStream inputStream = null;
            inputStream = new FileInputStream(downloadFile);

            // set content attributes for the response
            response.setContentType(pdfService.getMime(context, fullPath));
            response.setContentLength((int) downloadFile.length());

            // set headers for the response
            String headerKey = "Content-Disposition";
            String headerValue = String.format("filename=\"%s\"",
                    downloadFile.getName());
            response.setHeader(headerKey, headerValue);

            // get output stream of the response
            OutputStream outStream = null;
            outStream = response.getOutputStream();

            byte[] buffer = new byte[BUFFERSIZE];
            int bytesRead = -1;

            // write bytes read from the input stream into the output stream
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, bytesRead);
            }

            inputStream.close();
            outStream.close();
            logger.isInfoEnabled();
            logger.info(String.format(
                    "%s USER HISTORY PDF GENERATION AND DOWNLOAD SUCCESSFUL",
                    userModel.getUsername()));
        } catch (Exception e) {
            logger.error(String.format(
                    "%s USER HISTORY PDF GENERATION OR DOWNLOAD FAIL",
                    userModel.getUsername()), e);
        }
        return HISTORY;
    }

    /**
     * 
     * Controller method to send the user response to admin
     * 
     * @param from
     *            {@link String}
     * @param body
     *            {@link String}
     * @param model
     *            {@link Model}
     * @return "contact" page
     */
    @RequestMapping(value = "/contact", method = RequestMethod.POST)
    public String contactUs(String from, String body, Model model) {
        mailService.sendMail(from, ADMINEMAIL, "Feedback/Query", "Admin", body);
        mailService.sendMail(ADMINEMAIL, from, "TravelSmart Response", from,
                mailService.contactMessage());
        logger.info("USER CONTACTED TRAVElSMART ADMIN");
        model.addAttribute(
                MESSAGE,
                "Your response has been successfully captured.\n Thanks for contacting TravelSmart.");
        return CONTACT;
    }
}
