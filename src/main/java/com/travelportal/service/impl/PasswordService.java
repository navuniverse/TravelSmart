/**
 * 
 */
package com.travelportal.service.impl;

import org.jasypt.util.text.BasicTextEncryptor;

import com.travelportal.service.IPasswordService;

/**
 * 
 * Service class to encrypt and decrypt user password
 * 
 * @author naveen.kumar
 * 
 */
public class PasswordService implements IPasswordService {

    /**
     * 
     * Method to encrypt the user password
     * 
     * @param plainPassword
     *            {@link String}
     * @return encryptor.encrypt(password) - Encrypted Password
     */
    public String encryptPassword(String plainPassword) {
        BasicTextEncryptor encryptor = new BasicTextEncryptor();
        encryptor.setPassword("naveen");
        return encryptor.encrypt(plainPassword);
    }

    /**
     * 
     * Method to decrypt the user password
     * 
     * @param encryptedPassword
     *            {@link String}
     * @return decryptor.decrypt(encryptedPassword) - Plain/Decrypted Password
     */
    public String decryptPassword(String encryptedPassword) {
        BasicTextEncryptor decryptor = new BasicTextEncryptor();
        decryptor.setPassword("naveen");
        return decryptor.decrypt(encryptedPassword);
    }
}
