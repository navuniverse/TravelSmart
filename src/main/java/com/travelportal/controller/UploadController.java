package com.travelportal.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.travelportal.exception.ServiceLayerException;
import com.travelportal.service.IUploadService;
import com.travelportal.service.impl.UploadService;

/**
 * 
 * Controller class to manage methods and mappings used for file upload tasks
 * 
 * @author naveen.kumar
 * 
 */
@Controller
@SuppressWarnings("deprecation")
public class UploadController extends SimpleFormController {
    public static final String XMLUPLOAD = "xmlupload";
    public static final String ADMIN = "admin";
    public static final String MESSAGE = "message";
    private Logger logger = Logger.getLogger(UploadController.class);

    private IUploadService uploadService;

    public IUploadService getUploadService() {
        return uploadService;
    }

    public void setUploadService(IUploadService uploadService) {
        this.uploadService = uploadService;
    }

    /**
     * 
     * Controller method to upload a CSV file through portal
     * 
     * @param csvUtil
     *            {@link IUploadService}
     * @param result
     *            {@link BindingResult}
     * @return "admin" page
     */
    @RequestMapping(value = "/actionCSVUpload", method = RequestMethod.POST)
    public String uploadCSV(UploadService csvFile, BindingResult result,
            Model model) {
        try {
            uploadService.csvUpload(csvFile);
            model.addAttribute(MESSAGE, "CSV File Successfully Uploaded!!!");
        } catch (ServiceLayerException ex) {
            logger.error("CSV FILE NOT SUCCESSFULLY UPLOADED", ex);
            new ExceptionController(
                    "Error Uploading CSV File!!! Please Try Again!!!", ex,
                    model);
        }
        return ADMIN;
    }

    /**
     * 
     * Controller Method to upload a XML file for deal through portal
     * 
     * @param xmlUtil
     *            {@link IUploadService}
     * @param result
     *            {@link BindingResult}
     * @return "admin" page
     */
    @RequestMapping(value = "/actionXMLUpload", method = RequestMethod.POST)
    public String uploadXML(UploadService xmlFile, BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            return XMLUPLOAD;
        }
        try {
            uploadService.xmlUpload(xmlFile);
            uploadService.xmlParser();
            logger.isInfoEnabled();
            logger.info(String.format("XML FILE %s SUCCESSFULLY UPLOADED",
                    xmlFile.getFile().getOriginalFilename()));
            model.addAttribute(MESSAGE, "XML File Successfully Uploaded!!!");
        } catch (ServiceLayerException e) {
            logger.error("XML FILE NOT SUCCESSFULLY UPLOADED", e);
            new ExceptionController(
                    "Error Uploading XML File!!! Please Try Again!!!", e, model);
        }
        return ADMIN;
    }
}