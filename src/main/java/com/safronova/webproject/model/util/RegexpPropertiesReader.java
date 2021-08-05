package com.safronova.webproject.model.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class RegexpPropertiesReader {
    private static final Logger logger = LogManager.getLogger();
    private static ResourceBundle resourceBundle;
    private static Properties properties;
    private RegexpPropertiesReader(){}

    static {
        try{
            resourceBundle = ResourceBundle.getBundle("regexp", Locale.getDefault());
            Map<String, String> expressions = new HashMap<>();
            Collections.list(resourceBundle.getKeys()).forEach(key -> expressions.put(key, resourceBundle.getString(key)));
            properties = new Properties();
            properties.putAll(expressions);
        }catch (MissingResourceException e){
            logger.fatal( "MissingResourceException: {} {}", e.getMessage(), e.getStackTrace());
            throw new ExceptionInInitializerError("MissingResourceException: " + e.getMessage());
        }
    }

    public static Properties getProperties(){
        return properties;
    }

}
