package com.travelportal.util;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * 
 * Class to execute the jobs assigned to Quartx Scheduler
 * 
 * @author naveen.kumar
 * 
 */
public class SchedulerJob extends QuartzJobBean {

    private SchedulerTask schedulerTask;

    public void setSchedulerTask(SchedulerTask schedulerTask) {
        this.schedulerTask = schedulerTask;
    }

    /**
     * 
     * Method to execute a job assigned to scheduler
     * 
     */
    @Override
    protected void executeInternal(JobExecutionContext arg0)
            throws JobExecutionException {
        schedulerTask.schedule();
    }

}
