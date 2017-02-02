/**
 * 
 */
package com.travelportal.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.travelportal.dao.IAdminDao;
import com.travelportal.exception.DaoException;
import com.travelportal.exception.ServiceLayerException;
import com.travelportal.model.DealModel;
import com.travelportal.service.IUploadService;

/**
 * 
 * Service class to upload the CSV and XML files
 * 
 * @author naveen.kumar
 * 
 */
public class UploadService implements IUploadService {

    private static Logger logger = Logger.getLogger(UploadService.class);

    private Properties properties = new Properties();
    private CommonsMultipartFile file;
    private DealModel dealModel;
    private IAdminDao adminDao;

    public UploadService() throws IOException {
        properties.load(getClass().getClassLoader().getResourceAsStream(
                "filespath.properties"));
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public void setDealModel(DealModel dealModel) {
        this.dealModel = dealModel;
    }

    public CommonsMultipartFile getFile() {
        return file;
    }

    public void setFile(CommonsMultipartFile file) {
        this.file = file;
    }

    public void setAdminDao(IAdminDao adminDao) {
        this.adminDao = adminDao;
    }

    /**
     * 
     * Method to upload a CSV file
     * 
     * @param csvUtil
     *            {@link IUploadService}
     * @throws ServiceLayerException
     */
    public void csvUpload(IUploadService csvFile) throws ServiceLayerException {
        try {
            File destFile = new File(properties.getProperty("schedulePath")
                    + csvFile.getFile().getOriginalFilename());

            csvFile.getFile().transferTo(destFile);
            logger.info("CSV FILE SUCCESSFULLY UPLOADED");
        } catch (IllegalStateException ex) {
            logger.error("ERROR UPLOADING CSV", ex);
            throw new ServiceLayerException("Error in uploading CSV");
        } catch (IOException ex) {
            logger.error("ERROR IN SAVING CSV", ex);
            throw new ServiceLayerException("Error in saving CSV");
        }
    }

    /**
     * 
     * Method to upload a XML File
     * 
     * @param xmlUtil
     *            {@link IUploadService}
     * @throws ServiceLayerException
     */
    public void xmlUpload(IUploadService xmlFile) throws ServiceLayerException {
        try {
            File destFile = new File(properties.getProperty("dealPath")
                    + xmlFile.getFile().getOriginalFilename());
            xmlFile.getFile().transferTo(destFile);
        } catch (IllegalStateException ex) {
            logger.error("ERROR UPLOADING XML", ex);
            throw new ServiceLayerException("Error in uploading XML");
        } catch (IOException ex) {
            logger.error("ERROR IN SAVING XML", ex);
            throw new ServiceLayerException("Error in saving XML");
        }
    }

    /**
     * 
     * Method to read the deal details from an XML file and then upload the
     * details into the database
     * 
     * @throws ServiceLayerException
     * 
     */
    public void xmlParser() throws ServiceLayerException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
        Date startDate, endDate;
        try {
            File[] files = new File(properties.getProperty("dealPath"))
                    .listFiles();
            for (File fXmlFile : files) {
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory
                        .newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(fXmlFile);
                doc.getDocumentElement().normalize();

                NodeList nList = doc.getElementsByTagName("DEAL");

                // Reading data from XML file
                logger.isInfoEnabled();
                logger.info(String.format("READING DATA FROM XML FILE %s",
                        fXmlFile.getName()));
                for (int temp = 0; temp < nList.getLength(); temp++) {
                    Node nNode = nList.item(temp);
                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element eElement = (Element) nNode;
                        startDate = formatter.parse(eElement
                                .getElementsByTagName("START_DATE").item(0)
                                .getTextContent());
                        endDate = formatter.parse(eElement
                                .getElementsByTagName("END_DATE").item(0)
                                .getTextContent());
                        dealModel.setDealId(eElement
                                .getElementsByTagName("DEAL_ID").item(0)
                                .getTextContent());
                        dealModel.setStartDate(startDate);
                        dealModel.setEndDate(endDate);
                        dealModel.setDetails(eElement
                                .getElementsByTagName("DETAILS").item(0)
                                .getTextContent());

                        logger.info("MAKING CHANGES IN DEAL TABLE");
                        uploadDeal(eElement, dealModel);

                    }
                }
            }
        } catch (Exception e) {
            logger.error("CANNOT READ XML FILE", e);
            throw new ServiceLayerException("Cannot read xml file");
        }
    }

    /**
     * 
     * Method to parse the CSV file of schedule and add schedule to DB
     * 
     * @throws IOException
     * @throws ServiceLayerException
     * 
     */
    public void csvParser() throws ServiceLayerException, IOException {
        BufferedReader fileReader = null;
        // Delimiter used in CSV file final
        String delimeter = ",";
        try {
            String line = "";
            File[] files = new File(properties.getProperty("schedulePath"))
                    .listFiles();
            for (File csvFile : files) {
                // Create the file reader
                fileReader = new BufferedReader(new FileReader(csvFile));

                logger.info(String.format("READING DATA FROM CSV FILE %s",
                        csvFile.getName()));
                // Read the file line by line
                while ((line = fileReader.readLine()) != null) {
                    // Get all tokens available in line
                    String[] tokens = line.split(delimeter);
                    if (tokens[0].equals("UPDATE")) {
                        try {
                            adminDao.updateSchedule(tokens);
                        } catch (DaoException ex) {
                            logger.error(
                                    "CANNNOT UPDATE SCHEDULE. DUPLICATE ENTRY",
                                    ex);
                            throw new ServiceLayerException(
                                    "Cannot update Schedule already exists");
                        }
                    } else if (tokens[0].equals("ADD")) {
                        try {
                            adminDao.addSchedule(tokens);
                        } catch (DaoException ex) {
                            logger.error(
                                    "CANNOT ADD NEW SCHEDULE. DUPLICATE ENTRY",
                                    ex);
                            throw new ServiceLayerException(
                                    "Cannot add new schedule already exists");
                        }
                    } else if (tokens[0].equals("DELETE")) {
                        try {
                            adminDao.deleteSchedule(tokens);
                        } catch (DaoException ex) {
                            logger.error(
                                    "CANNOT DELETE SCHEDULE. NO SCHEDULE AVAILABLE",
                                    ex);
                            throw new ServiceLayerException(
                                    "Cannot delete schedule. No enrty found");
                        }

                    }
                }
            }
        } finally {
            fileReader.close();
        }
    }

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
    public void uploadDeal(Element eElement, DealModel dealModel)
            throws ServiceLayerException {
        // Calling DAO method to add/update/delete the deal from
        // DB
        if (eElement.getElementsByTagName("ACTION").item(0).getTextContent()
                .equals("ADD")) {
            try {
                adminDao.addDeal(dealModel);
            } catch (DaoException e) {
                logger.error(String.format(
                        "CANNOT ADD DEAL %s. UNIQUE CONSTRAINT",
                        dealModel.getDealId()), e);
                throw new ServiceLayerException("Deal not added");
            }
        } else if (eElement.getElementsByTagName("ACTION").item(0)
                .getTextContent().equals("UPDATE")) {
            try {
                adminDao.updateDeal(dealModel);
            } catch (DaoException e) {
                logger.error(String.format(
                        "CANNOT UPDATE DEAL %s. UNIQUE CONSTRAINT",
                        dealModel.getDealId()), e);
                throw new ServiceLayerException("Deal not updated");
            }
        } else {
            try {
                adminDao.deleteDeal(dealModel);
            } catch (DaoException e) {
                logger.error(String.format(
                        "CANNOT DELETE DEAL %s. NO ENTRY FOUND",
                        dealModel.getDealId()), e);
                throw new ServiceLayerException("Deal not deleted");
            }
        }
    }
}
