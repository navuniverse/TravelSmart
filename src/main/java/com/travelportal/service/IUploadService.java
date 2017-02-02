package com.travelportal.service;

import java.io.IOException;

import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.w3c.dom.Element;

import com.travelportal.dao.IAdminDao;
import com.travelportal.exception.ServiceLayerException;
import com.travelportal.model.DealModel;

/**
 * 
 * Service interface to upload the CSV and XML files
 * 
 * @author naveen.kumar
 * 
 */
public interface IUploadService {

    /**
     * 
     * Getter for CommonsMultipartFile
     * 
     * @return file {@link CommonsMultipartFile}
     */
    CommonsMultipartFile getFile();

    /**
     * 
     * Setter for CommonsMultipartFile
     * 
     * @param file
     *            {@link CommonsMultipartFile}
     */
    void setFile(CommonsMultipartFile file);

    /**
     * 
     * Setter for adminDao object
     * 
     * @param adminDao
     *            {@link IAdminDao}
     */
    void setAdminDao(IAdminDao adminDao);

    /**
     * 
     * Method to upload a CSV file
     * 
     * @param csvUtil
     *            {@link IUploadService}
     * @throws ServiceLayerException
     */
    void csvUpload(IUploadService csvFile) throws ServiceLayerException;

    /**
     * 
     * Method to upload a XML File
     * 
     * @param xmlUtil
     *            {@link IUploadService}
     * @throws ServiceLayerException
     */
    void xmlUpload(IUploadService xmlFile) throws ServiceLayerException;

    /**
     * 
     * Method to read the deal details from an XML file and then upload the
     * details into the database
     * 
     * @throws ServiceLayerException
     * 
     */
    void xmlParser() throws ServiceLayerException;

    /**
     * 
     * Method to parse the CSV file of schedule and add schedule to DB
     * 
     * @throws IOException
     * @throws ServiceLayerException
     * 
     */
    void csvParser() throws IOException, ServiceLayerException;

    /**
     * 
     * Method to upload deal to DB
     * 
     * @param eElement
     *            {@link Element}
     * @param dealModel
     *            {@link DealModel}
     * @throws ServiceLayerException
     */
    void uploadDeal(Element eElement, DealModel dealModel)
            throws ServiceLayerException;
}