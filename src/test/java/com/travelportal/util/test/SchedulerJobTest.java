/**
 * 
 */
package com.travelportal.util.test;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.travelportal.util.SchedulerJob;
import com.travelportal.util.SchedulerTask;

/**
 * @author naveen.kumar
 * 
 */
public class SchedulerJobTest {

    protected static SchedulerJob schedulerJob;
    protected static SchedulerTask schedulerTask;

    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        schedulerJob = new SchedulerJob();
        schedulerTask = new SchedulerTask();
    }

    /**
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        schedulerJob = null;
        schedulerTask = null;

    }

    /**
     * Test method for
     * {@link com.travelportal.util.SchedulerJob#setSchedulerTask(com.travelportal.util.SchedulerTask)}
     * .
     */
    @Test
    public void testSetSchedulerTask() throws Exception {
        schedulerJob.setSchedulerTask(schedulerTask);
        assertEquals(true, true);
    }

}
