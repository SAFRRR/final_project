package com.safronova.webproject.model.util.mail;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class MailPropertiesReader {
    private static final Logger logger = LogManager.getLogger();

    private static ResourceBundle resourceBundle;
    private static Properties properties;
    private MailPropertiesReader(){}
    static {
        try{
            resourceBundle = ResourceBundle.getBundle("mail");
            Map<String, String> messages = new HashMap<>();
            Collections.list(resourceBundle.getKeys()).forEach(key -> messages.put(key, resourceBundle.getString(key)));
            properties = new Properties();
            properties.putAll(messages);
        }catch (MissingResourceException e){
            logger.fatal( "MissingResourceException: {} {}", e.getMessage(), e.getStackTrace());
            throw new ExceptionInInitializerError("MissingResourceException: " + e.getMessage());
        }
    }
    public static Properties getProperties(){
        return properties;
    }

}

