package com.safronova.webproject.model.util;

import jakarta.xml.bind.DatatypeConverter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

public class PasswordEncryptor2 {
    private static final Logger logger = LogManager.getLogger();

    private PasswordEncryptor2(){}

    public static byte[] createSalt() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] salt = new byte[16];
        secureRandom.nextBytes(salt);
        return salt;
    }

    public static String createToken() {
        final int iterations = 10;
        final int size = 32 * 8;
        final String algorithmName = "PBKDF2WithHmacSHA1";
        try {
            char[] messageToHash = DatatypeConverter.printHexBinary(createSalt()).toCharArray();
            KeySpec spec = new PBEKeySpec(messageToHash, createSalt(), iterations, size);
            SecretKeyFactory factory = SecretKeyFactory.getInstance(algorithmName);
            byte[] hash = factory.generateSecret(spec).getEncoded();
            return DatatypeConverter.printHexBinary(hash);
        } catch (NoSuchAlgorithmException  | InvalidKeySpecException e) {
            logger.fatal("Error occured while instantiating SecretKeyFactory with algorithm {}", algorithmName, e);
            throw new RuntimeException("Error occured while instantiating SecretKeyFactory with algorithm " + algorithmName, e);
        }
    }



    public static byte[] hashPassword(String password, byte[] salt) {
        final String algorithmName = "PBKDF2WithHmacSHA1";
        try {
            // do not change this final variables
            final int iterations = 65536;
            final int size = 32 * 8;
            KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterations, size);
            SecretKeyFactory factory = SecretKeyFactory.getInstance(algorithmName);
            byte[] hash = factory.generateSecret(spec).getEncoded();
            return hash;
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            logger.fatal("Error occured while instantiating SecretKeyFactory with algorithm {}", algorithmName, e);
            throw new RuntimeException("Error occured while instantiating SecretKeyFactory with algorithm " + algorithmName, e);
        }
    }
}


