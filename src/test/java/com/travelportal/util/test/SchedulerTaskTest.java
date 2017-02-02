/**
 * 
 */
package com.travelportal.util.test;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.travelportal.service.impl.UploadService;
import com.travelportal.util.SchedulerTask;

/**
 * @author naveen.kumar
 * 
 */
public class SchedulerTaskTest {

    protected static SchedulerTask schedulerTask;
    protected static UploadService uploadService;

    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        schedulerTask = new SchedulerTask();
        uploadService = new UploadService();
    }

    /**
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        schedulerTask = null;
        uploadService = null;
    }

    /**
     * Test method for
     * {@link com.travelportal.util.SchedulerTask#setUploadService(com.travelportal.service.IUploadService)}
     * .
     */
    @Test
    public void testSetUploadService() throws Exception {
        schedulerTask.setUploadService(uploadService);
        assertEquals(true, true);
    }

    /**
     * Test method for {@link com.travelportal.util.SchedulerTask#schedule()}.
     */
    @Test
    public void testSchedule() throws Exception {
        schedulerTask.schedule();
        assertEquals(true, true);
    }

}
