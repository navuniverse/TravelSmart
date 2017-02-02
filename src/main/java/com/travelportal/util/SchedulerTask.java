/**
 * 
 */
package com.travelportal.util;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.travelportal.exception.ServiceLayerException;
import com.travelportal.service.IUploadService;

/**
 * 
 * Class to define the tasks for Quartz Scheduler
 * 
 * @author naveen.kumar
 * 
 */
public class SchedulerTask {

    private static Logger logger = Logger.getLogger(SchedulerJob.class);

    private IUploadService uploadService;

    public void setUploadService(IUploadService uploadService) {
        this.uploadService = uploadService;
    }

    /**
     * 
     * Method to assign a task for scheduler
     * 
     */
    public void schedule() {
        try {
            uploadService.csvParser();
        } catch (IOException e) {
            logger.error("ERROR IN SCHEDULER", e);
        } catch (ServiceLayerException e) {
            logger.error("ERROR IN SCHEDULER", e);
        }
    }
}
