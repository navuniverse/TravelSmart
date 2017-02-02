package com.travelportal.service;

/**
 * 
 * Service interface to encrypt and decrypt user password
 * 
 * @author naveen.kumar
 * 
 */
public interface IPasswordService {

    /**
     * 
     * Method to encrypt the user password
     * 
     * @param plainPassword
     *            {@link String}
     * @return encryptor.encrypt(password) - Encrypted Password
     */
    String encryptPassword(String plainPassword);

    /**
     * 
     * Method to decrypt the user password
     * 
     * @param encryptedPassword
     *            {@link String}
     * @return decryptor.decrypt(encryptedPassword) - Plain/Decrypted Password
     */
    String decryptPassword(String encryptedPassword);

}