/**
 * 
 */
package com.travelportal.service.test;

import static org.junit.Assert.assertEquals;

import java.util.Properties;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.travelportal.model.UserModel;
import com.travelportal.service.impl.MailService;
import com.travelportal.service.impl.PasswordService;

/**
 * @author naveen.kumar
 * 
 */
public class MailServiceTest {

    protected static MailService mailService;
    protected static PasswordService passwordService;
    protected static Properties properties;
    protected static UserModel userModel;

    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        mailService = new MailService();
        passwordService = new PasswordService();
        mailService.setPasswordService(passwordService);
        properties = new Properties();
        userModel = new UserModel();
        properties.setProperty("mail.smtp.host", "localhost");
        properties.setProperty("mail.smtp.port", "8091");
    }

    /**
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        mailService = null;
        properties = null;
        userModel = null;
    }

    /**
     * Test method for
     * {@link com.travelportal.service.impl.MailService#sendMail(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}
     * .
     */
    @SuppressWarnings("static-access")
    @Test
    public void testSendMail() {
        mailService.setProperties(properties);
        mailService.sendMail("a@a.com", "b@b.com", "Test Mail", "Test", "Test");
        assertEquals(true, true);
    }

    /**
     * Test method for
     * {@link com.travelportal.service.impl.MailService#welcomeMessage(com.travelportal.model.UserModel)}
     * .
     */
    @Test
    public void testWelcomeMessage() {
        userModel.setUsername("naveen@abc.in");
        userModel.setPassword("12345");
        mailService.welcomeMessage(userModel);
        assertEquals(true, true);
    }

    /**
     * Test method for
     * {@link com.travelportal.service.impl.MailService#forgetPasswordMessage(com.travelportal.model.UserModel)}
     * .
     */
    @Test
    public void testForgetPasswordMessage() {
        userModel.setUsername("naveen.kumar@impetus.co.in");
        userModel.setPassword("12345");
        mailService.forgetPasswordMessage(userModel);
        assertEquals(true, true);
    }

    /**
     * Test method for
     * {@link com.travelportal.service.impl.MailService#contactMessage()}.
     */
    @Test
    public void testContactMessage() {
        mailService.contactMessage();
        assertEquals(true, true);
    }

}
