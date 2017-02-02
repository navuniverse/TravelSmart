/**
 * 
 */
package com.travelportal.controller.test;

import static org.junit.Assert.assertEquals;

import java.beans.PropertyEditor;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import com.travelportal.controller.UploadController;
import com.travelportal.service.impl.UploadService;

/**
 * 
 * Test class of {@link UploadController}
 * 
 * @author naveen.kumar
 * 
 */
public class TestUploadController {

    protected static UploadController uploadController;
    protected static UploadService uploadService;
    protected static BindingResult result;
    protected static Model model;

    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        uploadController = new UploadController();
        uploadService = new UploadService();
        uploadController.setUploadService(uploadService);
        model = new Model() {

            public Model mergeAttributes(Map<String, ?> arg0) {
                return null;
            }

            public boolean containsAttribute(String arg0) {
                return false;
            }

            public Map<String, Object> asMap() {
                return null;
            }

            public Model addAttribute(String arg0, Object arg1) {
                return null;
            }

            public Model addAttribute(Object arg0) {
                return null;
            }

            public Model addAllAttributes(Map<String, ?> arg0) {
                return null;
            }

            public Model addAllAttributes(Collection<?> arg0) {
                return null;
            }
        };
        result = new BindingResult() {
            public void setNestedPath(String arg0) {
            }

            public void rejectValue(String arg0, String arg1, Object[] arg2,
                    String arg3) {
            }

            public void rejectValue(String arg0, String arg1, String arg2) {
            }

            public void rejectValue(String arg0, String arg1) {
            }

            public void reject(String arg0, Object[] arg1, String arg2) {
            }

            public void reject(String arg0, String arg1) {
            }

            public void reject(String arg0) {
            }

            public void pushNestedPath(String arg0) {
            }

            public void popNestedPath() throws IllegalStateException {
            }

            public boolean hasGlobalErrors() {
                return false;
            }

            public boolean hasFieldErrors(String arg0) {
                return false;
            }

            public boolean hasFieldErrors() {
                return false;
            }

            public boolean hasErrors() {
                return false;
            }

            public String getObjectName() {
                return null;
            }

            public String getNestedPath() {
                return null;
            }

            public List<ObjectError> getGlobalErrors() {
                return null;
            }

            public int getGlobalErrorCount() {
                return 0;
            }

            public ObjectError getGlobalError() {
                return null;
            }

            public Object getFieldValue(String arg0) {
                return null;
            }

            public Class<?> getFieldType(String arg0) {
                return null;
            }

            public List<FieldError> getFieldErrors(String arg0) {
                return null;
            }

            public List<FieldError> getFieldErrors() {
                return null;
            }

            public int getFieldErrorCount(String arg0) {
                return 0;
            }

            public int getFieldErrorCount() {
                return 0;
            }

            public FieldError getFieldError(String arg0) {
                return null;
            }

            public FieldError getFieldError() {
                return null;
            }

            public int getErrorCount() {
                return 0;
            }

            public List<ObjectError> getAllErrors() {
                return null;
            }

            public void addAllErrors(Errors arg0) {

            }

            public String[] resolveMessageCodes(String arg0, String arg1) {
                return null;
            }

            public void recordSuppressedField(String arg0) {
            }

            public Object getTarget() {
                return null;
            }

            public String[] getSuppressedFields() {
                return null;
            }

            public Object getRawFieldValue(String arg0) {
                return null;
            }

            public PropertyEditorRegistry getPropertyEditorRegistry() {
                return null;
            }

            public Map<String, Object> getModel() {
                return null;
            }

            public PropertyEditor findEditor(String arg0, Class<?> arg1) {
                return null;
            }

            public void addError(ObjectError arg0) {
            }
        };
    }

    /**
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        uploadController = null;
        uploadService = null;
        result = null;
        model = null;
    }

    /**
     * Test method for
     * {@link com.travelportal.controller.UploadController#setUploadService(com.travelportal.service.IUploadService)}
     * .
     */
    @Test
    public void testSetUploadService() {
        uploadController.setUploadService(uploadService);
        assertEquals(true, true);
    }

    /**
     * Test method for
     * {@link com.travelportal.controller.UploadController#uploadCSV(com.travelportal.service.impl.UploadService, org.springframework.validation.BindingResult, org.springframework.ui.Model)}
     * .
     * 
     * @throws IOException
     */
    @Test
    public void testUploadCSV() throws IOException {
        assertEquals("admin",
                uploadController.uploadCSV(new UploadService(), result, model));
    }

    /**
     * Test method for
     * {@link com.travelportal.controller.UploadController#uploadXML(com.travelportal.service.impl.UploadService, org.springframework.validation.BindingResult, org.springframework.ui.Model)}
     * .
     * 
     * @throws IOException
     */
    @Test
    public void testUploadXML() throws IOException {
        assertEquals("admin",
                uploadController.uploadXML(new UploadService(), result, model));
    }

}
